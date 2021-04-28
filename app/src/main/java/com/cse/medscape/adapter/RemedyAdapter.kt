package com.cse.medscape.adapter

import android.app.AlarmManager
import android.app.AlertDialog
import android.app.PendingIntent
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.cse.medscape.R
import com.cse.medscape.databinding.ItemRemedyBinding
import com.cse.medscape.fragment.WebViewFragment
import com.cse.medscape.model.Remedy
import com.cse.medscape.utils.ReminderBroadCast
import java.util.*

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

        AlertDialog.Builder(activity)
            .setTitle(list[adapterPosition].diseaseName)
            .setCancelable(true)
            .setMessage(list[adapterPosition].shortRemedy)
            .setPositiveButton(R.string.remind, object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    // implement reminder here
                    p0?.dismiss()
                    val alarmManager =
                        activity.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                    val time = Calendar.getInstance().time
//                    Log.d("time", "onClick: $time")
                    val reminder = Calendar.getInstance()
                    reminder.set(
                        time.year,
                        time.month,
                        time.date,
                        time.hours + 2,
                        time.minutes,
                        time.seconds + 5
                    )
                    val intent = Intent(activity, ReminderBroadCast::class.java)
                    intent.putExtra("name", list[adapterPosition].diseaseName)
                    val pendingIntent = PendingIntent.getBroadcast(
                        activity,
                        123,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                    )
                    alarmManager.set(AlarmManager.RTC_WAKEUP, reminder.timeInMillis, pendingIntent)
                    Log.d("reminder set", "onClick: reminder set " + reminder.time)
                    Toast.makeText(activity, "Reminder set!", Toast.LENGTH_SHORT).show()
                    p0?.dismiss()
                }
            })
            .setNegativeButton(R.string.moreHelp, object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    // implement reminder here
                    val fragment = WebViewFragment()
                    val bundle = Bundle()
                    bundle.putString("link", list[adapterPosition].link)
                    fragment.arguments = bundle
                    p0?.dismiss()
                    activity.supportFragmentManager.beginTransaction().replace(
                        R.id.flContent,
                        fragment
                    )
                        .commit()
                }
            })
            .show()
    }

}