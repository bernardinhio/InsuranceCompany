package bernardo.bernardinhio.globalside.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bernardo.bernardinhio.globalside.R
import bernardo.bernardinhio.globalside.model.HealthPrompt
import bernardo.bernardinhio.globalside.model.Profile

class HealthPromptsAdapter(
    val context: Context,
    val data: List<HealthPrompt> = mutableListOf<HealthPrompt>()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HealthPromptViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_health_prompt, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    inner class HealthPromptViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {

    }

}