package com.softwaresale.gettogether.auth0

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class Auth0Service(
        private val userInfoRestTemplate: RestTemplate,
        private val auth0ConfigurationProperties: Auth0ConfigurationProperties
) {
    fun getUserInfo(): Auth0UserInfo? {
        val response = this.userInfoRestTemplate.getForEntity(auth0ConfigurationProperties.userinfoEndpoint, Auth0UserInfo::class.java)
        return if (response.statusCode == HttpStatus.OK) response.body else null
    }
}