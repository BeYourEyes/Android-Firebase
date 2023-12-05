package com.example.beyoureyes

import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet


class TodayIntakeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_today_intake)

        // HorizontalBarChart 초기화
        val carboBarChart = findViewById<HorizontalBarChart>(R.id.carboBarchart)
        val fatBarChart = findViewById<HorizontalBarChart>(R.id.fatBarchart)
        val proteinBarChart = findViewById<HorizontalBarChart>(R.id.proBarchart)
        val naBarChart = findViewById<HorizontalBarChart>(R.id.naBarchart)
        val choleBarChart = findViewById<HorizontalBarChart>(R.id.choleBarchart)

        // 바 차트의 데이터 설정
        val entries = arrayListOf<BarEntry>()
        entries.add(BarEntry(24f, 72f))

        applyBarChart(carboBarChart, entries,"#FF0000")
        applyBarChart(fatBarChart, entries, "#F1BC00")
        applyBarChart(proteinBarChart, entries, "#34CA00")
        applyBarChart(naBarChart, entries, "#34CA00")
        applyBarChart(choleBarChart, entries, "#34CA00")
    }

    private fun applyBarChart(barChart: HorizontalBarChart, entries: List<BarEntry>, color : String) {
        // 바 차트의 데이터셋 생성
        val dataSet = BarDataSet(entries, "My Data")
        dataSet.color = Color.parseColor(color)

        dataSet.setDrawValues(false);

        // 바 차트의 X축 레이블 설정
        val labels = arrayListOf<String>()
        labels.add("Label 1")

        // 바 차트의 X축, Y축 설정
        val xAxis = barChart.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(labels)

        // 바 차트의 데이터 설정
        val data = BarData(dataSet)
        barChart.data = data

        // 바 차트의 다양한 설정 (예시)
        barChart.description.isEnabled = false
        barChart.setDrawValueAboveBar(true)
        barChart.legend.isEnabled = false // 레전드 삭제
        barChart.description.isEnabled = false // 차트의 설명 비활성화
        barChart.setDrawGridBackground(false) // 그리드 배경 비활성화
        barChart.axisLeft.isEnabled = false // 왼쪽 Y축 비활성화
        barChart.axisRight.isEnabled = false // 오른쪽 Y축 비활성화
        barChart.legend.isEnabled = false // 범례 비활성화


        // 레이아웃 파라미터 설정 (예시)
        val layoutParams = barChart.layoutParams
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT



        // 배경에 둥근 모서리 적용 (예시)
        //barChart.setBackgroundResource(R.drawable.rounded_corner_horizontal_barchart)

        // 바 차트 갱신
        barChart.layoutParams = layoutParams
        barChart.invalidate()
    }

}