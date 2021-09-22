package co.estudents.data

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

interface WebService {

    @Headers("Content-Type: application/json")
    @GET("/estudiantes")
    suspend fun getEstudents(): List<Estudent>
}

object RetrofitClient {

    val estudentService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }
}

const val BASE_URL = "https://private-83598-estudiantes.apiary-mock.com"

