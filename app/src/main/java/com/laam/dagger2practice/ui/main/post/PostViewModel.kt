package com.laam.dagger2practice.ui.main.post

import android.util.Log
import androidx.lifecycle.ViewModel
import com.laam.dagger2practice.SessionManager
import com.laam.dagger2practice.network.main.MainAPI
import javax.inject.Inject

class PostViewModel @Inject constructor(sessionManager: SessionManager, mainAPI: MainAPI) :
    ViewModel() {

    private val TAG = "PostViewModel"

    init {
        Log.d(TAG, "PostViewModel: work gan")
    }
}