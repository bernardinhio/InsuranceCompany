package bernardo.bernardinhio.globalside.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import bernardo.bernardinhio.globalside.R
import bernardo.bernardinhio.globalside.dataprovider.GlobalSideDataProvider
import bernardo.bernardinhio.globalside.retrofit.model.ProfileDataModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindToDataProviderListOfProfiles()

        GlobalSideDataProvider.getProfiles()
    }

    private fun bindToDataProviderListOfProfiles(){

        GlobalScope.launch {

            val responseListProfilesData: Pair<String, List<ProfileDataModel>?> = GlobalSideDataProvider.channelListProfiles.receive()

            runOnUiThread{

                Toast.makeText(this@MainActivity, responseListProfilesData.first, Toast.LENGTH_LONG).show()

                responseListProfilesData.second?.forEach { profile ->

                    profile.profile_id?.let {
                        Toast.makeText(this@MainActivity, it, Toast.LENGTH_LONG).show()

                        GlobalSideDataProvider.getProfileHealthPrompts(profile.profile_id!!)
                    }

                }
            }

        }

    }


}