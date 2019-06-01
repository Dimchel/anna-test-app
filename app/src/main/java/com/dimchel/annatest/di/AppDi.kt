package com.dimchel.annatest.di

import android.app.Application
import com.dimchel.annatest.common.AppSchedulers
import com.dimchel.annatest.common.AppSchedulersImpl
import com.dimchel.annatest.data.repositories.rates.RatesRepository
import com.dimchel.annatest.data.repositories.rates.RatesRepositoryImpl
import com.dimchel.annatest.features.exchange.ExchangeFragment
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope

@AppScope
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    // ===========================================================
    // Injects
    // ===========================================================

    fun inject(fragment: ExchangeFragment)
}

@AppScope
@Module
abstract class AppModule {

    @AppScope
    @Binds
    abstract fun provideAppSchedulers(appSchedulers: AppSchedulersImpl): AppSchedulers

    @AppScope
    @Binds
    abstract fun provideRatesRepository(ratesRepository: RatesRepositoryImpl): RatesRepository

}