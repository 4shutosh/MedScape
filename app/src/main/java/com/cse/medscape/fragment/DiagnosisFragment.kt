package com.cse.medscape.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cse.medscape.R
import com.cse.medscape.model.Condition
import com.cse.medscape.viewmodel.DiagnosisResultViewModel
import com.cse.medscape.viewmodel.UserViewModel
import com.example.mydiagnosis.adapter.ConditionsAdapter

class DiagnosisFragment : Fragment() {

    private lateinit var diagnosisResultViewModel: DiagnosisResultViewModel
    private lateinit var userViewModel: UserViewModel

    private lateinit var recyclerView: RecyclerView

    private fun onConditionsFetched(conditions: List<Condition>, recyclerView: RecyclerView) {

        recyclerView.adapter = ConditionsAdapter(
            requireContext(),
            conditions.toMutableList(),
            this.layoutInflater,
            diagnosisResultViewModel
        )
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        diagnosisResultViewModel =
            ViewModelProviders.of(this).get(DiagnosisResultViewModel::class.java)
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.diagnosis_results, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val diagnoseButton = view.findViewById<Button>(R.id.diagnose_button)
        recyclerView = view.findViewById(R.id.recycler_view_diagnosis)

        val noText = view.findViewById<TextView>(R.id.noResults)

        val progress: ProgressBar = view.findViewById(R.id.progress)

        recyclerView.visibility = View.GONE

        diagnosisResultViewModel.conditions.observe(this, Observer {

            it?.let {
                if (it.isNotEmpty()) {
                    onConditionsFetched(it, recyclerView)
                } else {
                    progress.visibility = View.GONE
                    noText.visibility = View.VISIBLE
                }
            }

        })

        val localContext: Context? = activity

        if (localContext == null) {
            return
        }

        diagnoseButton.setOnClickListener {

            diagnoseButton.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
            progress.visibility = View.VISIBLE
            val userAge = userViewModel.getUserAge(localContext)
            val userGender = userViewModel.getUserGender(localContext)
            diagnosisResultViewModel.getDiagnosis(localContext, userAge, userGender)

        }


    }

}