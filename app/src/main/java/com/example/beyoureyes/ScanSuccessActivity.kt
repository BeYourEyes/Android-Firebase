package com.example.beyoureyes

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.ColorTemplate.COLORFUL_COLORS
import com.google.android.material.chip.Chip
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ScanSuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_success)

        // 스캔 성공 결과 가져오기
        // 로컬 클래스에 저장하고, 로컬에 저장되어 있던 부분 가져와서 칼로리, 원형 차트, 알러지 부분 추가해주기
        // 칼로리
        // 원형차트

        val chart = findViewById<PieChart>(R.id.pieChartScanSuccess)
        chart.setUsePercentValues(true)
        val entries = ArrayList<PieEntry>()
        entries.add(PieEntry(508f, "탄수화물"))
        entries.add(PieEntry(600f, "당류"))
        entries.add(PieEntry(750f, "단백질"))
        entries.add(PieEntry(508f, "지방"))
        entries.add(PieEntry(670f, "기타"))

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
            description.isEnabled = false
            isRotationEnabled = false
            centerText = "This is Center"
            setEntryLabelColor(Color.BLACK)
            animateY(1400, Easing.EaseInOutQuad)
            animate()
        }
        // 알러지
        // 질환 별 정보 보기 -> 내 질환정보 불러오고, 그에 따른 유의 사항 보여주기
        // 음성으로 듣기 버튼 -> 해당 정보 읽어주기
        // 먹을게요! 버튼 -> 팝업창 띄우기
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