package com.example.beyoureyes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UserInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)


        val userIdClass = application as userId
        val userId = userIdClass.userId
        val db = Firebase.firestore
        db.collection("userInfo")
            .whereEqualTo("userID", userId)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("FIRESTORE : ", "${document.id} => ${document.data}")
                    Toast.makeText(this@UserInfoActivity, "${document.id} => ${document.data}", Toast.LENGTH_LONG).show()
                }
            }
            .addOnFailureListener { exception ->
                Log.w("FIRESTORE : ", "Error getting documents.", exception)
            }


    }
}