package com.dimchel.annatest.di

import com.dimchel.annatest.data.api.AnnaApiProvider
import com.dimchel.annatest.data.api.AnnaApiProviderImpl
import com.dimchel.annatest.data.api.AnnaService
import com.dimchel.annatest.data.api.ApiConstants
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.simpleframework.xml.convert.AnnotationStrategy
import org.simpleframework.xml.core.Persister
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

@AppScope
@Module
abstract class NetworkModule {

    @AppScope
    @Module
    companion object {

        @JvmStatic
        @AppScope
        @Provides
        fun provideRetrofit(): Retrofit {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            return Retrofit.Builder()
                .baseUrl(ApiConstants.API_DOMAIN)
                .client(client)
                .addConverterFactory(
                    SimpleXmlConverterFactory.createNonStrict(
                    Persister(AnnotationStrategy())
                ))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }

        @JvmStatic
        @AppScope
        @Provides
        fun provideAnnaService(retrofit: Retrofit): AnnaService =
            retrofit.create(AnnaService::class.java)
    }

    @AppScope
    @Binds
    abstract fun provideAnnaApiProvider(annaApiProvider: AnnaApiProviderImpl): AnnaApiProvider

}