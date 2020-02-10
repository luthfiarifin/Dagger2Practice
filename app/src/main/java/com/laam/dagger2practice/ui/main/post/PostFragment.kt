package com.laam.dagger2practice.ui.main.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.laam.dagger2practice.R
import com.laam.dagger2practice.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PostFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private lateinit var postViewModel: PostViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.post_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        postViewModel =
            ViewModelProviders.of(this, viewModelProviderFactory)[PostViewModel::class.java]
    }
}