package com.example.leaderboarddemo.mockdata

import com.example.leaderboarddemo.ui.theme.backgroundColorsForWhiteText

class MockList {
    fun getList() : List<MockData>{
        val people = mutableListOf<MockData>()

        people.add(MockData("Ahmed", "Mahmoud", 1234, 1, colors = backgroundColorsForWhiteText.random()))
        people.add(MockData("Fatima", "Ali", 5678,2,colors = backgroundColorsForWhiteText.random()))
        people.add(MockData("Omar", "Hassan", 9012,3,colors = backgroundColorsForWhiteText.random()))
        people.add(MockData("Aisha", "Khan", 3456, 4,colors = backgroundColorsForWhiteText.random()))
        people.add(MockData("Youssef", "Ibrahim", 7890,5,colors = backgroundColorsForWhiteText.random()))
        people.add(MockData("Layla", "Salah", 2345,6,colors = backgroundColorsForWhiteText.random()))
        people.add(MockData("Mohammed", "Abdullah", 6789,7,colors = backgroundColorsForWhiteText.random()))
        people.add(MockData("Noor", "Hussein", 123,8,colors = backgroundColorsForWhiteText.random()))
        people.add(MockData("Ali", "Mansour", 4567,9,colors = backgroundColorsForWhiteText.random()))
        people.add(MockData("Sara", "Khaled", 8901,10,colors = backgroundColorsForWhiteText.random()))

        return people

    }
}