package com.example.receiptpocket.pocket.receipts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.receiptpocket.MonthPicker
import com.example.receiptpocket.R
import com.example.receiptpocket.pocket.PocketActivity
import com.example.receiptpocket.prefs

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ReceiptsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReceiptsFragment : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var receiptsToolbar: Toolbar
    private lateinit var dateBtn: Button
    private lateinit var analyzeBtn: Button
    private lateinit var monthPicker: MonthPicker


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
        var view = inflater.inflate(R.layout.fragment_receipts, container, false)

        // binding view
        bindingViews(view)

        setUpToolbar()
        setMonthPicker()

        dateBtn.setOnClickListener(this)
        analyzeBtn.setOnClickListener(this)

        return view
    }

    private fun bindingViews(view: View){
        receiptsToolbar = view.findViewById<Toolbar>(R.id.receipts_toolbar)
        dateBtn = view.findViewById(R.id.date_barbtn)
        analyzeBtn = view.findViewById(R.id.analyze_barbtn)
        monthPicker = view.findViewById(R.id.month_picker)


    }

    private fun setUpToolbar(){
        receiptsToolbar.title = ""
        (activity as PocketActivity?)?.setSupportActionBar(receiptsToolbar)

    }

    private fun setMonthPicker(){
        monthPicker.setMinYear(prefs.yearPref)
        monthPicker.setMinMonth(prefs.monthPref)
    }


    private fun loadFragment(fragment: Fragment){
        (activity as PocketActivity?)?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container,fragment)
            ?.addToBackStack(null)
            ?.commit()
    }

    override fun onClick(v: View?) {
        Log.i("click", "enter onClick")
        when(v?.id){
            R.id.date_barbtn -> { Log.i("click","click date"); loadFragment(ReceiptsDateFragment()) }
            R.id.analyze_barbtn -> { Log.i("click","click analyze") }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ReceiptsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ReceiptsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}