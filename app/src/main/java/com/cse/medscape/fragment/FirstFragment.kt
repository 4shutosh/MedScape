package com.cse.medscape.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cse.medscape.R
import com.cse.medscape.adapter.SymptomsAdapter
import com.cse.medscape.viewmodel.SymptomViewModel
import com.cse.medscape.model.Symptom


class FirstFragment() : Fragment() {

    private lateinit var symptomViewModel : SymptomViewModel
    private lateinit var recyclerView: RecyclerView

    private fun onSymptomsFetched(symptoms : List<Symptom>, recyclerView: RecyclerView) {

        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, LinearLayoutManager(requireContext()).orientation)
        dividerItemDecoration.setDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.draw_recyclerview
            )!!
        )

        recyclerView.addItemDecoration(dividerItemDecoration)

        recyclerView.adapter = SymptomsAdapter(requireContext(), symptoms, this.layoutInflater, symptomViewModel)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        symptomViewModel = ViewModelProviders.of(this).get(SymptomViewModel::class.java)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.first_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recyclerView = view.findViewById(R.id.recycler_view)

        symptomViewModel.symptomsFound.observe(this, Observer {

            it?.let{ onSymptomsFetched(it, recyclerView) }

        })

        val localContext : Context? = activity

        if(localContext == null) {
            return
        }

        symptomViewModel.fetchSymptoms(localContext)

    }

}