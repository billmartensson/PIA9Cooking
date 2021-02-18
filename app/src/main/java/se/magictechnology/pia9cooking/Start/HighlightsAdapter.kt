package se.magictechnology.pia9cooking

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class HighlightsAdapter() : RecyclerView.Adapter<HighlightViewHolder>() {

    lateinit var startfrag : StartFragment

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighlightViewHolder {
        val vh = HighlightViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.highlight_item, parent, false))
        return vh
    }

    override fun getItemCount(): Int {

        startfrag.startvm.getHighlights().value?.let {
            return it.size
        }

        return 0
    }

    override fun onBindViewHolder(holder: HighlightViewHolder, position: Int) {

        val currentRecipie = startfrag.startvm.getHighlights().value!![position]

        holder.highlighttextview.text = currentRecipie.title

        currentRecipie.recipeimage?.let { imageName ->
            val imageRef = Firebase.storage.reference.child("pia9cooking").child(imageName)

            imageRef.downloadUrl.addOnSuccessListener { imageUri ->
                Log.d("pia9debug", imageUri.toString())

                Glide.with(holder.itemView).load(imageUri).into(holder.highlightimageview)

            }

        }

        holder.itemView.setOnClickListener {
            Log.d("PIA9DEBUG", "KLICKAT PÅ ITEM")

            startfrag.goRecipe(currentRecipie)

        }
    }

}

class HighlightViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    var highlighttextview = view.findViewById<TextView>(R.id.highlightTextview)
    var highlightimageview = view.findViewById<ImageView>(R.id.highlightImageview)

}