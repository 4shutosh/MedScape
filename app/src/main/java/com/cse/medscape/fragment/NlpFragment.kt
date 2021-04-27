package com.cse.medscape.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cse.medscape.R
import com.cse.medscape.adapter.MentionsAdapter

import com.cse.medscape.model.Mention
import com.cse.medscape.viewmodel.MentionsViewModel

class NlpFragment : Fragment() {

    private lateinit var mentionsViewModel: MentionsViewModel
    private lateinit var recyclerView: RecyclerView

    private fun onMentionsFetched(mentions: List<Mention>, recyclerView: RecyclerView) {

        recyclerView.adapter = MentionsAdapter(
            requireContext(),
            mentions.toMutableList(),
            this.layoutInflater,
            mentionsViewModel
        )
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mentionsViewModel = ViewModelProviders.of(this).get(MentionsViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.nlp_symptoms, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val userInput = view.findViewById<EditText>(R.id.userInput)
        val parseButton = view.findViewById<Button>(R.id.goButton)
        val userGuide = view.findViewById<TextView>(R.id.textView)

        recyclerView = view.findViewById(R.id.recycler_view_mentions)

        recyclerView.visibility = View.GONE

        mentionsViewModel.mentions.observe(this, Observer {

            it?.let { onMentionsFetched(it, recyclerView) }

        })

        val localContext: Context? = activity

        if (localContext == null) {
            return
        }

        parseButton.setOnClickListener {
            val userText = userInput.text.toString()
            println("Parse button clicked here $userText")
            recyclerView.visibility = View.VISIBLE
            userInput.visibility = View.GONE
            parseButton.visibility = View.GONE
            userGuide.visibility = View.GONE
            mentionsViewModel.getSymptomMentions(requireContext(), userText)

        }

    }
}