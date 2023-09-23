package com.example.beyoureyes

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

lateinit var userAuth : FirebaseAuth
lateinit var userFireStore : FirebaseFirestore
lateinit var userStorage : FirebaseStorage


class UserInfoRegisterActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info_register)

       val button : Button = findViewById(R.id.button)

        button.setOnClickListener {
            val intent = Intent(this, SplashActivity::class.java)

            startActivity(intent)
        }

    }

    private fun signup(userName: String, userAge: Int, userGender: Int) {
        // Firebase 사용자 인증 확인
        val currentUser = userAuth.currentUser
        if (currentUser != null) {
            // 사용자 정보를 Firestore에 저장
            val user = UserInfo(userName, userAge, userGender)

            // Firestore에 사용자 정보 저장
            userFireStore.collection("users")
                .document(currentUser.uid)
                .set(user)
                .addOnSuccessListener {
                    // 사용자 정보 저장 성공
                    Toast.makeText(this, "사용자 정보가 성공적으로 저장되었습니다.", Toast.LENGTH_SHORT).show()
                    // 원하는 화면으로 이동
                    // 예: startActivity(Intent(this, YourNextActivity::class.java))
                }
                .addOnFailureListener { e ->
                    // 사용자 정보 저장 실패
                    Toast.makeText(this, "사용자 정보 저장에 실패했습니다: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        } else {
            // 사용자가 로그인되어 있지 않음
            Toast.makeText(this, "로그인되어 있지 않습니다.", Toast.LENGTH_SHORT).show()
        }


    }
}