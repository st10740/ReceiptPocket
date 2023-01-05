package com.example.receiptpocket

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import org.w3c.dom.Attr
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class MonthPicker: LinearLayout {
    private lateinit var minusBtn: Button
    private lateinit var addBtn: Button
    private lateinit var yearTexView: TextView
    private lateinit var monthTextView: TextView

    private var defaultYear: Int = 112
    private var defaultMonth: Int = 1
    private var minYear: Int = 100
    private var minMonth: Int = 1
    private var maxYear: Int = 112
    private var maxMonth: Int = 12

    private var curYear: Int = 0
    private var curMonth: Int = 0

    constructor(context: Context): super(context){
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int): super(context, attrs) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?){
        View.inflate(context, R.layout.month_picker, this)
        descendantFocusability = ViewGroup.FOCUS_BLOCK_DESCENDANTS

        this.minusBtn = findViewById(R.id.left_arrow)
        this.addBtn = findViewById(R.id.right_arrow)
        this.yearTexView = findViewById(R.id.year_num)
        this.monthTextView = findViewById(R.id.month_num)

        this.curYear = (SimpleDateFormat("yyyy").format(Date()).toInt()) - 1911
        this.curMonth = (SimpleDateFormat("MM").format(Date()).toInt()) - 1911
        this.maxYear = (SimpleDateFormat("yyyy").format(Date()).toInt()) - 1911
        this.maxMonth = (SimpleDateFormat("MM").format(Date()).toInt()) - 1911


        this.yearTexView.text = curYear.toString()
        this.monthTextView.text = curMonth.toString()



        if(attrs != null){
            val attributes = context.theme.obtainStyledAttributes(attrs, R.styleable.MonthPicker, 0, 0)

            // 從Layout上取預設值
            this.defaultYear = attributes.getInt(R.styleable.MonthPicker_default_year, this.defaultYear)
            this.defaultMonth = attributes.getInt(R.styleable.MonthPicker_default_month, this.defaultMonth)
            this.minYear = attributes.getInt(R.styleable.MonthPicker_min_year, this.minYear)
            this.minMonth = attributes.getInt(R.styleable.MonthPicker_min_month, this.minMonth)
            this.maxYear = attributes.getInt(R.styleable.MonthPicker_max_year, this.maxYear)
            this.maxMonth = attributes.getInt(R.styleable.MonthPicker_max_month, this.maxMonth)

            this.yearTexView.text = defaultYear.toString()
            this.monthTextView.text = defaultMonth.toString()

            this.curYear = defaultYear
            this.curMonth = defaultMonth
        }

        this.addBtn.setOnClickListener { addYearMonth() }

        this.minusBtn.setOnClickListener { minusYearMonth() }

    }

    fun setMinYear(value: Int){ this.minYear = value }

    fun setMinMonth(value: Int){ this.minMonth = value }

    fun setMaxYear(value: Int){ this.maxYear = value }

    fun setMaxMonth(value: Int){ this.maxMonth = value }

    fun getCurYear(): Int { return this.curYear }

    fun getCurMonth(): Int {return this.curMonth }


    private fun addYearMonth(){
        var canAddFlag:Boolean = false
        if(this.curYear < this.maxYear){ canAddFlag=true }

        if((this.curYear == this.maxYear) &&
            (this.curMonth < this.maxMonth)){ canAddFlag=true }

        if(canAddFlag){
            if(curMonth==12){
                this.curYear++
                this.curMonth=1
            }
            else{ this.curMonth++ }

            this.yearTexView.text = this.curYear.toString()
            this.monthTextView.text = this.curMonth.toString()
        }
    }


    private fun minusYearMonth(){
        var canMinusFlag:Boolean = false
        if(this.curYear > this.minYear){ canMinusFlag=true }

        if((this.curYear == this.minYear) &&
            (this.curMonth > this.minMonth)){ canMinusFlag=true }

        if(canMinusFlag){
            if(curMonth==1){
                this.curYear--
                this.curMonth=12
            }
            else{ this.curMonth-- }

            this.yearTexView.text = this.curYear.toString()
            this.monthTextView.text = this.curMonth.toString()
        }
    }
}