package com.tuk.oriddle.global.config

import com.tuk.oriddle.global.oauth.CustomOAuth2UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.HttpStatusEntryPoint

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
class SecurityConfig(
    private val customOAuth2UserService: CustomOAuth2UserService
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .cors(Customizer.withDefaults())
            .csrf {  csrfConfig: CsrfConfigurer<HttpSecurity> -> csrfConfig.disable() }
            .exceptionHandling { exceptions ->
            exceptions.authenticationEntryPoint(HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
        }.logout { logout ->
            logout.logoutSuccessUrl("/")
        }.oauth2Login { oauth2Login ->
            oauth2Login
                .userInfoEndpoint { userInfoEndpoint ->
                    userInfoEndpoint.userService(customOAuth2UserService)
                }
        }
        return http.build()
    }
}