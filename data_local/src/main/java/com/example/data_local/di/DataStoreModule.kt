package com.example.data_local.di

import com.example.data_local.DataStoreManager
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val DataStoreModule  = module {
    singleOf(::DataStoreManager)
}