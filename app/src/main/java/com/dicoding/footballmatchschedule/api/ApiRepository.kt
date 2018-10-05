package com.dicoding.footballmatchschedule.api

import com.dicoding.footballmatchschedule.BuildConfig
import retrofit2.http.GET
import java.net.URL

/**
 * Created by Firdaus Musyafi on 9/13/18.
 */
class ApiRepository{
//    @GET("api/v1/json/${BuildConfig.TSDB_API_KEY}/all_leagues.php")
//    fun getTeams() : List<League>

    fun doRequest(url: String): String {
        return URL(url).readText()
    }
}