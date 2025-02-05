package com.example.composesignup.feature.onboard.domain.usecase

data class FormValidation(
    val success:Boolean,
    val exception: Exception?=null
)