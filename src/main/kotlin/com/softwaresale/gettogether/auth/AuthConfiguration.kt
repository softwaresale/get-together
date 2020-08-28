package com.softwaresale.gettogether.auth

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(Auth0ConfigurationProperties::class)
class AuthConfiguration(
        private val auth0ConfigurationProperties: Auth0ConfigurationProperties
) {
    @Bean
    fun audienceValidator(): AudienceValidator {
        return AudienceValidator(auth0ConfigurationProperties.audience)
    }
}