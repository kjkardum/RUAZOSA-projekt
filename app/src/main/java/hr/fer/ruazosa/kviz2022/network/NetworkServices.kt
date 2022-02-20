package hr.fer.ruazosa.kviz2022.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkServices {
    private const val BASE_URL = "https://localhost:5001/api/" //"https://ruazosaapiservice.azurewebsites.net/api/"

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    //val gameService = retrofit.create(RemoteGameService::class.java)

    @Singleton
    @Provides
    fun provideDemoApiService(retrofit: Retrofit): RemoteDemoApiService = retrofit.create(RemoteDemoApiService::class.java)

    @Singleton
    @Provides
    fun provideLoginService(retrofit: Retrofit): RemoteLoginService = retrofit.create(RemoteLoginService::class.java)
}
