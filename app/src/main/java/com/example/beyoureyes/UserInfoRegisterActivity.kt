package com.example.beyoureyes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class UserInfoRegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info_register)


        val chipGroup = findViewById<ChipGroup>(R.id.diseaseChipGroup)

        chipGroup.setOnCheckedChangeListener { group, checkedId ->
            val chip = findViewById<Chip>(checkedId)
            if (chip != null) {
                // 이 부분에서 선택된 Chip에 대한 작업을 수행할 수 있습니다.
                val selectedText = chip.text.toString()
                // 예: 선택된 Chip의 텍스트를 로그에 출력
                println("선택된 Chip: $selectedText")
            }
        }



        val userIdClass = application as userId
        val userId = userIdClass.userId

        val db = Firebase.firestore

        /*
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

         */


    }
}