package com.sugoijapaneseschool.myfirebasetest1

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class MainActivity : AppCompatActivity() {
    private var mFirebaseAnalytics: FirebaseAnalytics? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "MyAPp")

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        mFirebaseAnalytics?.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
    }

    fun saveToFirebase(view: View) {
        val mDatabase: DatabaseReference

        //Firebase 에 넣을 hashmap 만들기
        val result = HashMap<Any, Any>()
        result["title"] = "제목1"
        result["body"] = "내용1"

        //Realtime Database에 Rule{.read, .write} true로 해줄필요가있다.

        // firebase 정의
        mDatabase = FirebaseDatabase.getInstance().reference
        mDatabase.child("article").push().setValue(result)

        // 이렇게 해도 되
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("message")
        myRef.setValue("data")
    }
}