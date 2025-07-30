package com.example.leaderboardscreenmodule.leaderboard

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.util.toRange
import com.example.leaderboardscreenmodule.DumyApi.DummyDataUiModel
import com.example.leaderboardscreenmodule.leaderboard.mockdata.MockData
import com.example.leaderboardscreenmodule.leaderboard.mockdata.MockList
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun filterList(name: String?, fromDate: String?, tooDate: String?): List<MockData> {
//    val fromDay = fromDate?.substring(0,2)!!.toInt()
//    val fromMonth = fromDate.substring(3,5).toInt()
//    val fromYear = fromDate.substring(6,10).toInt()
//    val tooDay = tooDate?.substring(0,2)!!.toInt()
//    val tooMonth = tooDate.substring(3,5).toInt()
//    val tooYear = tooDate.substring(6,10).toInt()

    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

    val from = LocalDate.parse(fromDate, formatter)
    val too = LocalDate.parse(tooDate, formatter)



    //Log.d("fakeDate", "date, month, years ${ MockList().getList(). } ${ fromMonth } ${ fromYear}")
    Log.d("fromDay", "from $from")
    Log.d("tooDay", "too $too")


    if (name!!.isNotBlank() && fromDate!!.isNotBlank() && tooDate!!.isNotBlank()) {
        val fullList = MockList().getList()
            .filter { item ->
                val mockDate = LocalDate.parse(item.date, formatter)

                item.first_name.contains(name) && mockDate in from..too }
        return fullList
    }
    if (name.isNotBlank()) {
        val nameList = MockList().getList().filter { it.first_name.contains(name) }
        return nameList
    }
    if (fromDate!!.isNotBlank() && tooDate!!.isNotBlank()) {
        val dateList = MockList().getList()
            .filter {item ->
                val mockDate = LocalDate.parse(item.date, formatter)
                mockDate in from..too
            }
        return dateList
    } else {
        return MockList().getList()
    }

}