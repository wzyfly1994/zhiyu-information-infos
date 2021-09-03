package com.zhiyu.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyu.common.entity.dto.elasticsearch.IndexDocDto;
import com.zhiyu.common.entity.dto.elasticsearch.SearchDocDto;
import com.zhiyu.common.exception.BusinessException;
import com.zhiyu.common.service.ElasticsearchService;
import com.zhiyu.common.utils.response.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wengzhiyu
 * @since 2021/8/31 15:39
 */
@Service
@Slf4j
public class ElasticsearchServiceImpl implements ElasticsearchService {
    private final RestHighLevelClient restHighLevelClient;

    public ElasticsearchServiceImpl(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }

    @Override
    public ResponseData createIndex(String index, String settings, String mappings) {
        try {
            if (!existsIndex(index)) {
                CreateIndexRequest createIndexRequest = new CreateIndexRequest(index);
                buildIndexSetting(createIndexRequest, settings);
                buildIndexMapping(createIndexRequest, mappings);
                restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
            }
            return ResponseData.success();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public void buildIndexSetting(CreateIndexRequest createIndexRequest, String setting) {
        createIndexRequest.settings(setting, XContentType.JSON);
    }

    @Override
    public void buildIndexMapping(CreateIndexRequest createIndexRequest, String mapping) {
        createIndexRequest.mapping(mapping, XContentType.JSON);
    }

    @Override
    public ResponseData createIndexDoc(IndexDocDto indexDocDto) {
        IndexRequest request = new IndexRequest();
        request.index(indexDocDto.getIndex());
        request.type(indexDocDto.getType());
        request.id(indexDocDto.getId());
        String json = JSON.toJSONString(indexDocDto.getSource());
        request.source(json, XContentType.JSON);
        try {
            restHighLevelClient.index(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return ResponseData.success();
    }


    @Override
    public ResponseData deleteIndex(String index, String type, String id) {
        try {
            DeleteRequest deleteRequest = new DeleteRequest(index, type, id);
            restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
            return ResponseData.success();
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public ResponseData updateIndexDoc(IndexDocDto indexDocDto) {
        try {
            UpdateRequest request = new UpdateRequest(indexDocDto.getIndex(), indexDocDto.getType(), indexDocDto.getId());
            request.doc(JSON.toJSONString(indexDocDto.getSource()), XContentType.JSON);
            UpdateResponse updateResponse = restHighLevelClient.update(request, RequestOptions.DEFAULT);
            return ResponseData.success(updateResponse.toString());
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    /**
     * 判断索引是否存在
     *
     * @param index
     * @return
     * @throws IOException
     */
    private boolean existsIndex(String index) throws IOException {
        GetIndexRequest request = new GetIndexRequest(index);
        request.indices();
        return restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
    }


    @Override
    public void bulkInsert(List<IndexDocDto> indexDocDtoList) {
        try {
            BulkRequest bulkAddRequest = new BulkRequest();
            int size = indexDocDtoList.size();
            IndexDocDto indexDocDto;
            for (int i = 0; i < size; i++) {
                indexDocDto = indexDocDtoList.get(i);
                IndexRequest indexRequest = new IndexRequest(indexDocDto.getIndex(), indexDocDto.getType(), indexDocDto.getId());
                indexRequest.source(JSON.toJSONString(indexDocDto.getSource()), XContentType.JSON);
            }
            restHighLevelClient.bulk(bulkAddRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public ResponseData allSearch(SearchDocDto searchDocDto) {
        try {
            int pageNo = searchDocDto.getPageNo();
            int pageSize = searchDocDto.getPageSize();
            int startIndex = (pageNo - 1) * pageSize;
            String index = searchDocDto.getIndex();
            IPage page = new Page<>(pageNo, pageSize);
            List searchList;

            SearchRequest searchRequest = new SearchRequest(index);
            searchRequest.types(searchDocDto.getType());
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            String[] fieldNames = getQueryFieldNames(index);
            sourceBuilder.query(QueryBuilders.multiMatchQuery(searchDocDto.getKeywords(), fieldNames).fuzziness(Fuzziness.AUTO).maxExpansions(10));
            sourceBuilder.from(startIndex);
            sourceBuilder.size(pageSize);
            if (searchDocDto.getIsHighlight()) {
                HighlightBuilder highlightBuilder = new HighlightBuilder();
                Arrays.stream(fieldNames).forEach(highlightBuilder::field);
                sourceBuilder.highlighter(highlightBuilder);
            }
            searchRequest.source(sourceBuilder);

            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            RestStatus restStatus = searchResponse.status();
            log.info(String.valueOf(restStatus.getStatus()));
            if (RestStatus.OK.equals(restStatus)) {
                SearchHits hits = searchResponse.getHits();
                TotalHits totalHits = hits.getTotalHits();
                float maxScore = hits.getMaxScore();
                log.info(String.valueOf(totalHits));
                log.info(String.valueOf(maxScore));
                SearchHit[] searchHits = hits.getHits();
                searchList = getSearchList(searchHits, searchDocDto.getIsHighlight());
                page.setRecords(searchList);
                page.setTotal(totalHits.value);
            }
            return ResponseData.success(page);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public String[] getAllFieldNames(String index) {
        try {
            GetIndexRequest getIndexRequest = new GetIndexRequest(index);
            getIndexRequest.indices();
            GetIndexResponse getIndexResponse = restHighLevelClient.indices().get(getIndexRequest, RequestOptions.DEFAULT);
            Map<String, Object> map = getIndexResponse.getMappings().get(index).getSourceAsMap();
            LinkedHashMap<String, LinkedHashMap> linkedHashMap = (LinkedHashMap<String, LinkedHashMap>) (map.get("properties"));
            return linkedHashMap.keySet().toArray(new String[linkedHashMap.keySet().size()]);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public String[] getQueryFieldNames(String index) {
        try {
            GetIndexRequest getIndexRequest = new GetIndexRequest(index);
            getIndexRequest.indices();
            GetIndexResponse getIndexResponse = restHighLevelClient.indices().get(getIndexRequest, RequestOptions.DEFAULT);
            Map<String, Object> map = getIndexResponse.getMappings().get(index).getSourceAsMap();
            LinkedHashMap<String, LinkedHashMap> linkedHashMap = (LinkedHashMap<String, LinkedHashMap>) (map.get("properties"));
            List<String> fieldNames = new ArrayList<>();
            for (Map.Entry<String, LinkedHashMap> entry : linkedHashMap.entrySet()) {
                String key = entry.getKey();
                LinkedHashMap linkedValue = entry.getValue();
                String typeValue = (String) linkedValue.get("type");
                if (typeValue != null) {
                    if ("text".equals(typeValue) || "object".equals(typeValue)) {
                        fieldNames.add(key);
                    }
                } else {
                    fieldNames.add(key);
                }
            }
            return fieldNames.toArray(new String[fieldNames.size()]);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public List getSearchList(SearchHit[] searchHits, Boolean isHighlight) {
        List searchList = new ArrayList();
        for (SearchHit hit : searchHits) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            if (isHighlight) {
                //取高亮结果
                Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                for (Map.Entry<String, HighlightField> entry : highlightFields.entrySet()) {
                    String key = entry.getKey();
                    HighlightField highlight = entry.getValue();
                    if (highlight != null) {
                        Text[] fragments = highlight.fragments();
                        if (fragments != null) {
                            String fragmentString = fragments[0].string();
                            sourceAsMap.put(key, fragmentString);
                        }
                    }
                }
            }
            searchList.add(sourceAsMap);
        }
        return searchList;
    }
}
