package com.zhiyu.common.service;

import com.zhiyu.common.entity.dto.elasticsearch.IndexDocDto;
import com.zhiyu.common.entity.dto.elasticsearch.SearchDocDto;
import com.zhiyu.common.utils.response.ResponseData;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.search.SearchHit;

import java.util.List;

/**
 * @author wengzhiyu
 * @since 2021/8/31 15:34
 */
public interface ElasticsearchService {

    /**
     * 创建索引
     *
     * @param index
     * @param settings
     * @param mappings
     * @return
     */
    ResponseData createIndex(String index, String settings, String mappings);

    /**
     * 添加setting配置
     *
     * @param createIndexRequest
     * @param setting
     * @return
     */
    void buildIndexSetting(CreateIndexRequest createIndexRequest, String setting);

    /**
     * 添加mapping配置
     *
     * @param createIndexRequest
     * @param mapping
     * @return
     */
    void buildIndexMapping(CreateIndexRequest createIndexRequest, String mapping);

    /**
     * 删除索引
     *
     * @param indexName 索引名称
     * @return
     */
    ResponseData deleteIndex(String indexName);

    /**
     * 添加索引文档
     *
     * @param indexDocDto
     * @return
     */
    ResponseData createIndexDoc(IndexDocDto indexDocDto);


    /**
     * 索引更新文档
     *
     * @param indexDocDto
     * @return
     */
    ResponseData updateIndexDoc(IndexDocDto indexDocDto);


    /**
     * 删除文档
     * @param index
     * @param id
     * @return
     */
    ResponseData  deleteIndexDoc(String index, String id);


    /**
     * 批量添加
     *
     * @param indexDocDtoList
     * @return
     */
    ResponseData bulkInsert(List<IndexDocDto> indexDocDtoList);


    /**
     * 全文检索
     *
     * @param searchDocDto
     * @return
     */
    ResponseData allSearch(SearchDocDto searchDocDto);

    /**
     * 获取索引所有设置的字段
     *
     * @param index
     * @return
     */
    String[] getAllFieldNames(String index);

    /**
     * 获取索引中需要全文检索的字段
     *
     * @param index
     * @return
     */
    String[] getQueryFieldNames(String index);

    /**
     * 获取搜索结果并是否高亮
     *
     * @param searchHits
     * @param isHighlight
     * @return
     */
    List getSearchList(SearchHit[] searchHits, Boolean isHighlight);
}
