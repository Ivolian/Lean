package com.ivotai.lean.other

import android.arch.lifecycle.LiveData
import io.reactivex.Single

// ResultType: Type for the Resource data
// RequestType: Type for the API response
abstract class NetworkBoundResource<T> {

    // returns a LiveData that represents the resource, implemented
    // in the base class.
//    val asLiveData: LiveData<Resource<T>>

    // Called to save the result of the API response into the database
    protected abstract fun saveCallResult(item: T)

    // Called to get the cached data from the database
    protected abstract fun loadFromDb(): LiveData<T>

    // Called to create the API call.
    protected abstract fun createCall(): Single<T>

    // Called when the fetch fails. The child class may want to reset components
    // like rate limiter.
    protected fun onFetchFailed() {
    }
}