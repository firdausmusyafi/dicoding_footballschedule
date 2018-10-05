package com.dicoding.footballmatchschedule.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Firdaus Musyafi on 9/17/18.
 */
data class Teams(@SerializedName("teams") val teams: List<Team>){
    data class Team(@SerializedName("strTeam") val name:String,
                    @SerializedName("strTeamBadge") val logo:String)
}