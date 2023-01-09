package com.example.receiptpocket.pocket.win

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.receiptpocket.R
import com.example.receiptpocket.pocket.PocketActivity
import com.example.receiptpocket.pocket.qrscan.manualInput.*

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
private const val WINPRIZE = "winPrize"

/**
 * A simple [Fragment] subclass.
 * Use the [WinDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WinDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var store: String? = null
    private var year: String? = null
    private var month: String? = null
    private var day: String? = null
    private var code_1: String? = null
    private var code_2: String? = null
    private var price: String? = null
    private var describes: String? = null
    private var winPrize: String? = null


    private lateinit var winDetailToolbar: Toolbar
    private lateinit var prizeTextView: TextView
    private lateinit var codeTextView: TextView
    private lateinit var dateTextView: TextView
    private lateinit var storeTextView: TextView
    private lateinit var priceTextView: TextView
    private lateinit var describesTextView: TextView


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
            winPrize = it.getString(WINPRIZE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_win_detail, container, false)

        bindingViews(view)
        setupToolbar()
        setTextViews()

        return view
    }

    private fun bindingViews(view: View){
        winDetailToolbar = view.findViewById(R.id.win_detail_toolbar)
        prizeTextView = view.findViewById(R.id.win_prize_textview)
        codeTextView = view.findViewById(R.id.code_textview)
        dateTextView = view.findViewById(R.id.date_textview)
        storeTextView = view.findViewById(R.id.store_textview)
        priceTextView = view.findViewById(R.id.price_textview)
        describesTextView = view.findViewById(R.id.describe_textview)
    }

    private fun setTextViews(){
        prizeTextView.text = "中獎金額 " + winPrize
        codeTextView.text = code_1 + "-" + code_2
        dateTextView.text = "民國" + year + "年" + month + "月" + day + "日"
        storeTextView.text = store
        priceTextView.text = "消費金額 $" + price
        describesTextView.text = describes
    }

    private fun setupToolbar(){
        winDetailToolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        winDetailToolbar.setNavigationOnClickListener { (activity as PocketActivity).onBackPressed() }
//        (activity as PocketActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment WinDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(store: String, year: String, month: String, day: String,
                        code_1: String, code_2: String, price: String, describes: String, winPrize: String) =
            WinDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(STORE, store)
                    putString(YEAR, year)
                    putString(MONTH, month)
                    putString(DAY, day)
                    putString(CODE1, code_1)
                    putString(CODE2, code_2)
                    putString(PRICE, price)
                    putString(DESCRIBES, describes)
                    putString(WINPRIZE, winPrize)
                }
            }
    }
}