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

    private var userInfoCheck : Boolean = false;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // 파이어베이스 테스트용
        val userIdClass = application as userId
        val userId = userIdClass.userId
        if (userId != null) {
            // 안드로이드 파이어베이스 - 파이어 스토어에 임의의 정보 저장
            val db = Firebase.firestore
            // 유저 정보 보내기
            /*
            val userInfo = hashMapOf(
                "userID" to userId,
                "userAge" to 60,
                "userDisease" to listOf("diabetes"),
                "userAllergic" to listOf("wheat", "soy")
            )

            db.collection("userInfo")
                .add(userInfo)
                .addOnSuccessListener { documentReference ->
                    Log.d("FIRESTORE : ", "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w("FIRESTORE : ", "Error adding document", e)
                }

             */

            // 유저 정보 받아오기 - userId가 일치하는 경우에만!!
            db.collection("userInfo")
                .whereEqualTo("userID", userId)
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        Log.d("FIRESTORE : ", "getDataSuccess")
                        userInfoCheck = true

                    }
                }
                .addOnFailureListener { exception ->
                    Log.w("FIRESTORE : ", "Error getting documents.", exception)
                    userInfoCheck = false
                }

        }
        else {
            Toast.makeText(this@HomeActivity, "userId not exist", Toast.LENGTH_LONG).show()
        }


        val filmButton : Button = findViewById(R.id.filmButton)
        val todayNutritionButton : Button = findViewById(R.id.todayNutritionButton)
        val exitButton : Button = findViewById(R.id.exitButton)
        val myProfileButton : Button = findViewById(R.id.myProfileButton)

        // 내 질환정보 수정하기 클릭 시...정보가 없으면 정보 등록 페이지로 넘어가도록 함
        myProfileButton.setOnClickListener {
            if (userInfoCheck) {
                val intent = Intent(this, UserInfoActivity::class.java)
                //Toast.makeText(this@HomeActivity, "TRUE", Toast.LENGTH_LONG).show()
                startActivity(intent)
            }
            else {
                val intent = Intent(this, UserInfoRegisterActivity::class.java)
                //Toast.makeText(this@HomeActivity, "FALSE", Toast.LENGTH_LONG).show()
                startActivity(intent)
            }
        }

        filmButton.setOnClickListener {
            val intent = Intent(this, FilmCautionActivity::class.java)
            startActivity(intent)
        }

        


    }
}