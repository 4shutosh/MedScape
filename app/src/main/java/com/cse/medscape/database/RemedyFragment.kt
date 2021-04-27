package com.cse.medscape.database

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cse.medscape.R
import com.cse.medscape.adapter.RemedyAdapter
import com.cse.medscape.databinding.FragmentRemedyBinding
import com.cse.medscape.model.Remedy
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class RemedyFragment : Fragment(R.layout.fragment_remedy) {

    private lateinit var binding: FragmentRemedyBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRemedyBinding.bind(view)

        binding.progress.visibility = View.VISIBLE
        val database = FirebaseDatabase.getInstance().reference.child("Remedy")
        val listener = object : ValueEventListener {
            val list = mutableListOf<Remedy>()
            override fun onDataChange(snapshot: DataSnapshot) {
                for (data in snapshot.children) {
                    val temp = data.getValue(Remedy::class.java)
                    if (temp != null) {
                        list.add(temp)
                    }
                }
                if (list.isNotEmpty()) {
                    binding.noList.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                    val remedyAdapter = RemedyAdapter(list, requireActivity())
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