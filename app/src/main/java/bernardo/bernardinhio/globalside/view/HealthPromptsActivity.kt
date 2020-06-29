package bernardo.bernardinhio.globalside.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import bernardo.bernardinhio.globalside.R
import bernardo.bernardinhio.globalside.adapter.HealthPromptsAdapter
import bernardo.bernardinhio.globalside.dataprovider.GlobalSideDataProvider
import bernardo.bernardinhio.globalside.model.HealthPrompt
import bernardo.bernardinhio.globalside.retrofit.model.ProfileHealthPromptDataModel
import kotlinx.android.synthetic.main.activity_health_prompts.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HealthPromptsActivity : AppCompatActivity() {

    private lateinit var adapterRecyclerView: HealthPromptsAdapter
    private val dataRecyclerView = ArrayList<HealthPrompt>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_prompts)

        title = "Health Prompts"

        if (intent != null){
            val profileId = intent.getStringExtra("profileId")
            if(!profileId.isNullOrEmpty()){
                bindToListOfProfileHealthPromptsChannel()
                GlobalSideDataProvider.getProfileHealthPrompts(profileId)
            }
        }

        setupRecyclerView()
        setupRecyclerViewAdapter()
    }

    private fun bindToListOfProfileHealthPromptsChannel() {
        GlobalScope.launch {
            val backendData: Pair<String, List<ProfileHealthPromptDataModel>?> = GlobalSideDataProvider.channelListProfileHealthPrompts.receive()
            populateRecyclerView(backendData)

            runOnUiThread{
                if (!backendData.first.equals(GlobalSideDataProvider.BackendStatus.SUCCESS.message)){
                    Toast.makeText(this@HealthPromptsActivity, backendData.first, Toast.LENGTH_LONG).show()
                }
                adapterRecyclerView.notifyDataSetChanged()
            }
        }
    }

    private fun populateRecyclerView(backendData: Pair<String, List<ProfileHealthPromptDataModel>?>) {

        backendData.second?.forEach { backendProfileHealthPrompts ->

            dataRecyclerView.add(

                HealthPrompt(
                    message = backendProfileHealthPrompts.message.orEmpty(),
                    title = backendProfileHealthPrompts.metadata?.link?.title.orEmpty(),
                    category = backendProfileHealthPrompts.display_category.orEmpty(),
                    isPermanent = backendProfileHealthPrompts.permanent?:false,
                    imageUrl = backendProfileHealthPrompts.style?.image.orEmpty(),
                    imagePrimaryColor = backendProfileHealthPrompts.style?.primary_color.orEmpty(),
                    imageSecondaryColor = backendProfileHealthPrompts.style?.secondary_color.orEmpty()
                )
            )
        }
    }

    private fun setupRecyclerView() {
        recyclerViewHealthPrompts.setHasFixedSize(false)
        recyclerViewHealthPrompts.layoutManager = LinearLayoutManager(this.applicationContext)
    }

    private fun setupRecyclerViewAdapter() {
        adapterRecyclerView = HealthPromptsAdapter(this, dataRecyclerView)
        recyclerViewHealthPrompts.adapter = adapterRecyclerView
    }
}