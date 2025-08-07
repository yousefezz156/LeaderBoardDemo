package com.example.leaderboardscreenmodule.leaderboard

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.leaderboardscreenmodule.leaderboard.presentation.s.mockdata.MockData
import com.example.leaderboardscreenmodule.leaderboard.presentation.s.mockdata.MockList
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun filterList(name: String, fromDate: String, tooDate: String): List<MockData> {
//    val fromDay = fromDate?.substring(0,2)!!.toInt()
//    val fromMonth = fromDate.substring(3,5).toInt()
//    val fromYear = fromDate.substring(6,10).toInt()
//    val tooDay = tooDate?.substring(0,2)!!.toInt()
//    val tooMonth = tooDate.substring(3,5).toInt()
//    val tooYear = tooDate.substring(6,10).toInt()

    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

    val from = if (fromDate.isNotBlank()) LocalDate.parse(fromDate, formatter) else null
    val too = if (tooDate.isNotBlank()) LocalDate.parse(tooDate, formatter) else null

    val list = MockList().getList()


    //Log.d("fakeDate", "date, month, years ${ MockList().getList(). } ${ fromMonth } ${ fromYear}")
    Log.d("fromDay", "from $from")
    Log.d("tooDay", "too $too")



    if (name.isNotBlank() && fromDate.isNotBlank() && tooDate.isNotBlank() && from !=null && too!=null) {
        val fullList = MockList().getList()
            .filter { item ->
                val mockDate = LocalDate.parse(item.date, formatter)

                item.first_name.contains(name, ignoreCase = true) && mockDate in from..too }
        return fullList
    }
    if (name.isNotBlank()) {
        val nameList = MockList().getList().filter { it.first_name.contains(name,ignoreCase = true) }
        return nameList
    }
    if (fromDate.isNotBlank() && tooDate.isNotBlank() && from !=null && too!=null) {
        val dateList = MockList().getList()
            .filter {item ->
                val mockDate = LocalDate.parse(item.date, formatter)
                mockDate in from..too
            }
        return dateList
    }

    return list

}