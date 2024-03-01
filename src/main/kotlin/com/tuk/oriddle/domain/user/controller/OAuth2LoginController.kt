package com.tuk.oriddle.domain.user.controller

import jakarta.servlet.http.HttpSession
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Controller
@RequestMapping("/api/v1/login")
class OAuth2LoginController(
    @Value("\${frontend.base-url}") private val frontendBaseUrl: String
) {
    @GetMapping("/{provider}")
    fun startOAuth2Login(
        session: HttpSession,
        @RequestParam("redirectEndPoint") encodedRedirectEndPoint: String?,
        @PathVariable("provider") provider: String
    ): String {
        if (encodedRedirectEndPoint != null) {
            val redirectUrl = frontendBaseUrl + URLDecoder.decode(
                encodedRedirectEndPoint,
                StandardCharsets.UTF_8.name()
            )
            session.setAttribute("REDIRECT_URL_AFTER_LOGIN", redirectUrl)
        }
        return "redirect:/oauth2/authorization/$provider"
    }
}