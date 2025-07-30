package com.example.leaderboardscreenmodule.leaderboard.mockdata

import androidx.compose.ui.graphics.toArgb
import com.example.leaderboardscreenmodule.theme.backgroundColorsForWhiteText

class MockList {
    fun getList() : List<MockData>{
        val people = mutableListOf<MockData>()

        people.add(MockData("Ahmed", "Mahmoud", 1234, 1, colors = backgroundColorsForWhiteText.random().toArgb(), date = "28-06-2025"))
        people.add(MockData("Fatima", "Ali", 5678,2,colors = backgroundColorsForWhiteText.random().toArgb(),date = "29-06-2025"))
        people.add(MockData("Omar", "Hassan", 9012,3,colors = backgroundColorsForWhiteText.random().toArgb(), date = "30-06-2025"))
        people.add(MockData("Aisha", "Khan", 3456, 4,colors = backgroundColorsForWhiteText.random().toArgb(), date = "28-07-2025"))
        people.add(MockData("Yousef", "Ibrahim", 7890,5,colors = backgroundColorsForWhiteText.random().toArgb(),date = "28-06-2024"))
        people.add(MockData("Layla", "Salah", 2345,6,colors = backgroundColorsForWhiteText.random().toArgb(), date = "20-07-2025"))
        people.add(MockData("Mohammed", "Abdullah", 6789,7,colors = backgroundColorsForWhiteText.random().toArgb(), date = "20-06-2025"))
        people.add(MockData("Noor", "Hussein", 123,8,colors = backgroundColorsForWhiteText.random().toArgb(), date = "27-07-2025"))
        people.add(MockData("Ali", "Mansour", 4567,9,colors = backgroundColorsForWhiteText.random().toArgb(), date = "20-08-2025"))
        people.add(MockData("Sara", "Khaled", 8901,10,colors = backgroundColorsForWhiteText.random().toArgb(), date = "23-07-2025"))
        people.add(MockData("Ahmed", "Mahmoud", 5234, 11, colors = backgroundColorsForWhiteText.random().toArgb(), date = "30-07-2025"))
        people.add(MockData("Yousef", "Mahmoud", 3434, 12, colors = backgroundColorsForWhiteText.random().toArgb(), date = "30-08-2025"))

        return people

    }
}