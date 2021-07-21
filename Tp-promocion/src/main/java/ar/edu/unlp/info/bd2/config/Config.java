package ar.edu.unlp.info.bd2.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import ar.edu.unlp.info.bd2.services.SpringDataMLService;

@Configuration
@EnableElasticsearchRepositories(basePackages = "ar.edu.unlp.info.bd2.repositories")
public class Config {


    @Bean
    public RestHighLevelClient client() {
    			ClientConfiguration config = ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .build();

        return RestClients.create(config).rest();
    }
    
    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
    	return new ElasticsearchRestTemplate(client());
    }
    

}
