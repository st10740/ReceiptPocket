package com.example.receiptpocket.pocket.receipts

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.receiptpocket.App
import com.example.receiptpocket.R
import com.example.receiptpocket.pocket.PocketActivity
import com.example.receiptpocket.pocket.receipts.Presenters.ReceiptPresenter
import com.example.receiptpocket.pocket.receipts.Presenters.ReceiptPresenterImpl
import com.example.receiptpocket.pocket.receipts.Views.ReceiptView
import com.example.receiptpocket.prefs
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [ReceiptsDateFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReceiptsDateFragment : Fragment(), ReceiptView, ReceiptRecyclerAdapter.OnItemClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var curPickYear: Int = 112
    private var curPickMonth: Int = 1
    private var curPickDay: Int = 1

    private lateinit var receiptsDateToolbar: Toolbar
    private lateinit var dateTextView: TextView
    private lateinit var recyclerAdapter: ReceiptRecyclerAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var receiptDataPresenter: ReceiptPresenter


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
        val view = inflater.inflate(R.layout.fragment_receipts_date, container, false)

        bindingView(view)

        setDefaultDate()
        setPresenter()
        setupToolbar()
        setDateTextViewOnClickListener()
        setRecyclerView()


        return view
    }

    private fun bindingView(view: View){
        receiptsDateToolbar = view.findViewById(R.id.receipts_date_toolbar)
        dateTextView = view.findViewById(R.id.date_textview)
        progressBar = view.findViewById(R.id.receipt_date_progress_bar)
        recyclerAdapter = ReceiptRecyclerAdapter(this)
        recyclerView = view.findViewById(R.id.receipts_date_recyclerview)
        receiptDataPresenter = ReceiptPresenterImpl(this)
    }

    private fun setPresenter(){
        receiptDataPresenter.loadItems(curPickYear, curPickMonth, curPickDay, false)
    }

    private fun setupToolbar(){
        receiptsDateToolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        receiptsDateToolbar.setNavigationOnClickListener { (activity as PocketActivity).onBackPressed() }
//        (activity as PocketActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setDefaultDate(){
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DATE)

        curPickYear = year-1911
        curPickMonth = month+1
        curPickDay = day
        dateTextView.text = "${year} 年 ${month+1} 月 ${day} 日"
    }

    private fun setDateTextViewOnClickListener(){
        dateTextView.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DATE)

            val maxDate = Date().time
            val minDate = dateStr2timestamp(prefs.yearPref+1911, prefs.monthPref, prefs.dayPrefs)

            val dpt = DatePickerDialog(requireContext(), { _, year, month, day ->
                run {
                    val format = "${year} 年 ${month+1} 月 ${day} 日"

                    curPickYear = year-1911
                    curPickMonth = month+1
                    curPickDay = day
                    dateTextView.text = format

                    setPresenter()

                    // TODO: 查詢當日發票 (利用presenter)
                }
            }, year, month, day)

            dpt.datePicker.maxDate = maxDate
            dpt.datePicker.minDate = minDate
            dpt.show()


        }
    }

    private fun dateStr2timestamp(year: Int, month: Int, day: Int): Long{
        val dateStr = "${year}-${month}-${day}"
        val pattern = "yyyy-MM-dd"
        val timeStamp = SimpleDateFormat(pattern).parse(dateStr).time

        return timeStamp
    }

    private fun loadFragment(fragment: Fragment){
        (activity as PocketActivity?)?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container,fragment)
            ?.addToBackStack(null)
            ?.commit()
    }

    private fun setRecyclerView(){
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(App.appContext)

    }

    override fun onItemClick(receiptItem: ReceiptItem) {
        //TODO: Use (prefs.username, receiptItem.code) to call presenter to query receipt details from Room
        //loadFragment(ManualInputFragment.newInstance())
    }



    override fun showProgressBar() {
        progressBar.post {
            progressBar.visibility = View.VISIBLE
        }
    }

    override fun hindProgressBar() {
        progressBar.post {
            progressBar.visibility = View.GONE
        }
    }

    override fun initRecycler(list: List<ReceiptItem>) {
        recyclerView.post{
            recyclerAdapter.notifyDataSetChanged()
            recyclerAdapter.updateList(list)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ReceiptsDateFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ReceiptsDateFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }



}