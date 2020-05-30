package com.djevtic.myswifttestcode

import android.os.Bundle
import com.djevtic.myswifttestcode.data.DataManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addClickListeners()
    }

    private fun addClickListeners() {
        clickButton.setOnClickListener {
            disposable.add(DataManager.getStandingsData(524).subscribe(
                    {

                    }
                    ,
                    {}
            ))
        }
    }

}
