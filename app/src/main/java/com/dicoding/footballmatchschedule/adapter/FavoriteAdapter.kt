package com.dicoding.footballmatchschedule.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.footballmatchschedule.R
import com.dicoding.footballmatchschedule.model.Events
import com.dicoding.footballmatchschedule.model.Favorite
import com.dicoding.footballmatchschedule.tools.formatDate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_event.*

/**
 * Created by Firdaus Musyafi on 9/18/18.
 */
class FavoriteAdapter(private val items: List<Favorite>, val listener : (Favorite)-> Unit): RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_event, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position],listener)
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(items: Favorite, listener: (Favorite) -> Unit) {
            tv_tanggal.text = items.eventDate?.let { formatDate("yyyy-MM-dd", "EEE, dd MMM yyyy", it) }
            tv_homeTeamName.text = items.homeTeamName
            tv_awayTeamName.text = items.awayTeamName
            tv_homeGoals.text = if(items.homeTeamGoals.equals("null")) "" else items.homeTeamGoals
            tv_awayGoals.text = if(items.awayTeamGoals.equals("null")) "" else items.awayTeamGoals
            containerView.setOnClickListener{
                listener(items)
            }
        }
    }
}