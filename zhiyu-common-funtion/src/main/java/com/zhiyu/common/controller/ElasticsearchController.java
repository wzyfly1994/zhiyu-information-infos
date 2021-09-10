package com.zhiyu.common.controller;

import com.zhiyu.common.entity.dto.elasticsearch.IndexDocDto;
import com.zhiyu.common.entity.dto.elasticsearch.IndexDto;
import com.zhiyu.common.entity.dto.elasticsearch.SearchDocDto;
import com.zhiyu.common.service.ElasticsearchService;
import com.zhiyu.common.utils.response.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wengzhiyu
 * @since 2021/8/31 16:25
 */
@RestController
@RequestMapping("/es")
@Slf4j
@Api(tags = "Elasticsearch功能接口")
public class ElasticsearchController {
    private final ElasticsearchService esService;

    public ElasticsearchController(ElasticsearchService esService) {
        this.esService = esService;
    }


    @PostMapping(value = "/index")
    @ApiOperation(value = "创建索引")
    public ResponseData createIndex(@RequestBody IndexDto indexDto) {
        return esService.createIndex(indexDto.getIndex(), indexDto.getSettings(), indexDto.getMappings());
    }

    @DeleteMapping(value = "/index/{indexName}")
    @ApiOperation(value = "删除索引")
    public ResponseData deleteIndex(@PathVariable String indexName) {
        return esService.deleteIndex(indexName);
    }

    @PostMapping(value = "/doc")
    @ApiOperation(value = "添加文档")
    public ResponseData createIndexDoc(@RequestBody IndexDocDto indexDocDto) {
        return esService.createIndexDoc(indexDocDto);
    }


    @PutMapping(value = "/doc")
    @ApiOperation(value = "更新文档")
    public ResponseData updateIndexDoc(@RequestBody IndexDocDto indexDocDto) {
        return esService.updateIndexDoc(indexDocDto);
    }

    @DeleteMapping(value = "/doc/{index}/{id}")
    @ApiOperation(value = "删除文档")
    public ResponseData deleteIndexDoc(@PathVariable String index, @PathVariable String id) {
        return esService.deleteIndexDoc(index, id);
    }

    @PostMapping(value = "/docList")
    @ApiOperation(value = "批量添加文档")
    public ResponseData bulkCreateIndexDoc(@RequestBody List<IndexDocDto> indexDocDtoList) {
        return esService.bulkInsert(indexDocDtoList);
    }

    @PostMapping(value = "/search")
    @ApiOperation(value = "搜索")
    public ResponseData search(@RequestBody SearchDocDto searchDocDto) {
        return esService.allSearch(searchDocDto);
    }

}
