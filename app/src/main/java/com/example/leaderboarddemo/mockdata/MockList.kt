package com.example.leaderboarddemo.mockdata

class MockList {
    fun getList() : List<MockData>{
        val people = mutableListOf<MockData>()

        people.add(MockData("Ahmed", "Mahmoud", 1234, 1))
        people.add(MockData("Fatima", "Ali", 5678,2))
        people.add(MockData("Omar", "Hassan", 9012,3))
        people.add(MockData("Aisha", "Khan", 3456, 4))
        people.add(MockData("Youssef", "Ibrahim", 7890,5))
        people.add(MockData("Layla", "Salah", 2345,6))
        people.add(MockData("Mohammed", "Abdullah", 6789,7))
        people.add(MockData("Noor", "Hussein", 123,8))
        people.add(MockData("Ali", "Mansour", 4567,9))
        people.add(MockData("Sara", "Khaled", 8901,10))

        return people

    }
}