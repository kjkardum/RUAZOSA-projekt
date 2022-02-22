package hr.fer.ruazosa.kviz2022.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.inject.Singleton
import javax.net.ssl.*

@Module
@InstallIn(SingletonComponent::class)
object NetworkServices {
    private const val BASE_URL = "https://ruazosaapiservice.azurewebsites.net/api/"

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return try {
            val trustAllCerts: Array<TrustManager> = arrayOf<TrustManager>(
                object : X509TrustManager {
                    override fun checkClientTrusted(p0: Array<out X509Certificate>?, p1: String?) { }
                    override fun checkServerTrusted(p0: Array<out X509Certificate>?, p1: String?) { }
                    override fun getAcceptedIssuers(): Array<X509Certificate> { return arrayOf() }
                }
            )
            val sslContext: SSLContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())
            val sslSocketFactory: SSLSocketFactory = sslContext.getSocketFactory()
            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier(object : HostnameVerifier {
                override fun verify(p0: String?, p1: SSLSession?): Boolean {
                    return true
                }
            })
            builder
                .addInterceptor(httpLoggingInterceptor)
                .build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideDemoApiService(retrofit: Retrofit): RemoteDemoApiService = retrofit.create(RemoteDemoApiService::class.java)

    @Singleton
    @Provides
    fun provideLoginService(retrofit: Retrofit): RemoteLoginService = retrofit.create(RemoteLoginService::class.java)

    @Singleton
    @Provides
    fun provideGameService(retrofit: Retrofit): RemoteGameService = retrofit.create(RemoteGameService::class.java)

    @Singleton
    @Provides
    fun provideFollowersService(retrofit: Retrofit): RemoteFollowersService = retrofit.create(RemoteFollowersService::class.java)
}
