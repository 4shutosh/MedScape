package com.cse.medscape.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.cse.medscape.databinding.ItemDoctorBinding
import com.cse.medscape.model.Doctor

class DoctorAdapter(
    val list: List<Doctor>,
    val activity: FragmentActivity
) : RecyclerView.Adapter<DoctorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemDoctorBinding.inflate(layoutInflater, parent, false)
        return DoctorViewHolder(itemBinding, activity, list)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class DoctorViewHolder(
    private val itemDoctorBinding: ItemDoctorBinding,
    val activity: FragmentActivity,
    val list: List<Doctor>
) :
    RecyclerView.ViewHolder(itemDoctorBinding.root), View.OnClickListener {

    fun bind(doctor: Doctor) {
        itemDoctorBinding.name.text = doctor.name
        itemDoctorBinding.speciality.text = doctor.speciality
        itemDoctorBinding.card.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        /// call here
        val intent =
            Intent(Intent.ACTION_CALL, Uri.parse("tel:" + list[adapterPosition].phone))
        activity.startActivity(intent)
    }

}