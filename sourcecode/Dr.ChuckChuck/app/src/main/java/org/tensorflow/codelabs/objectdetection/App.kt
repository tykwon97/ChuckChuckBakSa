package org.tensorflow.codelabs.objectdetection

import android.app.Application

class App: Application(){  //prefs를 저장
    companion object{
        lateinit var prefs1: MyIndexSaveClass
    }

    override fun onCreate() {
        prefs1 = MyIndexSaveClass(applicationContext)//prefs 객체를 생성 및 보관(lateinit)
        super.onCreate()
    }
}