package com.example.receiptpocket.pocket.win

import android.media.MediaCodec
import android.os.Build.VERSION_CODES.P
import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toolbar
import com.example.receiptpocket.R
import com.example.receiptpocket.pocket.PocketActivity
import com.example.receiptpocket.pocket.receipts.MonthPicker
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WinFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WinFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var yearEditText: TextInputEditText
    private lateinit var yearTextInputLayout: TextInputLayout
    private lateinit var monthDropDown: AutoCompleteTextView
    private lateinit var monthTextInputLayout: TextInputLayout
    private lateinit var firstPEditText: TextInputEditText
    private lateinit var firstTextInputLayout: TextInputLayout
    private lateinit var secondPEditText: TextInputEditText
    private lateinit var secondTextInputLayout: TextInputLayout
    private lateinit var thirdPEditText: TextInputEditText
    private lateinit var thirdTextInputLayout: TextInputLayout
    private lateinit var fourthPEditText: TextInputEditText
    private lateinit var fourTextInputLayout: TextInputLayout
    private lateinit var fifthPEditText: TextInputEditText
    private lateinit var fifTextInputLayout: TextInputLayout
    private lateinit var sendBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_win, container, false)

        bindingViews(view)
        setMonthDropDown()

        sendBtn.setOnClickListener { navigateToWinGetWithData() }

        return view
    }

    override fun onResume() {
        super.onResume()
        Log.d("Fragment", "WinFragment onResume fragments size: " + activity?.supportFragmentManager?.fragments?.size)
        Log.d("Fragment", "WinFragment onResume fragments back stack size: " + activity?.supportFragmentManager?.backStackEntryCount)
    }

    private fun bindingViews(view: View){
        toolbar = view.findViewById(R.id.win_num_input_toolbar)
        yearEditText = view.findViewById(R.id.year_edit_text)
        yearTextInputLayout = view.findViewById(R.id.win_year_text_input_layout)
        monthDropDown = view.findViewById(R.id.month_dropdown)
        monthTextInputLayout = view.findViewById(R.id.win_month_text_input_layout)
        firstPEditText = view.findViewById(R.id.first_edit_text)
        firstTextInputLayout = view.findViewById(R.id.first_prize_text_input_layout)
        secondPEditText = view.findViewById(R.id.second_edit_text)
        secondTextInputLayout = view.findViewById(R.id.second_prize_text_input_layout)
        thirdPEditText = view.findViewById(R.id.third_edit_text)
        thirdTextInputLayout = view.findViewById(R.id.third_prize_text_input_layout)
        fourthPEditText = view.findViewById(R.id.four_edit_text)
        fourTextInputLayout = view.findViewById(R.id.fourth_prize_text_input_layout)
        fifthPEditText = view.findViewById(R.id.fif_edit_text)
        fifTextInputLayout = view.findViewById(R.id.fifth_prize_text_input_layout)
        sendBtn = view.findViewById(R.id.check_btn)
    }

    private fun setMonthDropDown(){
        val type = arrayOf("1-2月", "3-4月", "5-6月", "7-8月", "9-10月", "11-12月")
        val mAdapter = NoFilterAdapter(requireContext(), R.layout.month_dropdown_item, type)
        monthDropDown.setAdapter(mAdapter)
    }

    private fun navigateToWinGetWithData(){
        val year = yearEditText.text?.toString() ?: ""
        val month = monthDropDown.text?.toString() ?: ""
        val first = firstPEditText.text?.toString() ?: ""
        val second = secondPEditText.text?.toString() ?: ""
        val third = thirdPEditText.text?.toString() ?: ""
        val four = fourthPEditText.text?.toString() ?: ""
        val fif = fifthPEditText.text?.toString() ?: ""

        if(!year.equals("") && !month.equals("") && !first.equals("") && !second.equals("") &&
                !third.equals("") && !four.equals("") && !fif.equals("")){

            val fragment = WinGetFragment.newInstance(year, month, first, second, third, four, fif)
            loadFragment(fragment)
        }

        if(year.equals("")) { yearTextInputLayout.error = "不可為空" }
        if(month.equals("")) { monthTextInputLayout.error = "不可為空" }
        if(first.length!=8) { firstTextInputLayout.error = "輸入須為8個數字" }
        if(second.length!=8) { secondTextInputLayout.error = "輸入須為8個數字" }
        if(third.length!=8) { thirdTextInputLayout.error = "輸入須為8個數字" }
        if(four.length!=8) { fourTextInputLayout.error = "輸入須為8個數字" }
        if(fif.length!=8) { fifTextInputLayout.error = "輸入須為8個數字" }

    }

    private fun loadFragment(fragment: Fragment){
        (activity as PocketActivity?)?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container,fragment)
            ?.addToBackStack(null)
            ?.commit()
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment WinFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WinFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}