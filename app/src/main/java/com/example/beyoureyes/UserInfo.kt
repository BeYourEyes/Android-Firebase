package com.example.beyoureyes

class UserInfo {
    private lateinit var userId : String
    private var userName : String = "" // 사용자 이름
    private var userAge : Int? = null // 사용자 나이
    private var userGender : Int? = null // 사용자 성별
    private var userDisease : Array<String>? = null // 사용자 질병 정보(nullable)
    private var userAllergic : Array<String>? = null // 사용자 알레르기 정보(nullable)

    constructor(userName : String, userAge : Int?, userGender : Int?){
        this.userName = userName
        this.userAge = userAge
        this.userGender = userGender
    }

}