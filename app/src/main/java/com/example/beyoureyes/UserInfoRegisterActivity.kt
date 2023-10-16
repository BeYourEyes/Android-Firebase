package com.example.beyoureyes

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.android.material.chip.Chip
import android.content.Intent


val diseaseList : List<String> = listOf("고혈압", "고지혈증", "당뇨")
val allergyList : List<String> = listOf("메밀", "밀", "콩", "호두", "땅콩", "복숭아", "토마토", "돼지고기", "난류", "우유", "닭고기", "쇠고기", "새우", "고등어", "홍합", "전복", "굴", "조개류", "게", "오징어", "아황산")

class UserInfoRegisterActivity : AppCompatActivity() {
    private var clickedDisease : MutableList<Boolean> = mutableListOf(false, false, false)
    private var clickedAllergic : MutableList<Boolean> = MutableList(21) { false }
    private val userDiseaseList : ArrayList<String> = arrayListOf()
    private val userAllergyList : ArrayList<String> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info_register)

        val age : EditText = findViewById(R.id.editAge)

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

        val userInfoRegisterButton = findViewById<Button>(R.id.userInfoRegisterButton)

        val diseaseChips = arrayOf(chip0, chip1, chip2)
        val allergyChips = arrayOf(chip00, chip01, chip02, chip03, chip04, chip05, chip06, chip07,
                                    chip08, chip09, chip10, chip11, chip12, chip13, chip14, chip15,
                                    chip16, chip17, chip18, chip19, chip20)


        for (i in diseaseChips.indices) {
            diseaseChips[i].setOnClickListener { view ->
                val chip = view as Chip

                //val chipText = chip.text.toString()
                // Toast 메시지 표시
                //Toast.makeText(this@UserInfoRegisterActivity, chipText, Toast.LENGTH_LONG).show()

                // clickedDisease 리스트 업데이트
                val index = diseaseChips.indexOf(chip)
                clickedDisease[index] = !clickedDisease[index]

                // clickedDisease의 값을 확인하여 배경색과 텍스트 색상 변경
                if (clickedDisease[index]) {
                    // 클릭되었을 때의 처리
                    chip.setChipBackgroundColorResource(R.color.red)
                    chip.setTextColor(Color.WHITE)
                } else {
                    // 클릭이 해제되었을 때의 처리
                    // 원래의 배경색과 텍스트 색상을 복원하려면 해당 색상 리소스 ID를 사용하세요.
                    chip.setChipBackgroundColorResource(R.color.white)
                    chip.setTextColor(Color.BLACK) // 원래의 텍스트 색상을 복원
                }
            }
        }

        for (i in allergyChips.indices) {
            allergyChips[i].setOnClickListener { view ->
                val chip = view as Chip

                //val chipText = chip.text.toString()
                //Toast.makeText(this@UserInfoRegisterActivity, chipText, Toast.LENGTH_LONG).show()


                // clickedDisease 리스트 업데이트
                val index = allergyChips.indexOf(chip)
                clickedAllergic[index] = !clickedAllergic[index]

                // clickedDisease의 값을 확인하여 배경색과 텍스트 색상 변경
                if (clickedAllergic[index]) {
                    // 클릭되었을 때의 처리
                    chip.setChipBackgroundColorResource(R.color.red)
                    chip.setTextColor(Color.WHITE)
                } else {
                    // 클릭이 해제되었을 때의 처리
                    // 원래의 배경색과 텍스트 색상을 복원하려면 해당 색상 리소스 ID를 사용하세요.
                    chip.setChipBackgroundColorResource(R.color.white)
                    chip.setTextColor(Color.BLACK) // 원래의 텍스트 색상을 복원
                }
            }
        }


        val userIdClass = application as userId
        val userId = userIdClass.userId

        userInfoRegisterButton.setOnClickListener {
            if(age.text.toString() == ""){
                Toast.makeText(this@UserInfoRegisterActivity, "나이를 입력해주세요!", Toast.LENGTH_LONG).show()
            }
            else {
                for( index in clickedDisease.indices ) {
                    if(clickedDisease[index])
                        userDiseaseList.add(diseaseList[index])
                }
                for ( index in clickedAllergic.indices ) {
                    if(clickedAllergic[index])
                        userAllergyList.add(allergyList[index])
                }

                Log.d("LIST: ", userDiseaseList.toString())
                Log.d("LIST: ", userAllergyList.toString())


                val db = Firebase.firestore
                //유저 정보 보내기
                val userInfo = hashMapOf(
                    "userID" to userId,
                    "userAge" to age.text.toString().toInt(),
                    "userDisease" to userDiseaseList,
                    "userAllergic" to userAllergyList
                )

                db.collection("userInfo")
                    .add(userInfo)
                    .addOnSuccessListener { documentReference ->
                        Log.d("FIRESTORE : ", "DocumentSnapshot added with ID: ${documentReference.id}")
                    }
                    .addOnFailureListener { e ->
                        Log.w("FIRESTORE : ", "Error adding document", e)
                    }
                userDiseaseList.clear()
                userAllergyList.clear()

                val intent = Intent(this, UserInfoActivity::class.java)
                startActivity(intent)

            }
        }

    }
}