package com.tuk.oriddle.global.oauth

import com.tuk.oriddle.domain.user.service.UserQueryService
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class CustomOAuth2UserService(private val userQueryService: UserQueryService) : DefaultOAuth2UserService() {
    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val oauth2User = super.loadUser(userRequest)

        val email = oauth2User.attributes["email"] as String
        val user = userQueryService.findOrCreateUserByEmail(email)

        val authorities = listOf(SimpleGrantedAuthority("ROLE_USER"))
        val attributes = mapOf("userId" to user.id)

        return DefaultOAuth2User(
            authorities,
            attributes,
            "userId"
        )
    }
}