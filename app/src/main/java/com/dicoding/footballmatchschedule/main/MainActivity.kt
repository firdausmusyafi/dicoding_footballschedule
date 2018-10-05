package com.dicoding.footballmatchschedule.main

import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.AdapterView
import com.dicoding.footballmatchschedule.R
import com.dicoding.footballmatchschedule.R.id.next
import com.dicoding.footballmatchschedule.R.id.prev
import com.dicoding.footballmatchschedule.adapter.FavoriteAdapter
import com.dicoding.footballmatchschedule.adapter.LeagueAdapter
import com.dicoding.footballmatchschedule.adapter.RecyclerViewAdapter
import com.dicoding.footballmatchschedule.api.ApiRepository
import com.dicoding.footballmatchschedule.model.Events
import com.dicoding.footballmatchschedule.model.Favorite
import com.dicoding.footballmatchschedule.model.Leagues
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

interface MainView{
    fun showLoading()
    fun hideLoading()
    fun showEvent(data : Events)
    fun showLeague(data : Leagues)
    fun showFavorite(data : List<Favorite>)
}

class MainActivity : AppCompatActivity(), MainView {
    private var leagues: MutableList<Leagues.League> = mutableListOf()
    private var events: MutableList<Events.Event> = mutableListOf()
    private var favorites: MutableList<Favorite> = mutableListOf()
    private lateinit var leagueAdapter: LeagueAdapter
    private lateinit var eventsAdapter: RecyclerViewAdapter
    private lateinit var favoriteAdapter : FavoriteAdapter
    private lateinit var presenter: MainPresenter

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showEvent(data: Events) {
        pilihliga.visibility = View.VISIBLE
        league.visibility = View.VISIBLE
        events.clear()
        events.addAll(data.events)
        recycleView.adapter = eventsAdapter
        eventsAdapter.notifyDataSetChanged()
    }

    override fun showLeague(data: Leagues) {
        leagues.clear()
        leagues.addAll(data.leagues)
        leagueAdapter.notifyDataSetChanged()
    }

    override fun showFavorite(data: List<Favorite>) {
        pilihliga.visibility = View.INVISIBLE
        league.visibility = View.INVISIBLE
        favorites.clear()
        favorites.addAll(data)
        recycleView.adapter = favoriteAdapter
        favoriteAdapter.notifyDataSetChanged()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val request = ApiRepository()
        val gson = Gson()
        leagueAdapter = LeagueAdapter(this,R.layout.list_spinner,leagues)
        favoriteAdapter = FavoriteAdapter(favorites){
            startActivity<DetailEventActivity>("eventId" to it.eventId)
        }
        eventsAdapter = RecyclerViewAdapter(events){
            startActivity<DetailEventActivity>("eventId" to it.eventId)
        }
        league.adapter = leagueAdapter
        presenter = MainPresenter(this, request, gson)
        presenter.getLeague()
        recycleView.adapter = eventsAdapter
        recycleView.layoutManager = LinearLayoutManager(this)
        league.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (navigation.selectedItemId==R.id.prev)
                    presenter.getLast15(leagueAdapter.getItem(position).leagueId!!)
                else
                    presenter.getNext15(leagueAdapter.getItem(position).leagueId!!)
            }

        }

        navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                prev -> {presenter.getLast15((league.selectedItem as Leagues.League).leagueId!!)}
                next -> {presenter.getNext15((league.selectedItem as Leagues.League).leagueId!!)}
                R.id.favorite ->{ presenter.getFavorite(this@MainActivity)}
            }
            return@setOnNavigationItemSelectedListener true
        }


    }
}
