package com.example.receiptpocket.pocket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.receiptpocket.R
import com.example.receiptpocket.pocket.account.AccountFragment
import com.example.receiptpocket.pocket.qrscan.QrscanFragment
import com.example.receiptpocket.pocket.receipts.ReceiptsFragment
import com.example.receiptpocket.pocket.win.WinFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class PocketActivity : AppCompatActivity() {

    private lateinit var bottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pocket)


        // Bottom Navigation View
        loadFragment(ReceiptsFragment())
        bottomNav = findViewById<BottomNavigationView>(R.id.nav_view)
        bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.it_receipts -> { loadFragment(ReceiptsFragment()); true }
                R.id.it_qrscan -> { loadFragment(QrscanFragment()); true }
                R.id.it_account -> { loadFragment(AccountFragment()); true }
                else -> { loadFragment(WinFragment()); true }
            }
        }



    }

    private fun loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container,fragment)
            .commit()
    }
}