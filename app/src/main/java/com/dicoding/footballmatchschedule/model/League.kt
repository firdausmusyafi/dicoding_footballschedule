package com.dicoding.footballmatchschedule.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Firdaus Musyafi on 9/13/18.
 */
data class Leagues(@SerializedName("countrys")
                   val leagues: List<League>
){
    data class League( @SerializedName("idLeague")
                       val leagueId: String? = null,

                       @SerializedName("strLeague")
                       val leagueName : String? = null)

}