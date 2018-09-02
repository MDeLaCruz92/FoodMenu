package com.mdelacruz.foodmenu.injection.module

import com.google.gson.GsonBuilder
import com.mdelacruz.foodmenu.data.api.FoodApi
import com.mdelacruz.foodmenu.util.NETWORK_TIMEOUT_SECONDS
import com.mdelacruz.foodmenu.util.BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Handle all required dependencies and define Retrofit API calls to the Server.
 */

@Module
object NetworkModule {

  @Provides
  @JvmStatic
  internal fun provideFoodApi(retrofit: Retrofit): FoodApi {
    return retrofit.create(FoodApi::class.java)
  }

  @Provides
  @JvmStatic
  internal fun provideRetrofitInterface(httpClient: OkHttpClient,
                               gsonConverterFactory: GsonConverterFactory): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(gsonConverterFactory)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
  }

  @Provides
  @JvmStatic
  internal fun provideGsonConverterFactory(): GsonConverterFactory {
    return GsonConverterFactory.create(GsonBuilder().create())
  }

  @Provides
  @JvmStatic
  internal fun provideHttpClient(): OkHttpClient {
    val okHttpClientBuilder = OkHttpClient.Builder()
    val interceptor = HttpLoggingInterceptor()

    interceptor.level = HttpLoggingInterceptor.Level.BODY

    okHttpClientBuilder.addInterceptor(interceptor)
    okHttpClientBuilder.connectTimeout(NETWORK_TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
        .readTimeout(NETWORK_TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)

    return okHttpClientBuilder.build()
  }
}