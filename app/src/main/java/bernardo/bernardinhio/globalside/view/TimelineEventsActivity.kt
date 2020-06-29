package bernardo.bernardinhio.globalside.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import bernardo.bernardinhio.globalside.R
import bernardo.bernardinhio.globalside.adapter.TimelineEventsAdapter
import bernardo.bernardinhio.globalside.dataprovider.GlobalSideDataProvider
import bernardo.bernardinhio.globalside.model.TimelineEvent
import bernardo.bernardinhio.globalside.retrofit.model.ProfileTimelineEventDataModel
import kotlinx.android.synthetic.main.activity_timeline_events.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TimelineEventsActivity : AppCompatActivity() {

    private lateinit var adapterRecyclerView: TimelineEventsAdapter
    private val dataRecyclerView = ArrayList<TimelineEvent>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timeline_events)

        title = "Timeline Events"

        if (intent != null){
            val profileId = intent.getStringExtra("profileId")
            if(!profileId.isNullOrEmpty()){
                bindToListOfProfileTimelineEventsChannel()
                GlobalSideDataProvider.getProfileTimelineEvents(profileId)
            }
        }

        setupRecyclerView()
        setupRecyclerViewAdapter()
    }

    private fun bindToListOfProfileTimelineEventsChannel() {
        GlobalScope.launch {
            val backendData: Pair<String, List<ProfileTimelineEventDataModel>?> = GlobalSideDataProvider.channelListProfileTimelineEvents.receive()
            populateRecyclerView(backendData)

            runOnUiThread{
                if (!backendData.first.equals(GlobalSideDataProvider.BackendStatus.SUCCESS.message)){
                    Toast.makeText(this@TimelineEventsActivity, backendData.first, Toast.LENGTH_LONG).show()
                }
                adapterRecyclerView.notifyDataSetChanged()
            }
        }
    }

    private fun populateRecyclerView(backendData: Pair<String, List<ProfileTimelineEventDataModel>?>) {

        backendData.second?.forEach { backendProfileTimelineEvent ->

            dataRecyclerView.add(
                TimelineEvent(
                    time = backendProfileTimelineEvent.timestamp.orEmpty(),
                    displayCategory = backendProfileTimelineEvent.display_category.orEmpty(),
                    title = backendProfileTimelineEvent.title.orEmpty(),
                    description = backendProfileTimelineEvent.description.orEmpty()
                )
            )
        }
    }

    private fun setupRecyclerView() {
        recyclerViewTimelineEvents.setHasFixedSize(false)
        recyclerViewTimelineEvents.layoutManager = LinearLayoutManager(this.applicationContext)
    }

    private fun setupRecyclerViewAdapter() {
        adapterRecyclerView = TimelineEventsAdapter(this, dataRecyclerView)
        recyclerViewTimelineEvents.adapter = adapterRecyclerView
    }
}