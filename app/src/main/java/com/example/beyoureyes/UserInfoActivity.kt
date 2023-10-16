package com.example.beyoureyes

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

val diseaseKoreanList : List<String> = listOf("고혈압", "고지혈증", "당뇨")
val allergyKoreanList : List<String> = listOf("메밀", "밀", "콩", "호두", "땅콩", "복숭아", "토마토", "돼지고기", "난류", "우유", "닭고기", "쇠고기", "새우", "고등어", "홍합", "전복", "굴", "조개류", "게", "오징어", "아황산")



class UserInfoActivity : AppCompatActivity() {
    private val userDiseaseList : ArrayList<String> = arrayListOf()
    private val userAllergyList : ArrayList<String> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        val diseaseChipGroup = findViewById<ChipGroup> (R.id.diseaseChipGroup)
        val allergicChipGroup  = findViewById<ChipGroup>(R.id.allergyChipGroup)

        val infoAge = findViewById<TextView>(R.id.infoAge)

        val userInfoChangeButton = findViewById<Button>(R.id.userInfoChangeButton)


        val userIdClass = application as userId
        val userId = userIdClass.userId
        val db = Firebase.firestore
        db.collection("userInfo")
            .whereEqualTo("userID", userId)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("FIRESTORE : ", "${document.id} => ${document.data}")

                    infoAge.text = document.data.get("userAge").toString() + "세"
                    val userDisease = document.data.get("userDisease") as ArrayList<String>
                    val userAllergic = document.data.get("userAllergic") as ArrayList<String>
                    if (userDisease != null) {
                        userDiseaseList.addAll(userDisease)
                        for (diseaseItem in userDiseaseList) {
                            val chip = Chip(this)
                            chip.text = diseaseItem
                            chip.setChipBackgroundColorResource(R.color.red)
                            chip.setTextColor(Color.WHITE)
                            diseaseChipGroup.addView(chip)
                        }
                    }
                    Log.d("FIRESTORE", userDiseaseList.toString())
                    if (userAllergic != null) {
                        userAllergyList.addAll(userAllergic)
                        for (allergyItem in userAllergyList) {
                            val chip = Chip(this)
                            chip.text = allergyItem
                            chip.setChipBackgroundColorResource(R.color.red)
                            chip.setTextColor(Color.WHITE)
                            allergicChipGroup.addView(chip)
                        }
                    }
                    Log.d("FIRESTORE", userAllergic.toString())
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