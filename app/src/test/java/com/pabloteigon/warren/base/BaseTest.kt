package com.pabloteigon.warren.base

import org.junit.After
import org.junit.Before
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest

abstract class BaseTest : KoinTest {

    @Before
    open fun setUp() {

    }


    @After
    open fun tearDown() {
        //Stop Koin as well
        stopKoin()
    }
}