package bernardo.bernardinhio.globalside.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bernardo.bernardinhio.globalside.R
import bernardo.bernardinhio.globalside.model.TimelineEvent

class TimelineEventsAdapter(
    val context: Context,
    val data: List<TimelineEvent> = mutableListOf<TimelineEvent>()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TimelineEventViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_timeline_event, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    inner class TimelineEventViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {

    }

}