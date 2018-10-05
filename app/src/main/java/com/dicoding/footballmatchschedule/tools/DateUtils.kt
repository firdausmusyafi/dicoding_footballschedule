package com.dicoding.footballmatchschedule.tools

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Firdaus Musyafi on 9/17/18.
 */
fun formatDate(formatin: String, formatout: String, date: String): String {
    val format1 = SimpleDateFormat(formatin)
    val format2 = SimpleDateFormat(formatout)
    var formatteddate = ""
    try {
        formatteddate = format2.format(format1.parse(date))
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    return formatteddate
}

fun formatDate(date: Date, formatout: String): String {
    val format2 = SimpleDateFormat(formatout)
    var formatteddate = ""
    formatteddate = format2.format(date)
    return formatteddate
}