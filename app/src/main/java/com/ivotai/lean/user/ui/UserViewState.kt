package com.ivotai.lean.user.ui

import com.ivotai.lean.user.po.User

interface UserViewState {

    class LoadingState : UserViewState {
        override fun toString(): String {
            return "LoadingState{}"
        }
    }

    class ErrorState(private val error: Throwable) : UserViewState {
        override fun toString(): String {
            return "ErrorState{" +
                    "error=" + error +
                    '}'
        }
    }

    /**
     * Data has been loaded successfully and can now be displayed
     */
    class DataState(val users: List<User>) : UserViewState {
        override fun toString(): String {
            return "DataState{" +
                    "users=" + users +
                    '}'
        }
    }

}
