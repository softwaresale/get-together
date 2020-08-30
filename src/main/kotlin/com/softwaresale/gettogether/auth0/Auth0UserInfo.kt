package com.softwaresale.gettogether.auth0

data class Auth0UserInfo(
        val name: String,
        val nickname: String,
        val sub: String,
        val picture: String
)