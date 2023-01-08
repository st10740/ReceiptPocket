package com.example.receiptpocket.pocket.account

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import com.example.receiptpocket.Prefs
import com.example.receiptpocket.R
import com.example.receiptpocket.login.MainActivity
import com.example.receiptpocket.login.Presenters.LoginPresenter
import com.example.receiptpocket.pocket.PocketActivity
import com.example.receiptpocket.pocket.account.Presenters.AccountPresenter
import com.example.receiptpocket.pocket.account.Presenters.AccountPresenterImpl
import com.example.receiptpocket.pocket.account.Views.AccountView
import com.example.receiptpocket.prefs
import com.google.android.material.textfield.TextInputEditText

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AccountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccountFragment : Fragment(), AccountView, View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var accInputEditText: TextInputEditText
    private lateinit var nameInputEditText: TextInputEditText
    private lateinit var pwInputEditText: TextInputEditText
    private lateinit var logoutBtn: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var accountPresenter: AccountPresenter

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
        val view = inflater.inflate(R.layout.fragment_account, container, false)

        bindingViews(view)

        accInputEditText.setText(prefs.userNamePrefs)
        nameInputEditText.setText(prefs.namePrefs)
        pwInputEditText.setText(prefs.passwordPrefs)

        logoutBtn.setOnClickListener(this)

        return view
    }

    private fun bindingViews(view: View){
        accInputEditText = view.findViewById(R.id.username_edit_text)
        nameInputEditText = view.findViewById(R.id.name_edit_text)
        pwInputEditText = view.findViewById(R.id.pw_edit_text)
        logoutBtn = view.findViewById(R.id.logout_btn)
        progressBar = view.findViewById(R.id.account_process_bar)
    }

    override fun showProgress() {
        progressBar.post {
            progressBar.visibility = View.VISIBLE
        }
    }

    override fun hideProgress() {
        progressBar.post {
            progressBar.visibility = View.GONE
        }
    }

    override fun navigateToLogin() {
        activity?.let {
            it.startActivity(Intent(it, MainActivity::class.java))
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.logout_btn -> {
                accountPresenter = AccountPresenterImpl(this)
                accountPresenter.logout()
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AccountFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AccountFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }




}