package bernardo.bernardinhio.globalside.dataprovider

import android.util.Log
import bernardo.bernardinhio.globalside.retrofit.model.ProfileDataModel
import bernardo.bernardinhio.globalside.retrofit.model.ProfileHealthPromptDataModel
import bernardo.bernardinhio.globalside.retrofit.model.ProfileTimelineEventDataModel
import bernardo.bernardinhio.globalside.retrofit.service.RetrofitInstance
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException

const val LOG_TAG = "RetrofitMessage"

object GlobalSideDataProvider {

    const val BASE_URL_LOGIN = "https://freemium.ottonova.de/api/"
    const val ENDPOINT_LIST_PROFILES = "user/customer/profiles"

    val channelListProfilesData: Channel<List<ProfileDataModel>?> = Channel<List<ProfileDataModel>?>()
    val channelProfileTimelineEventsData: Channel<List<ProfileTimelineEventDataModel>?> = Channel<List<ProfileTimelineEventDataModel>?>()
    val channelProfileHealthPromptData: Channel<List<ProfileHealthPromptDataModel>?> = Channel<List<ProfileHealthPromptDataModel>?>()

    fun getProfiles(){

        val listProfilesCall = RetrofitInstance
            .setupRetrofitCalls()
            .getListProfilesCall()

        listProfilesCall.enqueue(object : Callback<List<ProfileDataModel>> {

            override fun onResponse(
                call: Call<List<ProfileDataModel>>?,
                response: Response<List<ProfileDataModel>>?
            ) {

                if (response != null && response.isSuccessful && response.code() == HttpURLConnection.HTTP_OK) {

                    Log.d(LOG_TAG, BackendStatus.SUCCESS.message)

                    val listOfProfilesData: List<ProfileDataModel>? = response.body()

                    listOfProfilesData?.forEach { profile ->
                        Log.d(LOG_TAG, profile.toString())
                    }

                    GlobalScope.launch {
                        channelListProfilesData.send(listOfProfilesData)
                    }

                    //initializeDataProviderSuccess(response.body())

                } else {
                    Log.d(LOG_TAG, BackendStatus.SOMETHING_WRONG_CODE_200.message)
                    //initializeDataProviderFailure(BackendStatus.SOMETHING_WRONG_CODE_200)
                }

            }

            override fun onFailure(call: Call<List<ProfileDataModel>>?, error: Throwable?) {

                when (error) {

                    is SocketTimeoutException -> {
                        Log.d(LOG_TAG, BackendStatus.ERROR_CONNECTION_TIMEOUT.message)
                        //initializeDataProviderFailure(BackendStatus.ERROR_CONNECTION_TIMEOUT)
                    }

                    is UnknownHostException -> {
                        Log.d(LOG_TAG, BackendStatus.ERROR_NO_INTERNET.message)
                        //initializeDataProviderFailure(BackendStatus.ERROR_NO_INTERNET)
                    }

                    is ConnectException -> {
                        Log.d(LOG_TAG, BackendStatus.ERROR_SERVER_NOT_RESPONDING.message)
                        //initializeDataProviderFailure(BackendStatus.ERROR_SERVER_NOT_RESPONDING)
                    }

                    is JSONException -> {
                        Log.d(LOG_TAG, BackendStatus.ERROR_PARSING.message)
                        //initializeDataProviderFailure(BackendStatus.ERROR_PARSING)
                    }

                    is JsonSyntaxException -> {
                        Log.d(LOG_TAG, BackendStatus.ERROR_PARSING_SYNTAX.message)
                        //initializeDataProviderFailure(BackendStatus.ERROR_PARSING_SYNTAX)
                    }

                    is IOException -> {
                        Log.d(LOG_TAG, BackendStatus.ERROR_OTHER.message)
                        //initializeDataProviderFailure(BackendStatus.ERROR_OTHER)
                    }
                }

            }

        })

    }

    fun getProfileTimelineEvents(profileId : String) {

        val profileTimelineEventsCalls = RetrofitInstance
            .setupRetrofitCalls()
            .getProfileTimelineEventsCalls(profileId)

        profileTimelineEventsCalls.enqueue(object : Callback<List<ProfileTimelineEventDataModel>> {

            override fun onResponse(
                call: Call<List<ProfileTimelineEventDataModel>>?,
                response: Response<List<ProfileTimelineEventDataModel>>?
            ) {
                TODO("Not yet implemented")
            }

            override fun onFailure(
                call: Call<List<ProfileTimelineEventDataModel>>?,
                t: Throwable?
            ) {
                TODO("Not yet implemented")
            }

        })
    }

    fun getProfileHealthPrompts(profileId : String) {

        val profileHealthPromptsCall = RetrofitInstance
            .setupRetrofitCalls()
            .getProfileHealthPromptsCall(profileId)

        profileHealthPromptsCall.enqueue(object : Callback<List<ProfileHealthPromptDataModel>> {

            override fun onResponse(
                call: Call<List<ProfileHealthPromptDataModel>>?,
                response: Response<List<ProfileHealthPromptDataModel>>?
            ) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<List<ProfileHealthPromptDataModel>>?, t: Throwable?) {
                TODO("Not yet implemented")
            }

        })


    }


    enum class BackendStatus(val message: String) {
        SUCCESS("Request Successful"),
        SOMETHING_WRONG_CODE_200("Something wrong: request not succeeded"),
        ERROR_CONNECTION_TIMEOUT("Error: Connection timeout"),
        ERROR_NO_INTERNET("Error: No Internet"),
        ERROR_SERVER_NOT_RESPONDING("Error: Server not responding"),
        ERROR_PARSING("Error: Parse error"),
        ERROR_PARSING_SYNTAX("Error: Parse error Syntax"),
        ERROR_OTHER("Error: throwable error"),
        ERROR_WRONG_LOGIN_CODE("Error: Wrong Login code"),
        REQUEST_NOT_MADE_YET("Request not made yet")
    }

}