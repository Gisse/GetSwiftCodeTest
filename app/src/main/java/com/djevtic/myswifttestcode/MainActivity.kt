package com.djevtic.myswifttestcode

import android.os.Bundle
import com.djevtic.myswifttestcode.network.ApiService
import com.djevtic.myswifttestcode.network.ApiSwiftInterface
import com.djevtic.myswifttestcode.network.SwiftUsecaseImpl

class MainActivity : BaseActivity() {

    private val swiftUsecase =
            SwiftUsecaseImpl(ApiService.getSwiftClient().create(ApiSwiftInterface::class.java))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}
