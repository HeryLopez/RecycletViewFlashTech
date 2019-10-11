package com.app.recyclerviewflashtechexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_person.*
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer

class PersonFragment : Fragment() {

    private lateinit var adapterView: PersonFragmentAdapter

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: MainViewModel by activityViewModels()
        this.viewModel = viewModel
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_person, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = recyclerViewTeams
        val layoutManager = LinearLayoutManager(activity)
        adapterView = PersonFragmentAdapter()
        recyclerView.apply {
            setLayoutManager(layoutManager)
            adapter = adapterView
        }

        viewModel.personsList.observe(this, Observer<List<Person>> { personList ->
            when {
                personList.isNotEmpty() -> {
                    adapterView.submitList(personList.toList())
                    recyclerViewTeams.visibility = View.VISIBLE
                    noDataMessage.visibility = View.GONE
                }
                else -> {
                    adapterView.submitList(null)
                    recyclerViewTeams.visibility = View.GONE
                    noDataMessage.visibility = View.VISIBLE
                }
            }
        })
    }
}
