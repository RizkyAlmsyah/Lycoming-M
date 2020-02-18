package gmf.rizky.lycomingm.data.network

import gmf.rizky.lycomingm.data.network.responses.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface ApiService {

    @FormUrlEncoded
    @POST("login")
    suspend fun managementLogin(
        @Field("management_user_name") management_user_name: String,
        @Field("password") password: String
    ) : Response<ManagementResponse>

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @PUT("progress-job/{id}/note")
    suspend fun updateNote(
        @Header("authorization") auth: String,
        @Path("id") id: Int,
        @Field("progress_job_note") progress_job_note: String
    ) : Response<ProgressJobNoteResponse>

    @Headers("Accept: application/json")
    @GET("job/done")
    suspend fun getJobDone(@Header("authorization") auth: String): Response<EngineDoneResponse>

    @Headers("Accept: application/json")
    @GET("job/{id}")
    suspend fun getJob(@Header("authorization") auth: String, @Path("id") id: Int): Response<JobResponse>

    @Headers("Accept: application/json")
    @GET("job/progress")
    suspend fun getJobProgress(@Header("authorization") auth: String): Response<EngineProgressResponse>

    @Headers("Accept: application/json")
    @GET("job/{id}/progress")
    suspend fun getJobStatusProgress(@Header("authorization") auth: String, @Path("id") id: Int): Response<JobStatusProgressResponse>

    companion object{
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ) : ApiService{
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.HEADERS
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(50, TimeUnit.SECONDS)
                .readTimeout(50, TimeUnit.SECONDS)
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl("https://lycoming-gmf.herokuapp.com/api/management-side/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(ApiService::class.java)
        }
    }
}