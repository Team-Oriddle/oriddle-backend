package com.tuk.oriddle.domain.user.entity

enum class Modifier(val value: String) {
    CURIOUS("호기심 많은"),
    PASSIONATE("열정적인"),
    CREATIVE("창의적인"),
    PURE("순수한"),
    EMOTIONAL("감성적인");

    companion object {
        fun getRandomModifier(): Modifier {
            return values().random()
        }
    }

}