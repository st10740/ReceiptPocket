package com.example.receiptpocket.pocket.qrscan.qrscan

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.example.receiptpocket.R
import com.example.receiptpocket.pocket.PocketActivity
import com.example.receiptpocket.pocket.qrscan.manualInput.ManualInputFragment
import com.example.receiptpocket.pocket.qrscan.qrscan.Presenters.QrcodePresenter
import com.example.receiptpocket.pocket.qrscan.qrscan.Presenters.QrcodePresenterImpl
import com.example.receiptpocket.pocket.qrscan.qrscan.Views.QrcodeView
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
class QrscanFragment : Fragment(), QrcodeView {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var manualInputBtn: Button
    private lateinit var codeScanner: CodeScanner
    private lateinit var codeScannerView: CodeScannerView
    private lateinit var qrcodePresenter: QrcodePresenter

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
        setUpCodeScanner()

        manualInputBtn.setOnClickListener { loadFragment(ManualInputFragment()) }


        return view
    }

    override fun onResume() {
        super.onResume()
        Log.d("Fragment", "QrscanFragment onResume fragments size: " + activity?.supportFragmentManager?.fragments?.size)
        Log.d("Fragment", "QrscanFragment onResume fragments back stack size: " + activity?.supportFragmentManager?.backStackEntryCount)
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

    override fun navigateManualInput(
        store: String,
        year: String,
        month: String,
        day: String,
        code_1: String,
        code_2: String,
        price: String,
        describes: String
    ) {
        val manualInputFragmentWithDatas = ManualInputFragment.newInstance(store, year, month, day, code_1, code_2, price, describes)
        loadFragment(manualInputFragmentWithDatas)
    }

    override fun reloadQrcodeScanner() {
        codeScanner.startPreview()
    }


    private fun bindingViews(view: View){
        manualInputBtn = view.findViewById(R.id.manual_input_barbtn)
        codeScannerView = view.findViewById(R.id.code_scanner_view)
        qrcodePresenter = QrcodePresenterImpl(this)
    }

    private fun setUpCodeScanner(){
        val activity = requireActivity()
        codeScanner = CodeScanner(activity, codeScannerView)
        codeScanner.decodeCallback = DecodeCallback {
            activity.runOnUiThread{
//                Toast.makeText(activity, it.text, Toast.LENGTH_LONG).show()
                Log.d("code", it.text)
                qrcodePresenter.analyzeScanStr(it.text)
            }
        }

        codeScannerView.setOnClickListener{ codeScanner.startPreview() }
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