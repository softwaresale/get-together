package com.softwaresale.gettogether.user

import com.fasterxml.jackson.databind.ObjectMapper
import com.nimbusds.jwt.JWTParser
import com.softwaresale.gettogether.auth0.Auth0Service
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.core.OAuth2AccessToken
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.stereotype.Service

@Service
class UserService(
        private val userRepository: UserRepository,
        private val auth0Service: Auth0Service
) {
    private fun getAccessToken(): Jwt {
        val accessToken = SecurityContextHolder.getContext().authentication as JwtAuthenticationToken
        return accessToken.token
    }

    fun getById(id: String): User? {
        val opt = userRepository.findById(id)
        return if(opt.isPresent) opt.get() else null
    }

    fun getCurrentUser(): User? {
        val token = this.getAccessToken()
        return this.getById(token.subject) // Subject is userId
    }

    fun createUserFromCurrent(): User? {
        val userInfo = this.auth0Service.getUserInfo()
        return userInfo?.let {
            val newUser = User.fromAuth0UserInfo(it)
            this.userRepository.save(newUser)
        }
    }
}