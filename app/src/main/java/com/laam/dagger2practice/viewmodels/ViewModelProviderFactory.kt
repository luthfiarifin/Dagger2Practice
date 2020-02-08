package com.laam.dagger2practice.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

//
//class ViewModelProviderFactory @Inject constructor(private val creators: Map<Class<out ViewModel>, Provider<ViewModel>>) :
//    ViewModelProvider.Factory {
//    private val TAG = "ViewModelProviderFactory"
//
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        var creator: Provider<out ViewModel?>? = creators[modelClass]
//
//        creator?.let {
//            for ((key, value) in creators.entries) { // if it's allowed, set the Provider<ViewModel>
//                if (modelClass.isAssignableFrom(key)) {
//                    creator = value
//                    break
//                }
//            }
//        }.run {
//            throw IllegalArgumentException("unknown model class $modelClass")
//        }
//
//        @Suppress("UNREACHABLE_CODE", "UNCHECKED_CAST")
//        try {
//            return creator!!.get() as T
//        } catch (ex: Exception) {
//            @Suppress("ThrowableNotThrown")
//            throw RuntimeException(ex)
//        }
//    }
//}

class ViewModelProviderFactory @Inject constructor(private val creators: Map<Class<out ViewModel?>?, @JvmSuppressWildcards Provider<ViewModel>?>) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var creator: Provider<out ViewModel>? = creators[modelClass]
        if (creator == null) {

            for ((key, value) in creators) {
                if (modelClass.isAssignableFrom(key!!)) {
                    creator = value
                    break
                }
            }
        }

        requireNotNull(creator) { "unknown model class $modelClass" }

        return try {
            creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    companion object {
        private const val TAG = "ViewModelProviderFactor"
    }

}