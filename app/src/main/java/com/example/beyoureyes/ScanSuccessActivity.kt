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
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.ColorTemplate.COLORFUL_COLORS
import com.google.android.material.chip.Chip
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ScanSuccessActivity : AppCompatActivity() {


    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            System.exit(0)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_success)

        // 스캔 성공 결과 가져오기
        // 로컬 클래스에 저장하고, 로컬에 저장되어 있던 부분 가져와서 칼로리, 원형 차트, 알러지 부분 추가해주기
        // 칼로리
        val kcalText : TextView = findViewById(R.id.textViewKcalScanSuccess)

        // 원형차트 만들기

        val chart = findViewById<PieChart>(R.id.pieChartScanSuccess)
        chart.setUsePercentValues(true)
        val entries = ArrayList<PieEntry>()
        entries.add(PieEntry(708f, "탄수화물"))
        entries.add(PieEntry(400f, "당류"))
        entries.add(PieEntry(950f, "단백질"))
        entries.add(PieEntry(508f, "지방"))
        entries.add(PieEntry(270f, "기타"))

        // add a lot of colors
        val colorsItems = ArrayList<Int>()
        for (c in ColorTemplate.VORDIPLOM_COLORS) colorsItems.add(c)
        for (c in ColorTemplate.JOYFUL_COLORS) colorsItems.add(c)
        for (c in COLORFUL_COLORS) colorsItems.add(c)
        for (c in ColorTemplate.LIBERTY_COLORS) colorsItems.add(c)
        for (c in ColorTemplate.PASTEL_COLORS) colorsItems.add(c)
        colorsItems.add(ColorTemplate.getHoloBlue())

        val pieDataSet = PieDataSet(entries, "")
        pieDataSet.apply {
            colors = colorsItems
            valueTextColor = Color.BLACK
            valueTextSize = 16f
        }

        val pieData = PieData(pieDataSet)


        chart.apply {
            data = pieData
            description.isEnabled = false // 차트 설명 비활성화
            isRotationEnabled = false // 차트 회전 활성화
            legend.isEnabled = false // 하단 설명 비활성화
            isDrawHoleEnabled = false // 가운데 빈 구멍 비활성화
            centerText = null // 가운데 텍스트 없앰
            setEntryLabelColor(Color.BLACK) // label 색상
            animateY(1400, Easing.EaseInOutQuad) // 1.4초 동안 애니메이션 설정
            animate()
        }
        // 알러지

        // 질환 별 정보 보기 -> 내 질환정보 불러오고, 그에 따른 유의 사항 보여주기
        // 음성으로 듣기 버튼 -> 해당 정보 읽어주기
        // 먹을게요! 버튼 -> 팝업창 띄우기

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