package com.dicoding.footballmatchschedule.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Firdaus Musyafi on 9/14/18.
 */
data class Events(
        @SerializedName("events")
        val events: List<Events.Event>){

    data class Event(@SerializedName("idEvent") val eventId: String? = null,
                     @SerializedName("strEvent") val eventName: String? = null,
                     @SerializedName("strLeague") val leagueName: String? = null,
                     @SerializedName("strHomeTeam") val homeTeam: String? = null,
                     @SerializedName("strAwayTeam") val awayTeam: String? = null,
                     @SerializedName("intHomeScore") val homeScore: Int? = null,
                     @SerializedName("intAwayScore") val awayScore: Int? = null,
                     @SerializedName("intRound") val round: Int? = null,
                     @SerializedName("strHomeGoalDetails") val homeGoalDetails: String? = null,
                     @SerializedName("strHomeRedCards") val homeRedCard: String? = null,
                     @SerializedName("strHomeYellowCards") val homeYellowCard: String? = null,
                     @SerializedName("strHomeLineupGoalkeeper") val homeLineupGoalKeeper: String? = null,
                     @SerializedName("strHomeLineupDefense") val homeLineupDefense: String? = null,
                     @SerializedName("strHomeLineupMidfield") val homeLineupMidfield: String? = null,
                     @SerializedName("strHomeLineupForward") val homeLineupForward: String? = null,
                     @SerializedName("strHomeLineupSubstitutes") val homeLineupSubstitutes: String? = null,
                     @SerializedName("strHomeFormation") val homeFormation: String? = null,
                     @SerializedName("strAwayRedCards") val awayRedCard: String? = null,
                     @SerializedName("strAwayYellowCards") val awayYellowCard: String? = null,
                     @SerializedName("strAwayGoalDetails") val awayGoalDetails: String? = null,
                     @SerializedName("strAwayLineupGoalkeeper") val awayLineupGoalkeeper: String? = null,
                     @SerializedName("strAwayLineupDefense") val awayLineupDefense: String? = null,
                     @SerializedName("strAwayLineupMidfield") val awayLineupMidfield: String? = null,
                     @SerializedName("strAwayLineupForward") val awayLineupForward: String? = null,
                     @SerializedName("strAwayLineupSubstitutes") val awayLineupSubstitutes: String? = null,
                     @SerializedName("strAwayFormation") val awayFormation: String? = null,
                     @SerializedName("intHomeShots") val homeShots: String? = null,
                     @SerializedName("intAwayShots") val awayShots: String? = null,
                     @SerializedName("dateEvent") val dateEvent: String? = null,
                     @SerializedName("strDate") val date: String? = null,
                     @SerializedName("strTime") val time: String? = null,
                     @SerializedName("strTVStation") val tvStation: String? = null,
                     @SerializedName("idHomeTeam") val homeTeamId: String? = null,
                     @SerializedName("idAwayTeam") val awayTeamId: String? = null)
}


