package com.example.beyoureyes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UserInfoRegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info_register)

        val userIdClass = application as userId
        val userId = userIdClass.userId

        val db = Firebase.firestore

        val testText : TextView = findViewById(R.id.test)

        // 유저 정보 받아오기 - userId가 일치하는 경우에만!!
        db.collection("userInfo")
            .whereEqualTo("userID", userId)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("FIRESTORE : ", "${document.id} => ${document.data}")
                    Toast.makeText(this@UserInfoRegisterActivity, "${document.id} => ${document.data}", Toast.LENGTH_LONG).show()
                    testText.setText("${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("FIRESTORE : ", "Error getting documents.", exception)
            }


    }
}