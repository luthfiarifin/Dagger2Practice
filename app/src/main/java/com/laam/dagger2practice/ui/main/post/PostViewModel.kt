package com.laam.dagger2practice.ui.main.post

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.laam.dagger2practice.SessionManager
import com.laam.dagger2practice.model.Post
import com.laam.dagger2practice.model.User
import com.laam.dagger2practice.network.main.MainAPI
import com.laam.dagger2practice.ui.main.Resource
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostViewModel @Inject constructor(val sessionManager: SessionManager, val mainAPI: MainAPI) :
    ViewModel() {

    private val TAG = "PostViewModel"

    init {
        Log.d(TAG, "PostViewModel: work gan")
    }

    private var posts: MediatorLiveData<Resource<List<Post>>>? = null

    fun observePosts(): LiveData<Resource<List<Post>>> {
        if (posts == null) {
            posts = MediatorLiveData()
            posts!!.value = Resource.loading(null)

            val source: LiveData<Resource<List<Post>>> = LiveDataReactiveStreams.fromPublisher(
                mainAPI.getPostsFromUser(sessionManager.getAuthUser().value?.data?.id!!)
                    .onErrorReturn { ex ->
                        Log.e(TAG, "observePosts: $ex")
                        return@onErrorReturn arrayListOf<Post>(Post(-1, 0, "", ""))
                    }
                    .map { listPost ->
                        if (listPost.isNotEmpty()) {
                            if (listPost[0].id == -1) {
                                return@map Resource.error("Something went error", listPost)
                            }
                            return@map Resource.success(listPost)
                        } else {
                            return@map Resource.error("Something went error", listPost)
                        }
                    }.subscribeOn(Schedulers.io())
            )

            posts!!.addSource(source, Observer {
                it?.let { resourceList ->
                    posts!!.value = resourceList
                    posts!!.removeSource(source)
                }
            })
        }
        return posts as LiveData<Resource<List<Post>>>
    }
}
