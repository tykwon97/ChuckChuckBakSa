package org.tensorflow.codelabs.objectdetection

import android.content.Context
import android.content.SharedPreferences

class MyIndexSaveClass(context: Context) {
    private val prefsFilename = "prefs1" //파일 이름
    private val prefsKeyIndex = "myIndex1"
    private val prefs1:SharedPreferences = context.getSharedPreferences(prefsFilename, 0)  //저장된 값 불러오기
    var myIndex1:String?  //이 변수로 get, set.
    get() = prefs1.getString(prefsKeyIndex, "") //반환
    set(value) = prefs1.edit().putString(prefsKeyIndex, value!!).apply() //저장
}