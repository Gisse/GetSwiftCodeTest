package com.djevtic.myswifttestcode.utils

object TimeUtils {

    /**
     * Check if provided timestamp is more then 23 hours in past
     */
    fun didDayPassed(time : Long): Boolean{
        val timePassed = System.currentTimeMillis() - time
        val hoursPassed = timePassed / 3600
        return hoursPassed > 23
    }

}