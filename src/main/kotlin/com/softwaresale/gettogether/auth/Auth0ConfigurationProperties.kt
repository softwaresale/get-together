package com.softwaresale.gettogether.auth

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties("auth0")
data class Auth0ConfigurationProperties @ConstructorBinding constructor(
        val audience: String,
        val issuer: String
)