package com.trendsoftware.usermanagementapp.domain.validation

sealed class ValidationError {

    data object EmptyName : ValidationError()

    data object InvalidAge : ValidationError()

    data object EmptyJobTitle : ValidationError()

    data object EmptyGender : ValidationError()
}