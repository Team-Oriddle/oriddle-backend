package com.tuk.oriddle.global.oauth

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import jakarta.servlet.http.HttpSession
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationSuccessHandler : AuthenticationSuccessHandler {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val session: HttpSession = request.session
        val redirectUrl = session.getAttribute("REDIRECT_URL_AFTER_LOGIN") as? String
        session.removeAttribute("REDIRECT_URL_AFTER_LOGIN")

        if (redirectUrl != null) {
            response.sendRedirect(redirectUrl)
        }
        else {
            response.sendRedirect("/")
        }
    }
}