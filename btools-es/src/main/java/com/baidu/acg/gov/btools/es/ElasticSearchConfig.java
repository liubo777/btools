package com.baidu.acg.gov.btools.es;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lb51
 */
@Configuration
@Slf4j
public class ElasticSearchConfig {
    @Value("${es.host.address}")
    private String esHostAddress;

    @Value("${es.host.port}")
    private Integer esHostPort;

    @Bean
    public ElasticsearchClient esClient(){
        RestClient restClient = RestClient.builder(new HttpHost(esHostAddress,esHostPort)).build();
        ElasticsearchTransport transport = new RestClientTransport(restClient,new JacksonJsonpMapper());
        ElasticsearchClient client = new ElasticsearchClient(transport);
        log.info("init ElasticsearchClient success ->",client);

        return client;
    }

}
