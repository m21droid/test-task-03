package com.m21droid.booknet.domain.usecases

interface BaseUseCase<In, Out> {

    fun execute(input: In): Out

}