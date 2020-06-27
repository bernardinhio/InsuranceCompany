package bernardo.bernardinhio.globalside.retrofit.service

import bernardo.bernardinhio.globalside.dataprovider.GlobalSideDataProvider
import bernardo.bernardinhio.globalside.retrofit.model.ProfileDataModel
import bernardo.bernardinhio.globalside.retrofit.model.ProfileHealthPromptDataModel
import bernardo.bernardinhio.globalside.retrofit.model.ProfileTimelineEventDataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitCalls {

    // example full url: https://freemium.ottonova.de/api/user/customer/profiles
    @GET(GlobalSideDataProvider.ENDPOINT_LIST_PROFILES)
    fun getListProfilesCall(): Call<List<ProfileDataModel>>

    // example full url: https://freemium.ottonova.de/api/user/customer/profiles/freemium_profile/timeline-events
    @GET(GlobalSideDataProvider.ENDPOINT_LIST_PROFILES.plus("/{profile_id}/timeline-events"))
    fun getProfileTimelineEventsCalls(@Path(value = "profile_id", encoded = true) version: String): Call<List<ProfileTimelineEventDataModel>>

    // example full url: https://freemium.ottonova.de/api/user/customer/profiles/freemium_profile/health-prompts
    @GET(GlobalSideDataProvider.ENDPOINT_LIST_PROFILES.plus("/{profile_id}/health-prompts"))
    fun getProfileHealthPromptsCall(@Path(value = "profile_id", encoded = true) version: String): Call<List<ProfileHealthPromptDataModel>>

}