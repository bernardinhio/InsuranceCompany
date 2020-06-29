package bernardo.bernardinhio.globalside.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bernardo.bernardinhio.globalside.R
import bernardo.bernardinhio.globalside.model.HealthPrompt
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_list_health_prompt.view.*
import kotlinx.android.synthetic.main.item_list_timeline_event.view.tvTitle

class HealthPromptsAdapter(
    val context: Context,
    val data: List<HealthPrompt> = mutableListOf<HealthPrompt>()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HealthPromptViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_health_prompt, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val itemData = data.get(position)

        (holder as HealthPromptsAdapter.HealthPromptViewHolder).apply {
            textViewMessage.text = itemData.message
            textViewTitle.text = itemData.title
            if (itemData.imageUrl.isNotEmpty()){
                imageViewIcon.setBackgroundColor(if(itemData.isPermanent) Color.parseColor(itemData.imagePrimaryColor) else Color.parseColor(itemData.imageSecondaryColor))
                Glide.with(context)
                    .load(itemData.imageUrl)
                    .fitCenter()
                    .into(holder.imageViewIcon)
            } else{
                imageViewIcon.visibility = View.GONE
            }
            textViewPermanent.text = if (itemData.isPermanent) "Permanent" else "Temporary"
            textViewCategory.text = itemData.category
        }
    }

    inner class HealthPromptViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {

        val textViewMessage = itemView.tvMessage
        val textViewTitle = itemView.tvTitle
        val imageViewIcon = itemView.ivIcon
        val textViewPermanent = itemView.tvPermanent
        val textViewCategory = itemView.tvPromptCategory
    }
}