package com.laam.dagger2practice

import android.os.Bundle
import android.util.Log
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {

    private val TAG = "AuthActivity"

    @Inject
    lateinit var asdad: String

    @JvmField
    @Inject
    var isNull: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        Log.d(TAG, " $asdad")
        Log.d(TAG, "is null ? $isNull")
    }
}