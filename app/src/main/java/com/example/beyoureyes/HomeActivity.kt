package com.example.beyoureyes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {

    private var userInfoCheck : Int = 0;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        Log.d("HOMEFIRESTORE : ", "success0")

        // 파이어베이스 테스트용
        val userIdClass = application as userId
        val userId = userIdClass.userId
        if (userId != null) {
            // 안드로이드 파이어베이스 - 파이어 스토어에 임의의 정보 저장
            val db = Firebase.firestore
            // 유저 정보 받아오기 - userId가 일치하는 경우에만!!
            db.collection("userInfo")
                .whereEqualTo("userID", userId)
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val result = task.result
                        // 유저 정보가 이미 존재하는 경우
                        if (result != null && !result.isEmpty) {
                            Log.d("HOMEFIRESTORE : ", "getDataSuccess_exist")
                            userInfoCheck = 1
                        }
                        // 유저 정보가 이미 존재하는 경우
                        else {
                            Log.d("HOMEFIRESTORE : ", "getDataSuccess_not exist")
                            userInfoCheck = -1
                        }
                    } else {
                        // 쿼리 중에 예외가 발생한 경우
                        Log.d("HOMEFIRESTORE : ", "Error getting documents.", task.exception)
                        userInfoCheck = 0
                    }
                }


        }
        else {
            Log.d("HOMEFIRESTORE : ", "Error getting documents.")
            Toast.makeText(this@HomeActivity, "userId not exist", Toast.LENGTH_LONG).show()
        }


        val filmButton : Button = findViewById(R.id.filmButton)
        val todayIntakeButton : Button = findViewById(R.id.todayNutritionButton)
        val exitButton : Button = findViewById(R.id.exitButton)
        val userInfoButton : Button = findViewById(R.id.myProfileButton)

        // 내 질환정보 수정하기 클릭 시...정보가 없으면 정보 등록 페이지로 넘어가도록 함
        userInfoButton.setOnClickListener {
            if (userInfoCheck == 1) {
                val intent = Intent(this, UserInfoActivity::class.java)
                Log.d("HOMEFIRESTORE : ", "success_1")
                //Toast.makeText(this@HomeActivity, "TRUE", Toast.LENGTH_LONG).show()
                startActivity(intent)
            }
            else if (userInfoCheck == -1){
                val intent = Intent(this, UserInfoRegisterActivity::class.java)
                Log.d("HOMEFIRESTORE : ", "success_-1")
                //Toast.makeText(this@HomeActivity, "FALSE", Toast.LENGTH_LONG).show()
                startActivity(intent)
            }
            else {
                Log.d("HOMEFIRESTORE : ", "failure_0")
                val intent = Intent(this, UserInfoRegisterActivity::class.java)
                //Toast.makeText(this@HomeActivity, "FALSE", Toast.LENGTH_LONG).show()
                startActivity(intent)
            }
        }

        filmButton.setOnClickListener {
            val intent = Intent(this, FilmCautionActivity::class.java)
            startActivity(intent)
        }
        
        todayIntakeButton.setOnClickListener {
            val intent = Intent(this, TodayIntakeActivity::class.java)
            startActivity(intent)
        }

    }
}