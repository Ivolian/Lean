package com.ivotai.lean

import android.os.AsyncTask
import android.os.Bundle
import android.os.SystemClock
import android.support.v7.app.AppCompatActivity


/**
 * Created by thinkpad on 2017/10/24.
 */
class LeakAct:AppCompatActivity (){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
setContentView(R.layout.act_user)

    startAsyncTask()
    }


    fun startAsyncTask() {
        object :AsyncTask<Unit,Unit,Unit>(){
            override fun doInBackground(vararg params: Unit?) {
                SystemClock.sleep(20000)
            }
        }.execute()
    }
}