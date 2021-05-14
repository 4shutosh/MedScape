package com.cse.medscape.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cse.medscape.R
import com.cse.medscape.databinding.FragmentTermsAndConditionsBinding

class TermsAndConditions : Fragment(R.layout.fragment_terms_and_conditions) {
    private lateinit var binding: FragmentTermsAndConditionsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentTermsAndConditionsBinding.bind(view)
        binding.buttonProceed.setOnClickListener {
            if (binding.checkboxAccept.isChecked){
                findNavController().navigate(TermsAndConditionsDirections.actionTermsAndConditionsToLoginFragment())
            }
            else{
                Toast.makeText(context,"Please Accept Terms & Conditions",Toast.LENGTH_SHORT).show()
            }
        }

    }

}