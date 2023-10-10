package com.example.beyoureyes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.chip.Chip
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UserInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        val infoAge = findViewById<TextView>(R.id.infoAge)

        val chip0 = findViewById<Chip>(R.id.chip0)
        val chip1 = findViewById<Chip>(R.id.chip1)
        val chip2 = findViewById<Chip>(R.id.chip2)

        val chip00 = findViewById<Chip>(R.id.chip2_0)
        val chip01 = findViewById<Chip>(R.id.chip2_1)
        val chip02 = findViewById<Chip>(R.id.chip2_2)
        val chip03 = findViewById<Chip>(R.id.chip2_3)
        val chip04 = findViewById<Chip>(R.id.chip2_4)
        val chip05 = findViewById<Chip>(R.id.chip2_5)
        val chip06 = findViewById<Chip>(R.id.chip2_6)
        val chip07 = findViewById<Chip>(R.id.chip2_7)
        val chip08 = findViewById<Chip>(R.id.chip2_8)
        val chip09 = findViewById<Chip>(R.id.chip2_9)
        val chip10 = findViewById<Chip>(R.id.chip2_10)
        val chip11 = findViewById<Chip>(R.id.chip2_11)
        val chip12 = findViewById<Chip>(R.id.chip2_12)
        val chip13 = findViewById<Chip>(R.id.chip2_13)
        val chip14 = findViewById<Chip>(R.id.chip2_14)
        val chip15 = findViewById<Chip>(R.id.chip2_15)
        val chip16 = findViewById<Chip>(R.id.chip2_16)
        val chip17 = findViewById<Chip>(R.id.chip2_17)
        val chip18 = findViewById<Chip>(R.id.chip2_18)
        val chip19 = findViewById<Chip>(R.id.chip2_19)
        val chip20 = findViewById<Chip>(R.id.chip2_20)

        val userInfoChangeButton = findViewById<Button>(R.id.userInfoChangeButton)

        val diseaseChips = arrayOf(chip0, chip1, chip2)
        val allergyChips = arrayOf(chip00, chip01, chip02, chip03, chip04, chip05, chip06, chip07,
            chip08, chip09, chip10, chip11, chip12, chip13, chip14, chip15,
            chip16, chip17, chip18, chip19, chip20)



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
                    if(document.id.equals("userAge")) {
                        infoAge.text = document.data.toString()
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.w("FIRESTORE : ", "Error getting documents.", exception)
            }

        userInfoChangeButton.setOnClickListener {
            val intent = Intent(this, UserInfoRegisterActivity::class.java)
            startActivity(intent)
        }

    }
}