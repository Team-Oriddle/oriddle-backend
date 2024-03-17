package com.tuk.oriddle.global.resolver

import com.tuk.oriddle.global.annotation.LoginUser
import com.tuk.oriddle.global.error.ErrorCode
import org.springframework.core.MethodParameter
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class LoginUserArgumentResolver : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        val hasLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser::class.java) != null
        val isLongType = Long::class.java.isAssignableFrom(parameter.parameterType)
        return hasLoginUserAnnotation && isLongType
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        val authentication = SecurityContextHolder.getContext().authentication
        val principal = authentication.principal

        if (principal is OAuth2User) {
            return principal.attributes["userId"] as Long
        }

        throw AccessDeniedException(ErrorCode.ACCESS_DENIED.message)
    }
}
