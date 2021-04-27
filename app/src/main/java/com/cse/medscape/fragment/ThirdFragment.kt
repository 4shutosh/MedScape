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
import com.cse.medscape.adapter.RiskFactorsAdapter
import com.cse.medscape.viewmodel.RiskFactorsViewModel
import com.cse.medscape.model.RiskFactor

class ThirdFragment : Fragment() {

    private lateinit var riskFactorsViewModel: RiskFactorsViewModel
    private lateinit var recyclerView: RecyclerView

    private fun onRiskFactorsFetched(riskFactors: List<RiskFactor>, recyclerView: RecyclerView) {

        recyclerView.adapter = RiskFactorsAdapter(requireContext(), riskFactors, this.layoutInflater, riskFactorsViewModel)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        riskFactorsViewModel = ViewModelProviders.of(this).get(RiskFactorsViewModel::class.java)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.third_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        println("Fetching risk factors")

        recyclerView = view.findViewById(R.id.risk_factors_recyclerview)

        riskFactorsViewModel.riskFactors.observe(this, Observer {

            it?.let{ onRiskFactorsFetched(it, recyclerView) }

        })

        val localContext : Context? = activity

        if(localContext == null) {
            return
        }

        riskFactorsViewModel.fetchRiskFactors(localContext)

    }



}