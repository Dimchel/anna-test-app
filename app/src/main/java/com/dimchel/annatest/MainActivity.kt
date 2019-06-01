package com.dimchel.annatest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dimchel.annatest.features.exchange.ExchangeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.app_container,
                    ExchangeFragment()
                )
                .commit()
        }
    }
}
