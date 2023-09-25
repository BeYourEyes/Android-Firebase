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
            val userInfo = hashMapOf(
                "userID" to userId,
                "userAge" to 60,
                "userDisease" to listOf("diabetes"),
                "userAllergic" to listOf("wheat", "soy", "beef")
            )

            db.collection("userInfo")
                .add(userInfo)
                .addOnSuccessListener { documentReference ->
                    Log.d("FIRESTORE : ", "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w("FIRESTORE : ", "Error adding document", e)
                }



            // 유저 정보 받아오기
            db.collection("userInfo")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        Log.d("FIRESTORE : ", "${document.id} => ${document.data}")
                        Toast.makeText(this@HomeActivity, "${document.id} => ${document.data}", Toast.LENGTH_LONG).show()
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w("FIRESTORE : ", "Error getting documents.", exception)
                }

        }
        else {
            Toast.makeText(this@HomeActivity, "userId not exist", Toast.LENGTH_LONG).show()
        }


        val filmButton : Button = findViewById(R.id.filmButton)
        val todayNutritionButton : Button = findViewById(R.id.todayNutritionButton)
        val exitButton : Button = findViewById(R.id.exitButton)
        val myProfileButton : Button = findViewById(R.id.myProfileButton)

        // 내 질환정보 수정하기 클릭 시
        myProfileButton.setOnClickListener {
            val intent = Intent(this, UserInfoRegisterActivity::class.java)
            startActivity(intent)
        }

        


    }
}