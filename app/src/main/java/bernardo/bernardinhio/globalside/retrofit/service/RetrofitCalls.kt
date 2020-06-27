package bernardo.bernardinhio.globalside.retrofit.service

import bernardo.bernardinhio.globalside.dataprovider.GlobalSideDataProvider
import bernardo.bernardinhio.globalside.retrofit.model.ProfileDataModel
import bernardo.bernardinhio.globalside.retrofit.model.ProfileHealthPromptDataModel
import bernardo.bernardinhio.globalside.retrofit.model.ProfileTimelineEventDataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitCalls {

    @GET(GlobalSideDataProvider.ENDPOINT_LIST_PROFILES)
    fun getListProfilesCall(): Call<List<ProfileDataModel>>

    @GET(GlobalSideDataProvider.ENDPOINT_LIST_PROFILES.plus("{profile_id}/timeline-events"))
    fun getProfileTimelineEventsCalls(@Path(value = "profile_id", encoded = true) version: String): Call<List<ProfileTimelineEventDataModel>>

    @GET(GlobalSideDataProvider.ENDPOINT_LIST_PROFILES.plus("{profile_id}/health-prompts"))
    fun getProfileHealthPromptsCall(@Path(value = "profile_id", encoded = true) version: String): Call<List<ProfileHealthPromptDataModel>>

}