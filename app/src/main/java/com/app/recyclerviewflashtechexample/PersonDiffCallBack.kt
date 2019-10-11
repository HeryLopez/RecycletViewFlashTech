package com.app.recyclerviewflashtechexample

import androidx.recyclerview.widget.DiffUtil

class PersonDiffCallBack : DiffUtil.ItemCallback<Person>() {

    override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem.name == newItem.name && oldItem.team == newItem.team && oldItem.image == newItem.image
    }
}