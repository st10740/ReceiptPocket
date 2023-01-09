package com.example.receiptpocket.pocket.qrscan.manualInput

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import androidx.room.Delete
import com.example.receiptpocket.R
import com.example.receiptpocket.Room.Receipt
import com.example.receiptpocket.pocket.PocketActivity
import com.example.receiptpocket.pocket.qrscan.manualInput.Presenters.ManualInputPresenter
import com.example.receiptpocket.pocket.qrscan.manualInput.Presenters.ManualInputPresenterImpl
import com.example.receiptpocket.pocket.qrscan.manualInput.Views.ManualInputView
import com.example.receiptpocket.pocket.receipts.ReceiptsFragment
import com.example.receiptpocket.prefs
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.time.Year

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val STORE = "store"
private const val YEAR = "year"
private const val MONTH = "month"
private const val DAY = "day"
private const val CODE1 = "code_1"
private const val CODE2 = "code_2"
private const val PRICE = "price"
private const val DESCRIBES = "describes"

/**
 * A simple [Fragment] subclass.
 * Use the [ManualInputFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ManualInputFragment : Fragment(), View.OnClickListener, ManualInputView {
    // TODO: Rename and change types of parameters
    private var store: String? = null
    private var year: String? = null
    private var month: String? = null
    private var day: String? = null
    private var code_1: String? = null
    private var code_2: String? = null
    private var price: String? = null
    private  var describes: String? = null


    private lateinit var manualInputToolbar: Toolbar
    private lateinit var storeEditText: TextInputEditText
    private lateinit var yearEditText: TextInputEditText
    private lateinit var yearTextInputLayout: TextInputLayout
    private lateinit var monthEditText: TextInputEditText
    private lateinit var monthTextInputLayout: TextInputLayout
    private lateinit var dayEditText: TextInputEditText
    private lateinit var dayTextInputLayout: TextInputLayout
    private lateinit var code1EditText: TextInputEditText
    private lateinit var code1TextInputLayout: TextInputLayout
    private lateinit var code2EditText: TextInputEditText
    private lateinit var code2TextInputLayout: TextInputLayout
    private lateinit var priceEditText: TextInputEditText
    private lateinit var describesEditText: TextInputEditText
    private lateinit var deleteBtn: Button
    private lateinit var updateBtn: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var manualInputPresenter: ManualInputPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            store = it.getString(STORE)
            year = it.getString(YEAR)
            month = it.getString(MONTH)
            day = it.getString(DAY)
            code_1 = it.getString(CODE1)
            code_2 = it.getString(CODE2)
            price = it.getString(PRICE)
            describes = it.getString(DESCRIBES)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_manual_input, container, false)

        bindingViews(view)
        setupToolbar()
        setupEditText()

        deleteBtn.setOnClickListener(this)
        updateBtn.setOnClickListener(this)

        return view
    }

    private fun bindingViews(view: View){
        manualInputToolbar = view.findViewById(R.id.manual_input_toolbar)
        storeEditText = view.findViewById(R.id.store_name_edit_text)
        yearEditText = view.findViewById(R.id.year_edit_text)
        yearTextInputLayout = view.findViewById(R.id.year_text_input_layout)
        monthEditText = view.findViewById(R.id.month_edit_text)
        monthTextInputLayout = view.findViewById(R.id.month_text_input_layout)
        dayEditText = view.findViewById(R.id.day_edit_text)
        dayTextInputLayout = view.findViewById(R.id.day_text_input_layout)
        code1EditText = view.findViewById(R.id.code1_edit_text)
        code1TextInputLayout = view.findViewById(R.id.code1_text_input_layout)
        code2EditText = view.findViewById(R.id.code2_edit_text)
        code2TextInputLayout = view.findViewById(R.id.code2_text_input_layout)
        priceEditText = view.findViewById(R.id.price_edit_text)
        describesEditText = view.findViewById(R.id.describes_edit_text)
        deleteBtn = view.findViewById(R.id.delete_btn)
        updateBtn = view.findViewById(R.id.save_btn)
        progressBar = view.findViewById(R.id.manual_input_progress_bar)
        manualInputPresenter = ManualInputPresenterImpl(this)
    }

    private fun setupToolbar(){
        manualInputToolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        manualInputToolbar.setNavigationOnClickListener { (activity as PocketActivity).onBackPressed() }
//        (activity as PocketActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupEditText(){
        if(store != null) storeEditText.setText(store) else storeEditText.setText("")
        if(year != null) yearEditText.setText(year) else yearEditText.setText("")
        if(month != null) monthEditText.setText(month) else monthEditText.setText("")
        if(day != null) dayEditText.setText(day) else dayEditText.setText("")
        if(code_1 != null) code1EditText.setText(code_1) else code1EditText.setText("")
        if(code_2 != null) code2EditText.setText(code_2) else code2EditText.setText("")
        if(price != null) priceEditText.setText(price) else priceEditText.setText("")
        if(describes != null) describesEditText.setText(describes) else describesEditText.setText("")
    }

    private fun loadFragment(fragment: Fragment){
        (activity as PocketActivity?)?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container,fragment)
            ?.commit()
    }

    override fun onClick(v: View?) {
        if (v != null) {
            var sidStr = prefs.userNamePrefs!!
            var storeStr = storeEditText.text.toString()
            var yearStr = yearEditText.text.toString()
            var monthStr = monthEditText.text.toString()
            var dayStr = dayEditText.text.toString()
            var code1Str = code1EditText.text.toString()
            var code2Str = code2EditText.text.toString()
            var priceStr = priceEditText.text.toString()
            var describesStr = describesEditText.text.toString()


            // 檢查必輸入的值是否有輸入
            if(!yearStr.equals("") || !monthStr.equals("") || !dayStr.equals("")
                || !code1Str.equals("") || !code2Str.equals("")){

                val receipt = Receipt(sidStr, storeStr, yearStr.toInt(), monthStr.toInt(),
                    dayStr.toInt(), code1Str, code2Str, priceStr.toInt(), describesStr)

                when(v.id){
                    R.id.delete_btn -> {
                        manualInputPresenter.doUpdateItem(receipt, true)
                    }
                    R.id.save_btn -> {
                            manualInputPresenter.doUpdateItem(receipt, false)
                        }

                }
            }

            if(yearStr.equals("")) { yearTextInputLayout.error = " " }
            if(monthStr.equals("")) { monthTextInputLayout.error = " " }
            if(dayStr.equals("")) { dayTextInputLayout.error = " " }
            if(code1Str.equals("")) { code1TextInputLayout.error = " " }
            if(code2Str.equals("")) { code2TextInputLayout.error = " " }
        }
    }

    override fun showProgress() {
        progressBar.post {
            progressBar.visibility = View.VISIBLE
        }
    }

    override fun hideProgress() {
        progressBar.post {
            progressBar.visibility = View.GONE
        }
    }

    override fun navigateToPocket() {
        loadFragment(ReceiptsFragment())
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ManualInputFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(store: String, year: String, month: String, day: String,
                        code_1: String, code_2: String, price: String, describes: String) =

            ManualInputFragment().apply {
                arguments = Bundle().apply {
                    putString(STORE, store)
                    putString(YEAR, year)
                    putString(MONTH, month)
                    putString(DAY, day)
                    putString(CODE1, code_1)
                    putString(CODE2, code_2)
                    putString(PRICE, price)
                    putString(DESCRIBES, describes)
                }
            }
    }




}