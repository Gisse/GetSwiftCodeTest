package com.djevtic.myswifttestcode.utils

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

object TimeUtils {

    /**
     * Check if provided timestamp is more then 23 hours in past
     */
    fun didDayPassed(time : Long): Boolean {
        val timePassed = System.currentTimeMillis()/1000 - time/1000
        val hoursPassed = timePassed / 3600
        return hoursPassed > 23
    }

    fun countAge(dateData : String) : String {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val date = sdf.parse(dateData)
        var calendar = Calendar.getInstance()
        calendar.time = date
        var calendar2 = Calendar.getInstance()
        calendar2.timeInMillis = System.currentTimeMillis()
        return (calendar2.get(Calendar.YEAR) - calendar.get(Calendar.YEAR)).toString()
    }

}