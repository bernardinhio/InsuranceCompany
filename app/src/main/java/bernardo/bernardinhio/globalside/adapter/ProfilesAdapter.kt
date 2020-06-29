package bernardo.bernardinhio.globalside.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bernardo.bernardinhio.globalside.R
import bernardo.bernardinhio.globalside.model.Profile
import bernardo.bernardinhio.globalside.view.HealthPromptsActivity
import bernardo.bernardinhio.globalside.view.TimelineEventsActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_list_profile.view.*

class ProfilesAdapter(
    val context: Context,
    val data: List<Profile> = mutableListOf<Profile>()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ProfileViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_profile, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val itemData = data.get(position)

        (holder as ProfileViewHolder).apply {
            textViewDisplayedName.text = itemData.displayedName
            textViewIsPrimary.text = if(itemData.isPrimary) "Primary" else "Secondary"
            textViewDateOfBirth.text = itemData.dateOfBirth
            textViewGender.text = itemData.gender
            textViewTarrifLabel.text = itemData.tariffLabel
            textViewTarrifLabel.setBackgroundColor(if(itemData.isPrimary) Color.parseColor(itemData.tariffIconPrimaryColor) else Color.parseColor(itemData.tariffIconSecondaryColor))
            if (itemData.tariffIconUrl.isNotEmpty()){
                imageViewTariffIcon.setBackgroundColor(if(itemData.isPrimary) Color.parseColor(itemData.tariffIconPrimaryColor) else Color.parseColor(itemData.tariffIconSecondaryColor))
                Glide.with(context)
                    .load(itemData.tariffIconUrl)
                    .fitCenter()
                    .into(holder.imageViewTariffIcon)
            } else {
                imageViewTariffIcon.visibility = View.GONE
            }
            textViewAddress.text = itemData.address
            textViewContact.text = itemData.contact
            buttonTimelineEvents.setOnClickListener{ context.startActivity(Intent(context, TimelineEventsActivity::class.java).apply { putExtra("profileId", itemData.profileId) }) }
            buttonHealthPrompt.setOnClickListener{ context.startActivity(Intent(context, HealthPromptsActivity::class.java).apply { putExtra("profileId", itemData.profileId) }) }
        }
    }

    inner class ProfileViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {

        val textViewDisplayedName = itemView.tvDisplayedName
        val textViewIsPrimary = itemView.tvIsPrimary
        val textViewDateOfBirth = itemView.tvDateOfBirth
        val textViewGender = itemView.tvGender
        val textViewTarrifLabel = itemView.tvTarrifLabel
        val imageViewTariffIcon = itemView.ivTariffIcon
        val textViewAddress = itemView.tvAddress
        val textViewContact = itemView.tvContact
        val buttonTimelineEvents = itemView.btnTimelineEvents
        val buttonHealthPrompt = itemView.btnHealthPrompt
    }

}