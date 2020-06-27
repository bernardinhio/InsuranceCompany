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

    val channelListProfiles: Channel<Pair<String, List<ProfileDataModel>?>> = Channel<Pair<String, List<ProfileDataModel>?>>()
    val channelListProfileTimelineEvents: Channel<Pair<String, List<ProfileTimelineEventDataModel>?>> = Channel<Pair<String, List<ProfileTimelineEventDataModel>?>>()
    val channelListProfileHealthPrompt: Channel<Pair<String, List<ProfileHealthPromptDataModel>?>> = Channel<Pair<String, List<ProfileHealthPromptDataModel>?>>()

    fun getProfiles(){

        val listOfProfilesCall = RetrofitInstance
            .setupRetrofitCalls()
            .getListProfilesCall()

        listOfProfilesCall.enqueue(object : Callback<List<ProfileDataModel>> {

            override fun onResponse(
                call: Call<List<ProfileDataModel>>?,
                response: Response<List<ProfileDataModel>>?
            ) {

                if (response != null && response.isSuccessful && response.code() == HttpURLConnection.HTTP_OK) {
                    initializeListOfProfiles(BackendStatus.SUCCESS.message, response.body())
                } else {
                    initializeListOfProfiles(BackendStatus.SOMETHING_WRONG_CODE_200.message, null)
                }
            }

            override fun onFailure(call: Call<List<ProfileDataModel>>?, error: Throwable?) {

                when (error) {

                    is SocketTimeoutException ->
                        initializeListOfProfiles(BackendStatus.ERROR_CONNECTION_TIMEOUT.message, null)

                    is UnknownHostException ->
                        initializeListOfProfiles(BackendStatus.ERROR_NO_INTERNET.message, null)

                    is ConnectException ->
                        initializeListOfProfiles(BackendStatus.ERROR_SERVER_NOT_RESPONDING.message, null)

                    is JSONException ->
                        initializeListOfProfiles(BackendStatus.ERROR_PARSING.message, null)

                    is JsonSyntaxException ->
                        initializeListOfProfiles(BackendStatus.ERROR_PARSING_SYNTAX.message, null)

                    is IOException ->
                        initializeListOfProfiles(BackendStatus.ERROR_IO_EXCEPTION.message.plus(error.cause?.message), null)
                }
            }

        })

    }

    private fun initializeListOfProfiles(message: String, body: List<ProfileDataModel>?) {
        Log.d(LOG_TAG, message)
        GlobalScope.launch {
            channelListProfiles.send(Pair(message, body))
        }
    }



    fun getProfileTimelineEvents(profileId : String) {

        val listOfprofileTimelineEventsCalls = RetrofitInstance
            .setupRetrofitCalls()
            .getProfileTimelineEventsCalls(profileId)

        listOfprofileTimelineEventsCalls.enqueue(object : Callback<List<ProfileTimelineEventDataModel>> {

            override fun onResponse(
                call: Call<List<ProfileTimelineEventDataModel>>?,
                response: Response<List<ProfileTimelineEventDataModel>>?
            ) {

                if (response != null && response.isSuccessful && response.code() == HttpURLConnection.HTTP_OK) {
                    initializeListOfProfileTimelineEvents(BackendStatus.SUCCESS.message, response.body())
                } else {
                    initializeListOfProfileTimelineEvents(BackendStatus.SOMETHING_WRONG_CODE_200.message, null)
                }
            }

            override fun onFailure(
                call: Call<List<ProfileTimelineEventDataModel>>?,
                error: Throwable?
            ) {

                when (error) {

                    is SocketTimeoutException ->
                        initializeListOfProfileTimelineEvents(BackendStatus.ERROR_CONNECTION_TIMEOUT.message, null)

                    is UnknownHostException ->
                        initializeListOfProfileTimelineEvents(BackendStatus.ERROR_NO_INTERNET.message, null)

                    is ConnectException ->
                        initializeListOfProfileTimelineEvents(BackendStatus.ERROR_SERVER_NOT_RESPONDING.message, null)

                    is JSONException ->
                        initializeListOfProfileTimelineEvents(BackendStatus.ERROR_PARSING.message, null)

                    is JsonSyntaxException ->
                        initializeListOfProfileTimelineEvents(BackendStatus.ERROR_PARSING_SYNTAX.message, null)

                    is IOException ->
                        initializeListOfProfileTimelineEvents(BackendStatus.ERROR_IO_EXCEPTION.message.plus(error.cause?.message), null)
                }
            }

        })
    }

    private fun initializeListOfProfileTimelineEvents(message: String, body: List<ProfileTimelineEventDataModel>?) {
        Log.d(LOG_TAG, message)
        GlobalScope.launch {
            channelListProfileTimelineEvents.send(Pair(message, body))
        }
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

                if (response != null && response.isSuccessful && response.code() == HttpURLConnection.HTTP_OK) {
                    initializeListOfProfileHealthPrompts(BackendStatus.SUCCESS.message, response.body())
                } else {
                    initializeListOfProfileHealthPrompts(BackendStatus.SOMETHING_WRONG_CODE_200.message, null)
                }
            }

            override fun onFailure(call: Call<List<ProfileHealthPromptDataModel>>?, error: Throwable?) {
                when (error) {

                    is SocketTimeoutException ->
                        initializeListOfProfileHealthPrompts(BackendStatus.ERROR_CONNECTION_TIMEOUT.message, null)

                    is UnknownHostException ->
                        initializeListOfProfileHealthPrompts(BackendStatus.ERROR_NO_INTERNET.message, null)

                    is ConnectException ->
                        initializeListOfProfileHealthPrompts(BackendStatus.ERROR_SERVER_NOT_RESPONDING.message, null)

                    is JSONException ->
                        initializeListOfProfileHealthPrompts(BackendStatus.ERROR_PARSING.message, null)

                    is JsonSyntaxException ->
                        initializeListOfProfileHealthPrompts(BackendStatus.ERROR_PARSING_SYNTAX.message, null)

                    is IOException ->
                        initializeListOfProfileHealthPrompts(BackendStatus.ERROR_IO_EXCEPTION.message.plus(error.cause?.message), null)
                }
            }
        })
    }

    private fun initializeListOfProfileHealthPrompts(message: String, body: List<ProfileHealthPromptDataModel>?) {
        Log.d(LOG_TAG, message)
        GlobalScope.launch {
            channelListProfileHealthPrompt.send(Pair(message, body))
        }
    }

    enum class BackendStatus(val message: String) {
        SUCCESS("Request Successful"),
        SOMETHING_WRONG_CODE_200("Something wrong: request not succeeded"),
        ERROR_CONNECTION_TIMEOUT("Error: Connection timeout"),
        ERROR_NO_INTERNET("Error: No Internet"),
        ERROR_SERVER_NOT_RESPONDING("Error: Server not responding"),
        ERROR_PARSING("Error: Parse error"),
        ERROR_PARSING_SYNTAX("Error: Parse error Syntax"),
        ERROR_IO_EXCEPTION("Error: throwable: "),
        REQUEST_NOT_MADE_YET("Request not made yet")
    }

}