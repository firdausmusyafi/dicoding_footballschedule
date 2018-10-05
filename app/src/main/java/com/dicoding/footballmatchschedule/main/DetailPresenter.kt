package com.dicoding.footballmatchschedule.main

import android.content.Context
import android.database.sqlite.SQLiteConstraintException

import android.widget.ImageView
import com.dicoding.footballmatchschedule.api.ApiRepository
import com.dicoding.footballmatchschedule.api.TheSportDBApi
import com.dicoding.footballmatchschedule.db.database
import com.dicoding.footballmatchschedule.model.Events
import com.dicoding.footballmatchschedule.model.Favorite
import com.dicoding.footballmatchschedule.model.Teams
import com.google.gson.Gson
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.delete

/**
 * Created by Firdaus Musyafi on 9/15/18.
 */
class DetailPresenter(private val view: DetailView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson){
    fun getDetailEvent(idEvent : String){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getDetailEvent(idEvent)),
                    Events::class.java
            )
//
            uiThread {
                view.hideLoading()
                view.showDetail(data.events[0])
            }
        }
    }

    fun getTeamDetails(imageView : ImageView ,idTeam : String){

        println("get Team Details $idTeam")
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getDetailTeam(idTeam)),
                    Teams::class.java
            )
//
            uiThread {
                view.hideLoading()
                view.showLogo(imageView,data.teams[0])
            }
        }
    }

    fun isFavorite(context: Context, idEvent : String) : Boolean{
        var isfavorite = false
        context?.database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                    .whereArgs("(EVENT_ID = {id})",
                            "id" to idEvent)
            val favorite = result.parseList(classParser<Favorite>())
            isfavorite = !favorite.isEmpty()
        }
        return isfavorite
    }

    fun addFavorite(context: Context, item: Favorite){
        try {
            context?.database.use {
                insert(Favorite.TABLE_FAVORITE,
                        Favorite.EVENT_ID to item.eventId,
                        Favorite.EVENT_DATE to item.eventDate,
                        Favorite.HOME_TEAM_NAME to item.homeTeamName,
                        Favorite.HOME_TEAM_GOALS to item.homeTeamGoals,
                        Favorite.AWAY_TEAM_NAME to item.awayTeamName,
                        Favorite.AWAY_TEAM_GOALS to item.awayTeamGoals)
            }
            view.showInfo("Added to Favorite")
        } catch (e: SQLiteConstraintException){
            view.showInfo(e.localizedMessage)
        }
    }


    fun removeFavorite(context: Context, idEvent: String){
        try {
            context?.database.use {
                delete(Favorite.TABLE_FAVORITE, "(EVENT_ID = {id})",
                        "id" to idEvent)
            }
            view.showInfo("Remove from Favorite")
        } catch (e: SQLiteConstraintException){
            view.showInfo(e.localizedMessage)
        }
    }


}