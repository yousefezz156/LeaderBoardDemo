package com.example.leaderboardscreenmodule.leaderboard

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerChooser(isDateTextTo:Boolean, dateTextTo:String, dateTextFrom:String, onConfirm: (DatePickerState) -> Unit, onDismiss: () -> Unit) {
    val dateFormatter = SimpleDateFormat("dd-MM-yyyy", Locale.US)

    val fromDateMillis: Long? = try {
        val calendar = Calendar.getInstance()
        calendar.time = dateFormatter.parse(dateTextFrom)!!
        calendar.timeInMillis
    } catch (e: Exception) {
        null
    }


    val toDateMills :Long? = try{
        val calendar = Calendar.getInstance()
        calendar.time = dateFormatter.parse(dateTextTo)!!
        calendar.timeInMillis
    }catch (e: Exception){
        null
    }


    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = System.currentTimeMillis(),
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return if (isDateTextTo && fromDateMillis != null) {
                    utcTimeMillis >= fromDateMillis
                } else if(!isDateTextTo && toDateMills  !=null ){
                    utcTimeMillis<=toDateMills
                }else{
                    true
                }
            }
        }
    )

    DatePickerDialog(onDismissRequest = {},
        confirmButton = {
            TextButton(onClick = { onConfirm(datePickerState) }) {
                Text(text = "Ok")

            }


        },
        dismissButton = {

            TextButton(onClick = { onDismiss() }) {
                Text(text = "Cancel")

            }
        }

    ) {

        DatePicker(state = datePickerState)
    }
}