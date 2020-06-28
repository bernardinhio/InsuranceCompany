package bernardo.bernardinhio.globalside.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import bernardo.bernardinhio.globalside.R
import bernardo.bernardinhio.globalside.adapter.ProfilesAdapter
import bernardo.bernardinhio.globalside.dataprovider.GlobalSideDataProvider
import bernardo.bernardinhio.globalside.model.Profile
import bernardo.bernardinhio.globalside.retrofit.model.ProfileDataModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var adapterRecyclerView: ProfilesAdapter
    private val dataRecyclerView = ArrayList<Profile>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Profiles - Global Side"

        bindToDataProviderListOfProfiles()
        GlobalSideDataProvider.getProfiles()

        setupRecyclerView()
        setupRecyclerViewAdapter()
    }

    private fun bindToDataProviderListOfProfiles(){
        GlobalScope.launch {
            val backendData: Pair<String, List<ProfileDataModel>?> = GlobalSideDataProvider.channelListProfiles.receive()
            populateRecyclerView(backendData)

            runOnUiThread{
                Toast.makeText(this@MainActivity, backendData.first, Toast.LENGTH_LONG).show()
                adapterRecyclerView.notifyDataSetChanged()
            }
        }
    }

    private fun populateRecyclerView(backendData: Pair<String, List<ProfileDataModel>?>) {

        backendData.second?.forEach { backendProfile ->

            dataRecyclerView.add(
                Profile(
                    profileId = backendProfile.profile_id.orEmpty(),
                    displayedName = backendProfile.display_name.orEmpty(),
                    isPrimary = backendProfile.is_primary_profile?:false,
                    gender = backendProfile.gender.orEmpty(),
                    dateOfBirth = backendProfile.date_of_birth.orEmpty(),
                    tariffLabel = backendProfile.tariff_label.orEmpty(),
                    tariffIconUrl = backendProfile.tariff?.icon?.url.orEmpty(),
                    tariffIconPrimaryColor = backendProfile.tariff?.icon?.primary_color.orEmpty(),
                    tariffIconSecondaryColor = backendProfile.tariff?.icon?.secondary_color.orEmpty(),
                    address = backendProfile.address?.street.orEmpty()
                        .plus(" ${backendProfile.address?.street_number.orEmpty()}")
                        .plus("\n ${backendProfile.address?.zip.orEmpty()}")
                        .plus(" ").plus(backendProfile.address?.city.orEmpty()),
                    contact = backendProfile.contact?.email.orEmpty()
                        .plus("\n ${backendProfile.contact?.phone.orEmpty()}")
                )
            )
        }
    }

    private fun setupRecyclerView() {
        recyclerViewProfiles.setHasFixedSize(false)
        recyclerViewProfiles.layoutManager = LinearLayoutManager(this.applicationContext)
    }

    private fun setupRecyclerViewAdapter() {
        adapterRecyclerView = ProfilesAdapter(this, dataRecyclerView)
        recyclerViewProfiles.adapter = adapterRecyclerView
    }
}