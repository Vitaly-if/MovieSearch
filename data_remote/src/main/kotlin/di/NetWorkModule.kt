package di

import api.ApiFactory
import api.ApiService
import org.koin.dsl.module

val NetworkModule = module {
    single<ApiService> {
       ApiFactory.apiService
    }
}