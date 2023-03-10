package com.example.receiptpocket.pocket.receipts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.receiptpocket.App
import com.example.receiptpocket.R
import com.example.receiptpocket.Room.Receipt
import com.example.receiptpocket.pocket.PocketActivity
import com.example.receiptpocket.pocket.qrscan.manualInput.ManualInputFragment
import com.example.receiptpocket.pocket.receipts.Presenters.ReceiptPresenter
import com.example.receiptpocket.pocket.receipts.Presenters.ReceiptPresenterImpl
import com.example.receiptpocket.pocket.receipts.Views.ReceiptView
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
class ReceiptsFragment : Fragment(), View.OnClickListener, ReceiptView,
    ReceiptRecyclerAdapter.OnItemClickListener, MonthPicker.OnAddOrMinusButtonClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var receiptsToolbar: Toolbar
    private lateinit var priceTextView: TextView
    private lateinit var pieceTextView: TextView
    private lateinit var dateBtn: Button
    private lateinit var analyzeBtn: Button
    private lateinit var monthPicker: MonthPicker
    private lateinit var recyclerAdapter: ReceiptRecyclerAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var receiptPresenter: ReceiptPresenter


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

        setPresenter()
        setUpToolbar()
        setMonthPicker()
        setRecyclerView()

        dateBtn.setOnClickListener(this)
        analyzeBtn.setOnClickListener(this)


        return view
    }

    override fun onResume() {
        super.onResume()
        Log.d("Fragment", "ReceiptsFragment onResume fragments size: " + activity?.supportFragmentManager?.fragments?.size)
        Log.d("Fragment", "ReceiptsFragment onResume fragments back stack size: " + activity?.supportFragmentManager?.backStackEntryCount)
    }

    private fun bindingViews(view: View){
        receiptsToolbar = view.findViewById<Toolbar>(R.id.receipts_toolbar)
        priceTextView = view.findViewById(R.id.price_num_textview)
        pieceTextView = view.findViewById(R.id.piece_textview)
        dateBtn = view.findViewById(R.id.date_barbtn)
        analyzeBtn = view.findViewById(R.id.analyze_barbtn)
        monthPicker = view.findViewById(R.id.month_picker)
        recyclerAdapter = ReceiptRecyclerAdapter(this)
        recyclerView = view.findViewById(R.id.receipts_month_recyclerview)
        progressBar = view.findViewById(R.id.receipt_progress_bar)
        receiptPresenter = ReceiptPresenterImpl(this)
    }

    private fun setPresenter(){
        receiptPresenter.loadItems(monthPicker.getCurYear(), monthPicker.getCurMonth())
    }

    private fun setUpToolbar(){
        receiptsToolbar.title = ""
        (activity as PocketActivity?)?.setSupportActionBar(receiptsToolbar)

    }

    private fun setMonthPicker(){
        monthPicker.setMinYear(prefs.yearPref)
        monthPicker.setMinMonth(prefs.monthPref)
        monthPicker.setOnAddOrMinusCLickListener(this)
    }

    private fun setRecyclerView(){
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(App.appContext)
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
            R.id.date_barbtn -> { loadFragment(ReceiptsDateFragment()) }
            R.id.analyze_barbtn -> {  }
        }
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

            // ????????????????????????????????????
            priceTextView.text = recyclerAdapter.getTotalPrice().toString()
            pieceTextView.text = recyclerAdapter.itemCount.toString()
        }
    }

    override fun navigateToManualWithData(data: Receipt) {
        val store = data.store ?: ""
        val year = data.year.toString()
        val month = data.month.toString()
        val day = data.day.toString()
        val code_1 = data.code_1
        val code_2 = data.code_2
        val price = data.price?.toString() ?: ""
        val describes = data.describes ?: ""

        loadFragment(
            ManualInputFragment.newInstance(store, year, month, day,
            code_1, code_2, price, describes))
    }


    override fun onItemClick(receiptItem: ReceiptItem) {
        receiptPresenter.loadCertainItem(prefs.userNamePrefs!!,
            receiptItem.code.substring(0,2), receiptItem.code.substring(3, 11))
    }

    override fun onAddOrMinusBtnClick() {
        setPresenter()
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