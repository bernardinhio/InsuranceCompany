package bernardo.bernardinhio.globalside.retrofit.service

import bernardo.bernardinhio.globalside.dataprovider.GlobalSideDataProvider
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    fun setupRetrofitCalls(): RetrofitCalls {
        return Retrofit.Builder()
            .baseUrl(GlobalSideDataProvider.BASE_URL_LOGIN)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitCalls::class.java)
    }
}