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
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
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
            CreateIndexResponse createIndexResponse = null;
            if (!existsIndex(index)) {
                CreateIndexRequest createIndexRequest = new CreateIndexRequest(index);
                buildIndexSetting(createIndexRequest, settings);
                buildIndexMapping(createIndexRequest, mappings);
                createIndexRequest.settings();
                createIndexResponse = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
            }
            return ResponseData.success(createIndexResponse == null ? "索引已存在" : JSON.toJSONString(createIndexResponse));
        } catch (Exception e) {
            log.error("索引创建失败", e);
            return ResponseData.error(e.getMessage());
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
    public ResponseData deleteIndex(String indexName) {
        try {
            DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(indexName);
            restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
            return ResponseData.success("索引删除成功");
        } catch (Exception e) {
            log.error("索引删除失败", e);
            return ResponseData.error(e.getMessage());
        }
    }

    @Override
    public ResponseData createIndexDoc(IndexDocDto indexDocDto) {
        IndexRequest request = new IndexRequest();
        request.index(indexDocDto.getIndex());
        request.id(indexDocDto.getId());
        request.source(indexDocDto.getSource(), XContentType.JSON);
        try {
            IndexResponse index = restHighLevelClient.index(request, RequestOptions.DEFAULT);
            return ResponseData.success(JSON.toJSONString(index));
        } catch (IOException e) {
            log.error("创建文档失败", e);
            return ResponseData.error(e.getMessage());
        }
    }


    @Override
    public ResponseData updateIndexDoc(IndexDocDto indexDocDto) {
        try {
            UpdateRequest updateRequest = new UpdateRequest(indexDocDto.getIndex(), indexDocDto.getId());
            updateRequest.doc(indexDocDto.getSource(), XContentType.JSON);
            UpdateResponse updateResponse = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
            return ResponseData.success(JSON.toJSONString(updateResponse));
        } catch (Exception e) {
            log.error("更新文档失败", e);
            return ResponseData.error(e.getMessage());
        }
    }

    @Override
    public ResponseData deleteIndexDoc(String index, String id) {
        try {
            DeleteRequest deleteRequest = new DeleteRequest(index, id);
            DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
            return ResponseData.success(JSON.toJSONString(deleteResponse));
        } catch (IOException e) {
            log.error("删除文档失败", e);
            return ResponseData.error(e.getMessage());
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
    public ResponseData bulkInsert(List<IndexDocDto> indexDocDtoList) {
        try {
            if (CollectionUtils.isEmpty(indexDocDtoList)) {
                return ResponseData.error("插入数据为空");
            }
            BulkRequest bulkAddRequest = new BulkRequest();
            for (IndexDocDto indexDocDto : indexDocDtoList) {
                IndexRequest indexRequest = new IndexRequest(indexDocDto.getIndex());
                indexRequest.source(indexDocDto.getSource(), XContentType.JSON);
                bulkAddRequest.add(indexRequest);
            }
            BulkResponse bulkResponse = restHighLevelClient.bulk(bulkAddRequest, RequestOptions.DEFAULT);
            return ResponseData.success(JSON.toJSONString(bulkResponse));
        } catch (Exception e) {
            log.error("批量添加文档失败", e);
            return ResponseData.error(e.getMessage());
        }
    }

    @Override
    public ResponseData allSearch(SearchDocDto searchDocDto) {
        try {
            int pageNo = searchDocDto.getPageNo();
            int pageSize = searchDocDto.getPageSize();
            int startIndex = (pageNo - 1) * pageSize;
            //获取索引名称
            String index = searchDocDto.getIndex();
            IPage<Map<String, Object>> page = new Page<>(pageNo, pageSize);
            List<Map<String, Object>> searchList;
            SearchRequest searchRequest = new SearchRequest(index);
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            String[] fieldNames = getQueryFieldNames(index);
            MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("detail", searchDocDto.getKeywords());
            queryBuilder.fuzziness(Fuzziness.AUTO);

            // 模糊匹配
//            MultiMatchQueryBuilder matchQueryBuilder = QueryBuilders.multiMatchQuery(searchDocDto.getKeywords(), fieldNames);
//            matchQueryBuilder.fuzziness(Fuzziness.AUTO);
            //sourceBuilder.query(QueryBuilders.multiMatchQuery(searchDocDto.getKeywords(), fieldNames).fuzziness(Fuzziness.AUTO).maxExpansions(10));
            sourceBuilder.query(queryBuilder);
            sourceBuilder.from(startIndex);
            sourceBuilder.size(pageSize);
            if (searchDocDto.getIsHighlight()) {
                HighlightBuilder highlightBuilder = new HighlightBuilder();
                // highlightBuilder.field("detail").preTags("<font color='red'>").postTags("</font>");
//                Arrays.stream(fieldNames).forEach(e -> {
//                            highlightBuilder.field(e).preTags("<font color='red'>").postTags("</font>");
//                        }
//                );
               highlightBuilder.field("detail").preTags("<font color='red'>").postTags("</font>");
//                HighlightBuilder.Field field=new HighlightBuilder.Field("detail");
//                field.highlighterType("unified");
                //highlightBuilder.field("detail").highlighterType("unified");
                highlightBuilder.highlighterType("unified");
                highlightBuilder.highlightQuery(queryBuilder);
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
    @SuppressWarnings("all")
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
    @SuppressWarnings("unchecked")
    public String[] getQueryFieldNames(String index) {
        try {
            GetIndexRequest indexRequest = new GetIndexRequest(index);
            indexRequest.indices();
            GetIndexResponse getIndexResponse = restHighLevelClient.indices().get(indexRequest, RequestOptions.DEFAULT);
            Map<String, Object> map = getIndexResponse.getMappings().get(index).getSourceAsMap();
            LinkedHashMap<String, LinkedHashMap<String, Object>> linkedHashMap = (LinkedHashMap<String, LinkedHashMap<String, Object>>) map.get("properties");
            List<String> fieldNames = new ArrayList<>();
            for (Map.Entry<String, LinkedHashMap<String, Object>> entry : linkedHashMap.entrySet()) {
                String key = entry.getKey();
                LinkedHashMap<String, Object> linkedValue = entry.getValue();
                String typeValue = (String) linkedValue.get("type");
                if (typeValue != null) {
                    if ("text".equals(typeValue) || "object".equals(typeValue)) {
                        fieldNames.add(key);
                    }
                } else {
                    fieldNames.add(key);
                }
            }
            int listSize = fieldNames.size();
            return fieldNames.toArray(new String[listSize]);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public List<Map<String, Object>> getSearchList(SearchHit[] searchHits, Boolean isHighlight) {
        List<Map<String, Object>> searchList = new ArrayList<>(16);
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
