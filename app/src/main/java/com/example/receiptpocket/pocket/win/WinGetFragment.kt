package com.example.receiptpocket.pocket.win

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.receiptpocket.App
import com.example.receiptpocket.R
import com.example.receiptpocket.Room.Receipt
import com.example.receiptpocket.pocket.PocketActivity
import com.example.receiptpocket.pocket.qrscan.manualInput.ManualInputFragment
import com.example.receiptpocket.pocket.win.Presenters.WinPresenter
import com.example.receiptpocket.pocket.win.Presenters.WinPresenterImpl
import com.example.receiptpocket.pocket.win.Views.WinView
import com.example.receiptpocket.prefs

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val YEAR = "year"
private const val MONTH = "month"
private const val FIRST = "first"
private const val SECOND = "second"
private const val THIRD = "third"
private const val FOUR = "four"
private const val FIF = "fif"

/**
 * A simple [Fragment] subclass.
 * Use the [WinGetFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WinGetFragment : Fragment(), WinView, ReceiptWinRecyclerAdapter.OnItemClickListener {
    // TODO: Rename and change types of parameters
    private var year: String? = null
    private var month: String? = null
    private var first: String? = null
    private var second: String? = null
    private var third: String? = null
    private var four: String? = null
    private var fif: String? = null

    private lateinit var winGetToolbar: Toolbar
    private lateinit var monthTextView: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: ReceiptWinRecyclerAdapter
    private lateinit var presenter: WinPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            year = it.getString(YEAR)
            month = it.getString(MONTH)
            first = it.getString(FIRST)
            second = it.getString(SECOND)
            third = it.getString(THIRD)
            four = it.getString(FOUR)
            fif = it.getString(FIF)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_win_get, container, false)

        bindingViews(view)

        setupToolbar()
        setMonthTextView()
        setRecyclerView()
        setPresenter()

        return view
    }

    override fun onResume() {
        super.onResume()
        Log.d("Fragment", "WinGetFragment onResume fragments size: " + activity?.supportFragmentManager?.fragments?.size)
        Log.d("Fragment", "WinGetFragment onResume fragments back stack size: " + activity?.supportFragmentManager?.backStackEntryCount)
    }

    private fun bindingViews(view: View){
        winGetToolbar = view.findViewById(R.id.win_get_toolbar)
        monthTextView = view.findViewById(R.id.month_textview)
        progressBar = view.findViewById(R.id.win_get_progress_bar)
        recyclerView = view.findViewById(R.id.win_recyclerview)
        recyclerAdapter = ReceiptWinRecyclerAdapter(this)
        presenter = WinPresenterImpl(this)
    }

    private fun setupToolbar(){
        winGetToolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        winGetToolbar.setNavigationOnClickListener { (activity as PocketActivity).onBackPressed() }
//        (activity as PocketActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setMonthTextView(){
        monthTextView.text = month + " 中獎發票"
    }

    private fun setRecyclerView(){
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(App.appContext)
    }

    private fun setPresenter(){
        if(year!=null && month!=null && first!=null && second!=null && third!=null && four!=null && fif!=null){
            presenter.validateWin(year!!, month!!, first!!, second!!, third!!, four!!, fif!!)
        }
    }

    private fun loadFragment(fragment: Fragment){
        (activity as PocketActivity?)?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container,fragment)
            ?.addToBackStack(null)
            ?.commit()
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

    override fun initRecycler(list: List<ReceiptWinItem>) {
        recyclerView.post{
            recyclerAdapter.notifyDataSetChanged()
            recyclerAdapter.updateList(list)
        }
    }

    override fun navigateToWinDetailWithData(data: Receipt, prize: String) {
        val store = data.store ?: ""
        val year = data.year.toString()
        val month = data.month.toString()
        val day = data.day.toString()
        val code_1 = data.code_1
        val code_2 = data.code_2
        val price = data.price?.toString() ?: ""
        val describes = data.describes ?: ""

        loadFragment(
            WinDetailFragment.newInstance(store, year, month, day,
                code_1, code_2, price, describes, prize))
    }

    override fun onItemClick(receiptItem: ReceiptWinItem) {
        val code_1 = receiptItem.code.substringBefore('-')
        val code_2 = receiptItem.code.substringAfter('-')
        presenter.getOneItem(prefs.userNamePrefs!!, code_1, code_2, receiptItem.prize)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment WinGetFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(year: String, month: String, first: String, second: String,
                        third: String, four: String, fif: String) =
            WinGetFragment().apply {
                arguments = Bundle().apply {
                    putString(YEAR, year)
                    putString(MONTH, month)
                    putString(FIRST, first)
                    putString(SECOND, second)
                    putString(THIRD, third)
                    putString(FOUR, four)
                    putString(FIF, fif)
                }
            }
    }




}