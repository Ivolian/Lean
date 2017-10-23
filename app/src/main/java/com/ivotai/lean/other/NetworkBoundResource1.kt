package com.ivotai.lean.other

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData

abstract class NetworkBoundResourceImpl<T> : NetworkBoundResource<T>() {

    private val result = MediatorLiveData<Resource<T>>()

    val asLiveData: LiveData<Resource<T>>
        get() = result

    init {
        result.value = Resource(Status.Loading)
        val dbSource = loadFromDb()
        result.addSource(dbSource) { data ->
            if (data != null) {
                result.value = Resource(Status.Success, data)
            } else {
                result.removeSource(dbSource)
                fetchFromNetwork()
            }
        }
    }

    private fun fetchFromNetwork() {
//        var networkSource = MutableLiveData<T>()
//        createCall().
//                delay(3, TimeUnit.SECONDS)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        {
//                            networkSource.value = it
////                    saveResultAndReInit(response)
//                        },
//                        {
//                        }
//                )
//
//
////               result.addSource() { response ->
//
////            result.removeSource(networkSource)
//
//
//        if (response.isSuccessful()) {
//            saveResultAndReInit(response)
//        } else {
//            onFetchFailed()
//            result.addSource(dbSource
//            ) { newData ->
//                result.setValue(
//                        Resource.error(response.errorMessage, newData))
//            }
//        }
    }

//@MainThread
//private fun saveResultAndReInit(response: ApiResponse<RequestType>) {
//    object : AsyncTask<Void, Void, Void>() {
//
//        override fun doInBackground(vararg voids: Void): Void? {
//            saveCallResult(response.body)
//            return null
//        }
//
//        override fun onPostExecute(aVoid: Void) {
//            // we specially request a new live data,
//            // otherwise we will get immediately last cached value,
//            // which may not be updated with latest results received from network.
//            result.addSource(loadFromDb()
//            ) { newData -> result.setValue(Resource.success(newData)) }
//        }
//    }.execute()
//}
}