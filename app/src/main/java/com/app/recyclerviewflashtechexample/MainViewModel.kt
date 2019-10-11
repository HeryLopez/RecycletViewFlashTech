package com.app.recyclerviewflashtechexample

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class MainViewModel : ViewModel() {
    private var originalList: MutableList<Person> = mutableListOf()
    val personsList: MutableLiveData<MutableList<Person>> = MutableLiveData()
    lateinit var context: Context

    fun addPersonsFromJson() {
        originalList = mutableListOf()
        for (i in 0..5) {
            addRandomPerson()
        }
    }

    fun filterByRandomTeam() {
        val teamId = Random.nextInt(1, 4)

        val filter = originalList.filter {
            it.team == teamId
        }
        personsList.value = filter.toMutableList()
    }

    fun addRandomPerson() {
        val names = context.resources.getStringArray(R.array.names)

        val id = originalList.size + 1
        val name = names[Random.nextInt(1, 50)]
        val team = Random.nextInt(1, 4)
        val imageId = Random.nextInt(1, 11).toString().padStart(2, '0')
        val image = "avatar_$imageId"

        val index = if (originalList.isNotEmpty()) Random.nextInt(0, originalList.size) else 0
        originalList.add(index, Person(id, name, team, image))
        personsList.value = originalList
    }

    fun deleteRandomPerson() {
        if (originalList.isNotEmpty()) {
            originalList.removeAt(Random.nextInt(0, originalList.size))
            personsList.value = originalList
        }
    }
}