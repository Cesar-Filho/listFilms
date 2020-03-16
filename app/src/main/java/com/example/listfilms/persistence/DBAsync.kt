package com.example.listfilms.persistence

import android.os.AsyncTask


class DBAsync : AsyncTask<Runnable?, Void?, Void?>() {
    override fun doInBackground(vararg params: Runnable?): Void? {
        params[0]?.run()
        return null
    }
}