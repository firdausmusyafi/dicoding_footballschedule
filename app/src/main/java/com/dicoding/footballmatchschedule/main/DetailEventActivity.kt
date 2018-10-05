package com.dicoding.footballmatchschedule.main

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.dicoding.footballmatchschedule.R
import com.dicoding.footballmatchschedule.api.ApiRepository
import com.dicoding.footballmatchschedule.db.database
import com.dicoding.footballmatchschedule.model.Events
import com.dicoding.footballmatchschedule.model.Favorite
import com.dicoding.footballmatchschedule.model.Teams
import com.dicoding.footballmatchschedule.tools.formatDate
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_event.*
import org.jetbrains.anko.design.snackbar


interface DetailView{
    fun showLoading()
    fun hideLoading()
    fun showDetail(data : Events.Event)
    fun showLogo(imageView: ImageView, data : Teams.Team)
    fun showInfo(info :String)

}

class DetailEventActivity : AppCompatActivity(), DetailView {

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    var eventId = ""
    lateinit var eventFavorite : Favorite
    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showLogo(imageView: ImageView,data: Teams.Team) {
        Glide.with(this).load(data.logo).into(imageView)
    }

    override fun showInfo(info: String) {
        snackbar(progressBar,info).show()
    }

    override fun showDetail(data: Events.Event) {
        tv_tanggal.text = data.dateEvent?.let { formatDate("yyyy-MM-dd","EEE, dd MMM yyyy", it) }
        labelHome.text = data.homeTeam
        labelAway.text = data.awayTeam
        formationHome.text = data.homeFormation
        formationAway.text = data.awayFormation
        homeScore.text = data.homeScore?.let { data.homeScore.toString() }
        awayscore.text = data.awayScore?.let { data.awayScore.toString() }
        shootHome.text = data.homeShots
        shootAway.text = data.awayShots
        goalDetailsHome.text = data.homeGoalDetails
        goalDetailsAway.text = data.awayGoalDetails
        shootHome.text = data.homeShots
        shootAway.text = data.awayShots
        goalkeeperhome.text = data.homeLineupGoalKeeper
        goalkeeperaway.text = data.awayLineupGoalkeeper
        defensehome.text = data.homeLineupDefense
        defenseaway.text = data.awayLineupDefense
        midfieldhome.text = data.homeLineupMidfield
        midfieldaway.text = data.awayLineupMidfield
        forwardhome.text = data.homeLineupForward
        forwardhome.text = data.awayLineupForward
        substituteshome.text = data.homeLineupSubstitutes
        substitutesaway.text = data.awayLineupSubstitutes
        data.homeTeamId?.let { presenter.getTeamDetails(logo_home, it) }
        data.awayTeamId?.let { presenter.getTeamDetails(logo_away, it) }
        isFavorite = presenter.isFavorite(this@DetailEventActivity,eventId)
        eventFavorite = Favorite(null,data.eventId,data.dateEvent,data.homeTeam, data.homeScore.toString(), data.awayTeam.toString(), data.awayScore.toString())
        setFavorite()
    }


    lateinit var presenter : DetailPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_event)
        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailPresenter(this,request,gson)
        eventId = intent.getStringExtra("eventId")
        presenter.getDetailEvent(intent.getStringExtra("eventId"))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail_event, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorite)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_favorite)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.favorite -> {
                if (isFavorite) presenter.removeFavorite(this@DetailEventActivity,eventId) else presenter.addFavorite(this@DetailEventActivity,eventFavorite)

                isFavorite = !isFavorite
                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


}
