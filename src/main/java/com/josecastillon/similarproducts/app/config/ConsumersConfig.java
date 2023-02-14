package com.josecastillon.similarproducts.app.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import reactor.netty.http.client.HttpClient;

@Configuration
public class ConsumersConfig {

	@Value("${webclient.host.baseurl}")
	private String baseUrl;
    
    @Bean(name="webClientProducts")
    WebClient webClient() {    	
    	HttpClient client = HttpClient.create()
    			.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10_000)
    			.responseTimeout(Duration.ofMillis(90_000));
    	
    	return WebClient.builder()
    			  .baseUrl(baseUrl)
    			  .clientConnector(new ReactorClientHttpConnector(client))
    			  .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
    			  .build();
    }
	
}
