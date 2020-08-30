package com.softwaresale.gettogether.auth0

import org.springframework.http.HttpRequest
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.core.OAuth2AccessToken
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken

class AccessTokenInterceptor : ClientHttpRequestInterceptor {
    override fun intercept(request: HttpRequest, body: ByteArray, execution: ClientHttpRequestExecution): ClientHttpResponse {
        val accessToken = SecurityContextHolder.getContext().authentication as JwtAuthenticationToken
        request.headers.add("Authorization", "Bearer ${accessToken.token.tokenValue}")
        return execution.execute(request, body)
    }
}