package com.example.receiptpocket.pocket.qrscan.qrscan

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.receiptpocket.R
import com.example.receiptpocket.pocket.PocketActivity
import com.example.receiptpocket.pocket.qrscan.manualInput.ManualInputFragment
import com.example.receiptpocket.prefs

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QrscanFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QrscanFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var TestBtn: Button
    private lateinit var manualInputBtn: Button

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
        val view = inflater.inflate(R.layout.fragment_qrscan, container, false)

        bindingViews(view)
        testPrefsBtn()
        manualInputBtn.setOnClickListener { loadFragment(ManualInputFragment()) }


        return view
    }

    private fun bindingViews(view: View){
        TestBtn = view.findViewById(R.id.test_prefs_btn)
        manualInputBtn = view.findViewById(R.id.manual_input_barbtn)
    }

    private fun testPrefsBtn(){
        TestBtn.setOnClickListener {
//            prefs.yearPref = 110
//            prefs.monthPref = 6
            Log.i("Test", "year: ${prefs.yearPref}")
            Log.i("Test", "month: ${prefs.monthPref}")
        }
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
         * @return A new instance of fragment QrscanFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QrscanFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}