package com.laam.dagger2practice.ui.main.post

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.laam.dagger2practice.R
import com.laam.dagger2practice.ui.main.Resource
import com.laam.dagger2practice.util.VerticalSpacingItemDecoration
import com.laam.dagger2practice.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.post_fragment.*
import javax.inject.Inject

class PostFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    @Inject
    lateinit var adapter: PostsRecyclerAdapter

    private lateinit var postViewModel: PostViewModel

    private val TAG = "PostFragment"

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

        setupRecyclerView()
        subscribeObservers()
    }

    private fun setupRecyclerView() {
        recycler_view.layoutManager = LinearLayoutManager(activity)
        recycler_view.addItemDecoration(VerticalSpacingItemDecoration(15))
        recycler_view.adapter = adapter
    }

    private fun subscribeObservers() {
        postViewModel.observePosts().removeObservers(viewLifecycleOwner)
        postViewModel.observePosts().observe(viewLifecycleOwner, Observer {
            it?.let { resourceList ->
                when(resourceList.status){
                    Resource.ResourceStatus.LOADING -> {
                        Log.d(TAG, "subscribeObservers: LOADING...")
                    }

                    Resource.ResourceStatus.SUCCESS -> {
                        Log.d(TAG, "subscribeObservers: SUCCESS...")
                        adapter.setPosts(resourceList.data!!)
                    }

                    Resource.ResourceStatus.ERROR -> {
                        Log.e(TAG, "subscribeObservers: ERROR... ${resourceList.message}")
                    }
                }
            }
        })
    }


}