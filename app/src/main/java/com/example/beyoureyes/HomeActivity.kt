package com.example.beyoureyes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val db = Firebase.firestore

        val userInfo = hashMapOf(
            "userID" to "1234567890",
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
}