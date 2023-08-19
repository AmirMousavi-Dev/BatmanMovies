package ir.codroid.batmanmovies.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.codroid.batmanmovies.data.remote.ApiService
import ir.codroid.batmanmovies.util.Constants.BASE_URL
import ir.codroid.batmanmovies.util.Constants.TIMEOUT_IN_SECOND
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    internal fun interceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }


    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(TIMEOUT_IN_SECOND, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_IN_SECOND, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT_IN_SECOND, TimeUnit.SECONDS)
        .addInterceptor(interceptor())
        .build()




    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)
}