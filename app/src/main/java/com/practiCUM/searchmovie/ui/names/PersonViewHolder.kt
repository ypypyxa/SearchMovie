package com.practiCUM.searchmovie.ui.names

import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.practiCUM.searchmovie.R
import com.bumptech.glide.Glide
import com.practiCUM.searchmovie.domain.models.Person

class PersonViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(LayoutInflater.from(parent.context)
        .inflate(R.layout.item_person_list, parent, false)) {

    var photo: ImageView = itemView.findViewById(R.id.photo)
    var name: TextView = itemView.findViewById(R.id.name)
    var description: TextView = itemView.findViewById(R.id.description)

    fun bind(person: Person) {
        Glide.with(itemView)
            .load(person.photoUrl)
            .placeholder(R.drawable.ic_person)
            .circleCrop()
            .into(photo)

        name.text = person.name
        description.text = person.description
    }
}