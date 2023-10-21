package com.example.beyoureyes

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.ColorTemplate.COLORFUL_COLORS
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ScanSuccessActivity : AppCompatActivity() {
    // 사용자 알러지 리스트
    private val userAllergyList : ArrayList<String> = arrayListOf()

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            System.exit(0)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_success)

        //// 스캔 성공 결과 가져오기
        //// 로컬 클래스에 저장하고, 로컬에 저장되어 있던 부분 가져와서 칼로리, 원형 차트, 알러지 부분 추가해주기
        //// 칼로리
        val kcalText : TextView = findViewById(R.id.textViewKcalScanSuccess) // 총 칼로리
        val kcalPerTimeText : TextView = findViewById(R.id.textViewKcalPerTimeScanSuccess) // 1회당 칼로리

        //// 원형차트

        val chart = findViewById<PieChart>(R.id.pieChartScanSuccess)
        chart.setUsePercentValues(true)
        val entries = ArrayList<PieEntry>()
        entries.add(PieEntry(708f, "탄수화물"))
        entries.add(PieEntry(400f, "당류"))
        entries.add(PieEntry(950f, "단백질"))
        entries.add(PieEntry(508f, "지방"))
        entries.add(PieEntry(270f, "기타"))

        // 색상 추가하기
        val colorsItems = ArrayList<Int>()
        for (c in ColorTemplate.VORDIPLOM_COLORS) colorsItems.add(c)
        for (c in ColorTemplate.JOYFUL_COLORS) colorsItems.add(c)
        for (c in COLORFUL_COLORS) colorsItems.add(c)
        for (c in ColorTemplate.LIBERTY_COLORS) colorsItems.add(c)
        for (c in ColorTemplate.PASTEL_COLORS) colorsItems.add(c)
        colorsItems.add(ColorTemplate.getHoloBlue())

        // 색상 리스트를 만들고 색상을 추가
        val colorsList = ArrayList<Int>()
        colorsList.add(Color.RED)
        colorsList.add(Color.WHITE)
        colorsList.add(Color.BLUE)

        val pieDataSet = PieDataSet(entries, "")
        pieDataSet.apply {
            // Piechart 속 파이들 색상 설청
            colors = colorsItems
            // 값(백분율)에 대한 색상 설정
            valueTextColor = Color.BLACK
            // 값에 대한 크기 설정
            valueTextSize = 10f

        }

        val pieData = PieData(pieDataSet)
        // 값에 사용자 정의 형식(백분율 값 + "%") 설정


        chart.apply {
            data = pieData
            description.isEnabled = false // 차트 설명 비활성화
            isRotationEnabled = false // 차트 회전 활성화
            legend.isEnabled = false // 하단 설명 비활성화
            isDrawHoleEnabled = true // 가운데 빈 구멍 활성화 비활성화 여부
            holeRadius = 20f // 가운데 빈 구멍 크기
            transparentCircleRadius = 40f // 투명한 부분 크기
            centerText = null // 가운데 텍스트 없앰
            setEntryLabelColor(Color.BLACK) // label 색상
            animateY(1400, Easing.EaseInOutQuad) // 1.4초 동안 애니메이션 설정
            animate()
        }

        //// 알러지
        // 칩 동적 추가
        val allergyChipGroup : ChipGroup = findViewById<ChipGroup>(R.id.allergyChipGroup)
        userAllergyList.add("새우") // 알러지 정보 리스트에 추가
        for (diseaseItem in userAllergyList) {
            val chip = Chip(this)
            chip.text = diseaseItem
            chip.setChipBackgroundColorResource(R.color.red)
            chip.setTextColor(Color.WHITE)
            allergyChipGroup.addView(chip)
        }

        //// 질환 별 정보 보기 -> 내 질환정보 불러오고, 그에 따른 유의 사항 보여주기
        //// 음성으로 듣기 버튼 -> 해당 정보 읽어주기
        //// 먹을게요! 버튼 -> 팝업창 띄우기

        val intakeButton : Button = findViewById(R.id.buttonIntake)

        intakeButton.setOnClickListener {
            Toast.makeText(this, "아직 다 못만들었어용;;ㅎㅎ", Toast.LENGTH_LONG).show()
        }

        // 돌아가기 버튼 -> 홈으로 돌아가기
        val buttonToHome : Button = findViewById(R.id.buttonGoHomeScanSuccess)
        buttonToHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        val userIdClass = application as userId
        val userId = userIdClass.userId
        val db = Firebase.firestore

    }

}