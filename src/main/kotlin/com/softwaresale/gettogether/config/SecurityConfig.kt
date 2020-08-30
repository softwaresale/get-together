package com.softwaresale.gettogether.config

import com.softwaresale.gettogether.auth0.AudienceValidator
import com.softwaresale.gettogether.auth0.Auth0ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator
import org.springframework.security.oauth2.core.OAuth2TokenValidator
import org.springframework.security.oauth2.jwt.*

@EnableWebSecurity
class SecurityConfig(
        private val auth0ConfigurationProperties: Auth0ConfigurationProperties
) : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity?) {
        http
                ?.oauth2ResourceServer {
                    it.jwt()
                }
                ?.authorizeRequests {
                    it.mvcMatchers("/api/**").authenticated()
                    it.mvcMatchers("/**").permitAll() // Allow access to all other routes that aren't api scoped
                }
    }

    @Bean
    fun jwtDecoder(audienceValidator: AudienceValidator): JwtDecoder {
        // Not worried about unsafe cast
        val decoder: NimbusJwtDecoder = JwtDecoders.fromIssuerLocation(auth0ConfigurationProperties.issuer) as NimbusJwtDecoder
        val withIssuer: OAuth2TokenValidator<Jwt> = JwtValidators.createDefaultWithIssuer(auth0ConfigurationProperties.issuer)
        val delegation: OAuth2TokenValidator<Jwt> = DelegatingOAuth2TokenValidator(withIssuer, audienceValidator)

        decoder.setJwtValidator(delegation)

        return decoder
    }
}