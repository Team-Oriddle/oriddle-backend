package com.tuk.oriddle.domain.user.entity

enum class Modifier(val value: String) {
    CAREFUL("신중한"),
    CREATIVE("창의적인"),
    CURIOUS("호기심 많은"),
    EMOTIONAL("감성적인"),
    FREE("자유로운"),
    JOLLY("유쾌한"),
    PASSIONATE("열정적인"),
    PURE("순수한"),
    TOUGH("강인한"),
    VERSATILE("다재다능한")
    ;

    companion object {
        fun getRandomModifier(): Modifier {
            return values().random()
        }
    }
}