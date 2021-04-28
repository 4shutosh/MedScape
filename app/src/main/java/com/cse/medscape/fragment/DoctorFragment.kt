package com.cse.medscape.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cse.medscape.R
import com.cse.medscape.adapter.DoctorAdapter
import com.cse.medscape.databinding.FragmentDoctorBinding
import com.cse.medscape.model.Doctor
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DoctorFragment : Fragment(R.layout.fragment_doctor) {

    private lateinit var binding: FragmentDoctorBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDoctorBinding.bind(view)

        binding.progress.visibility = View.VISIBLE
        val database = FirebaseDatabase.getInstance().reference.child("Doctor")
        val listener = object : ValueEventListener {
            val list = mutableListOf<Doctor>()
            override fun onDataChange(snapshot: DataSnapshot) {
                for (data in snapshot.children) {
                    val temp = data.getValue(Doctor::class.java)
                    if (temp != null) {
                        list.add(temp)
                    }
                }
                if (list.isNotEmpty()) {
                    binding.noList.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                    val remedyAdapter = DoctorAdapter(list, requireActivity())
                    binding.recyclerView.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter = remedyAdapter
                    }
                } else {
                    binding.noList.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }
                binding.progress.visibility = View.GONE
            }

            override fun onCancelled(error: DatabaseError) {

            }

        }
        database.addValueEventListener(listener)
    }
}