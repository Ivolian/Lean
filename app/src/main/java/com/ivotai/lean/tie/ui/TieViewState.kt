package com.ivotai.lean.tie.ui

import com.ivotai.lean.tie.po.Tie

interface TieViewState {

    class LoadingState : TieViewState {
        override fun toString(): String {
            return "LoadingState{}"
        }
    }

    class ErrorState(private val error: Throwable) : TieViewState {
        override fun toString(): String {
            return "ErrorState{" +
                    "error=" + error +
                    '}'
        }
    }

    class DataState(val ties: List<Tie>) : TieViewState {
        override fun toString(): String {
            return "DataState{" +
                    "ties=" + ties +
                    '}'
        }
    }

}