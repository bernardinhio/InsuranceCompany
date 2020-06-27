package bernardo.bernardinhio.globalside.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import bernardo.bernardinhio.globalside.R
import bernardo.bernardinhio.globalside.dataprovider.GlobalSideDataProvider
import bernardo.bernardinhio.globalside.dataprovider.LOG_TAG
import bernardo.bernardinhio.globalside.retrofit.model.ProfileDataModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindDataFromCoroutines()

        GlobalSideDataProvider.getProfiles()
    }

    private fun bindDataFromCoroutines(){
        GlobalScope.launch {

            val listProfilesData: List<ProfileDataModel>? = GlobalSideDataProvider.channelListProfilesData.receive()

            listProfilesData?.forEach { profile ->
                Log.d(LOG_TAG, profile.toString())

                runOnUiThread{

                }

            }
        }

    }


}