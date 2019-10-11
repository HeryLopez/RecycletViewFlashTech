package com.app.recyclerviewflashtechexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this)[MainViewModel::class.java]
        viewModel.context = applicationContext
        viewModel.addPersonsFromJson()

        bottomAppBar.setOnMenuItemClickListener {
            when {
                it.itemId == R.id.delete -> viewModel.deleteRandomPerson()
                it.itemId == R.id.filter -> viewModel.filterByRandomTeam()
                it.itemId == R.id.reset -> viewModel.addPersonsFromJson()
            }
            true
        }

        mainActionButton.setOnClickListener {
            viewModel.addRandomPerson()
        }
    }
}
