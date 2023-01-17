package com.example.receiptpocket.pocket.qrscan.qrscan

import android.Manifest
import android.app.AlertDialog
import android.content.DialogInterface
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
import com.example.receiptpocket.Room.Receipt
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


    companion object {
        private const val CAMERA_PERMISSION_CODE = 100
    }


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
        getCameraPermission()
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

    override fun showDialog(
        store: String,
        year: String,
        month: String,
        day: String,
        code_1: String,
        code_2: String,
        price: String,
        describes: String
    ) {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setCancelable(false)
        builder.setTitle("掃描成功")
        builder.setMessage("發票號碼：$code_1-$code_2")

        builder.setNegativeButton("直接儲存", DialogInterface.OnClickListener { dialog, which ->
            val receipt = Receipt(prefs.userNamePrefs!!, store, year.toInt(), month.toInt(), day.toInt(),
                code_1, code_2, price?.toInt()?:null, describes)

            qrcodePresenter.storeCodeData(receipt)
        })

        builder.setPositiveButton("編輯內容", DialogInterface.OnClickListener { dialog, which ->
            // 跳轉至手動輸入頁面
            val manualInputFragmentWithDatas = ManualInputFragment.newInstance(store, year, month, day, code_1, code_2, price, describes)
            loadFragment(manualInputFragmentWithDatas)
        })

        builder.create().show()

    }

    override fun reloadQrcodeScanner() {
        codeScanner.startPreview()
    }

    override fun navigateToPocket() {
        val bottomNaviView = (activity as PocketActivity).bottomNav
        bottomNaviView.post {
            bottomNaviView.selectedItemId = R.id.it_receipts
        }
    }

    private fun getCameraPermission(){
        qrcodePresenter.checkCameraPermission(requireActivity(), Manifest.permission.CAMERA, CAMERA_PERMISSION_CODE)
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




}