package com.djevtic.myswifttestcode

import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable

open class BaseActivity: AppCompatActivity() {

    protected val disposable = CompositeDisposable()

    var isInForeground = false

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}