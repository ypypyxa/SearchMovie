package com.practiCUM.searchmovie.ui.names

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.practiCUM.searchmovie.domain.models.Person

class PersonsAdapter : RecyclerView.Adapter<PersonViewHolder>() {

    var persons = mutableListOf<Person>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder = PersonViewHolder(parent)

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(persons[position])
    }

    override fun getItemCount(): Int = persons.size
}