package com.baidu.acg.gov.btools.es.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.Refresh;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lb51
 */
@Component
@Slf4j
public class ElasticSearchClientAdapter {
    @Autowired
    private ElasticsearchClient esClient;

    public void createIndex(Map record,String index){
        try {
            IndexRequest<Map> indexRequest = IndexRequest.of(b -> b
                    .index(index)
                    .document(record)
                    .refresh(Refresh.True));
            esClient.index(indexRequest);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public List searchAllByIndex(String index){
        try {
            SearchRequest searchRequest = SearchRequest.of(s -> s
                    .index(index)
                    .query(q -> q
                            .bool( b-> b
                                    .must(m -> m
                                            .term(t -> t
                                                    .field("name").value(FieldValue.of("jj")))))));
            SearchResponse<Map> search = esClient.search(searchRequest, Map.class);
            return search.hits().hits().stream().map(Hit::source).collect(Collectors.toList());
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
