package com.softwaresale.gettogether.auth0

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.ClientHttpRequestFactory
import org.springframework.web.client.RestTemplate

@Configuration
@EnableConfigurationProperties(Auth0ConfigurationProperties::class)
class AuthConfiguration(
        private val auth0ConfigurationProperties: Auth0ConfigurationProperties
) {
    @Bean
    fun audienceValidator(): AudienceValidator {
        return AudienceValidator(auth0ConfigurationProperties.audience)
    }

    @Bean
    fun auth0UserInfoRestTemplate(): RestTemplate {
        val template = RestTemplate()
        template.interceptors.add(AccessTokenInterceptor())

        return template
    }
}