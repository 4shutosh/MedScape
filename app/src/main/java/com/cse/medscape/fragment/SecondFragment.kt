package com.cse.medscape.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cse.medscape.R
import com.cse.medscape.adapter.LabTestsAdapter
import com.cse.medscape.viewmodel.LabTestsViewModel
import com.cse.medscape.model.LabTest

class SecondFragment : Fragment() {

    private lateinit var labTestsViewModel: LabTestsViewModel
    private lateinit var recyclerView: RecyclerView

    private fun onLabTestsFetched(labTests : List<LabTest>, recyclerView: RecyclerView) {

        recyclerView.adapter = LabTestsAdapter(requireContext(), labTests.toMutableList(), this.layoutInflater, labTestsViewModel)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        labTestsViewModel = ViewModelProviders.of(this).get(LabTestsViewModel::class.java)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.second_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recyclerView = view.findViewById(R.id.recyclerViewLabTests)

        labTestsViewModel.labTests.observe(this, Observer {

            it?.let{ onLabTestsFetched(it, recyclerView) }

        })

        val localContext : Context? = activity

        if(localContext == null) {
            return
        }

        labTestsViewModel.fetchLabTests(localContext)

    }

}