package com.app.recyclerviewflashtechexample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_team.view.*

class PersonFragmentAdapter() : ListAdapter<Person, PersonFragmentAdapter.ViewHolder>(PersonDiffCallBack()) {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_team, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person = getItem(holder.adapterPosition)

        val imageResID = context.resources.getIdentifier(person.image, "drawable", context.packageName)
        holder.view.image.setImageResource(imageResID)

        holder.view.name.text = person.name

        val teamId = person.team
        holder.view.color.text = context.getString(R.string.team_label, teamId)
        holder.view.color.setBackgroundResource(getTeamColor(teamId))
    }

    private fun getTeamColor(teamId: Int): Int {
        return when (teamId) {
            1 -> R.color.color_team_01
            2 -> R.color.color_team_02
            3 -> R.color.color_team_03
            else -> R.color.color_team_03
        }
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}
