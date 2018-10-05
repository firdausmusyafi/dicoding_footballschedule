package com.dicoding.footballmatchschedule.main

import android.content.Context
import com.dicoding.footballmatchschedule.api.ApiRepository
import com.dicoding.footballmatchschedule.api.TheSportDBApi
import com.dicoding.footballmatchschedule.db.database
import com.dicoding.footballmatchschedule.model.Events
import com.dicoding.footballmatchschedule.model.Favorite
import com.dicoding.footballmatchschedule.model.Leagues
import com.google.gson.Gson
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by Firdaus Musyafi on 9/13/18.
 */

class MainPresenter(private val view: MainView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson){
    fun getLeague() {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getLeague()),
                    Leagues::class.java
            )
//
            uiThread {
                view.hideLoading()
                view.showLeague(data)
            }
        }
    }

    fun getLast15(idLeagues: String){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getLast15(idLeagues)),
                    Events::class.java
            )
//
            uiThread {
                view.hideLoading()
                view.showEvent(data)
            }
        }
    }

    fun getNext15(idLeagues: String){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getNext15(idLeagues)),
                    Events::class.java
            )
//
            uiThread {
                view.hideLoading()
                view.showEvent(data)
            }
        }
    }

    fun getFavorite(context : Context?) {
        context?.database?.use {
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            view.showFavorite(favorite)
        }
    }



}