package com.laam.dagger2practice.ui.main

class Resource<T>(val status: ResourceStatus, val data: T?, val message: String?) {

    enum class ResourceStatus {
        ERROR, LOADING, SUCCESS
    }

    companion object {
        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(ResourceStatus.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(ResourceStatus.LOADING, data, null)
        }

        fun <T> success(data: T?): Resource<T> {
            return Resource(ResourceStatus.SUCCESS, data, null)
        }
    }
}
