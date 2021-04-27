package com.cse.medscape.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.cse.medscape.R
import com.cse.medscape.databinding.ItemRemedyBinding
import com.cse.medscape.fragment.WebViewFragment
import com.cse.medscape.model.Remedy

class RemedyAdapter(
    val list: List<Remedy>,
    val activity: FragmentActivity
) : RecyclerView.Adapter<RemedyVieHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RemedyVieHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemRemedyBinding.inflate(layoutInflater, parent, false)
        return RemedyVieHolder(itemBinding, activity, list)
    }

    override fun onBindViewHolder(holder: RemedyVieHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class RemedyVieHolder(
    private val itemRemedyBinding: ItemRemedyBinding,
    val activity: FragmentActivity,
    val list: List<Remedy>
) :
    RecyclerView.ViewHolder(itemRemedyBinding.root), View.OnClickListener {

    fun bind(remedy: Remedy) {
        itemRemedyBinding.name.text = remedy.diseaseName
        itemRemedyBinding.shortText.text = remedy.shortRemedy
        itemRemedyBinding.card.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val fragment = WebViewFragment()
        val bundle = Bundle()
        bundle.putString("link", list[adapterPosition].link)
        fragment.arguments = bundle
        activity.supportFragmentManager.beginTransaction().replace(R.id.flContent, fragment)
            .commit()
    }

}