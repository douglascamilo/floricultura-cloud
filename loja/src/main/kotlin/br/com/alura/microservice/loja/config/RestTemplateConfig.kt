package br.com.alura.microservice.loja.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import org.springframework.context.annotation.Bean
import org.springframework.cloud.client.loadbalancer.LoadBalanced

@Configuration
open class RestTemplateConfig {
	
	@Bean
	@LoadBalanced
	open fun createRestTemplate(): RestTemplate {
		return RestTemplate()
	}
}