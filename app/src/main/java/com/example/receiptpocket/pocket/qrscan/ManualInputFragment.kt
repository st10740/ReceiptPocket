package com.example.receiptpocket.pocket.qrscan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import com.example.receiptpocket.R
import com.example.receiptpocket.pocket.PocketActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ManualInputFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ManualInputFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var manualInputToolbar: Toolbar

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
        val view = inflater.inflate(R.layout.fragment_manual_input, container, false)

        bindingViews(view)
        setupToolbar()


        return view
    }

    private fun bindingViews(view: View){
        manualInputToolbar = view.findViewById(R.id.manual_input_toolbar)
    }

    private fun setupToolbar(){
        manualInputToolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        manualInputToolbar.setNavigationOnClickListener { (activity as PocketActivity).onBackPressed() }
//        (activity as PocketActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
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
        fun newInstance(param1: String, param2: String) =
            ManualInputFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}