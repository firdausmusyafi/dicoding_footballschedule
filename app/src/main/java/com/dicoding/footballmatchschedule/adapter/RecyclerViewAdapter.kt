package com.dicoding.footballmatchschedule.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.dicoding.footballmatchschedule.R

import com.dicoding.footballmatchschedule.model.Events
import com.dicoding.footballmatchschedule.tools.formatDate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_event.*

/**
 * Created by Firdaus Musyafi on 9/1/18.
 */
class RecyclerViewAdapter(private val items: List<Events.Event>, val listener : (Events.Event)-> Unit): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_event, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bindItem(items[position],listener)
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer{
        fun bindItem(items: Events.Event,listener: (Events.Event) -> Unit) {
            tv_tanggal.text = items.dateEvent?.let { formatDate("yyyy-MM-dd", "EEE, dd MMM yyyy", it) }
            tv_homeTeamName.text = items.homeTeam
            tv_awayTeamName.text = items.awayTeam
            tv_homeGoals.text = items.homeScore?.let{ items.homeScore.toString() }
            tv_awayGoals.text = items.awayScore?.let{ items.awayScore.toString() }
            containerView.setOnClickListener{
                listener(items)
            }
        }
    }
}