package com.dicoding.footballmatchschedule.model

/**
 * Created by Firdaus Musyafi on 9/18/18.
 */
data class Favorite(val id: Long?, val eventId : String?, val eventDate : String?, val homeTeamName : String?, val homeTeamGoals: String?, val awayTeamName : String, val awayTeamGoals : String?){
    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val EVENT_ID: String = "EVENT_ID"
        const val EVENT_DATE: String = "EVENT_DATE"
        const val HOME_TEAM_NAME: String = "HOME_TEAM_NAME"
        const val HOME_TEAM_GOALS: String = "HOME_TEAM_GOALS"
        const val AWAY_TEAM_NAME: String = "AWAY_TEAM_NAME"
        const val AWAY_TEAM_GOALS: String = "AWAY_TEAM_GOALS"
    }
}