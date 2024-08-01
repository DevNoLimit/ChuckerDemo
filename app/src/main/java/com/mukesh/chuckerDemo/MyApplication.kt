package com.mukesh.chuckerDemo

import android.app.Application
import android.content.Context
import java.lang.ref.WeakReference

class MyApplication: Application() {

    companion object {
        var context: WeakReference<Context>? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = WeakReference(applicationContext)
    }

}