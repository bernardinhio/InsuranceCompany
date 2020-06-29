package bernardo.bernardinhio.globalside.adapter

import android.content.Context
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bernardo.bernardinhio.globalside.R
import bernardo.bernardinhio.globalside.model.TimelineEvent
import kotlinx.android.synthetic.main.item_list_timeline_event.view.*
import java.util.*

class TimelineEventsAdapter(
    val context: Context,
    val data: List<TimelineEvent> = mutableListOf<TimelineEvent>()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TimelineEventViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_timeline_event, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val itemData = data.get(position)

        (holder as TimelineEventsAdapter.TimelineEventViewHolder).apply {
            textViewTitle.text = itemData.title
            textViewDescription.text = itemData.description
            textViewCategory.text = itemData.displayCategory
            textViewTime.text = getFormattedTime(itemData.time)
        }
    }

    private fun getFormattedTime(timestamp: String): String {

        // format from backend: "2020-02-18T08:00:00+00:00"
        val year = timestamp.substring(0, 4)
        val month = timestamp.substring(5, 7)
        val day = timestamp.substring(8, 10)
        val hourAndMinutes = timestamp.substring(11, 16)

        val calendar: Calendar = GregorianCalendar(year.toInt(), month.toInt(), day.toInt())
        val dayNumber = calendar.get(Calendar.DAY_OF_WEEK)

        val dayNameGerman =  when(dayNumber){
            Calendar.MONDAY -> "Montag"
            Calendar.TUESDAY -> "Dienstag"
            Calendar.WEDNESDAY -> "Mittwoch"
            Calendar.THURSDAY -> "Donnerstag"
            Calendar.FRIDAY -> "Freitag"
            Calendar.SATURDAY -> "Samstag"
            Calendar.SUNDAY -> "Sonntag"
            else -> ""
        }

        val formattedDateAndTime = "$dayNameGerman, $day.$month.$year am $hourAndMinutes"
        return formattedDateAndTime
    }

    inner class TimelineEventViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {

        val textViewTitle = itemView.tvTitle
        val textViewDescription = itemView.tvDescription
        val textViewCategory = itemView.tvCategory
        val textViewTime = itemView.tvTime
    }

}