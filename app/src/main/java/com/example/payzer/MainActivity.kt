package com.example.payzer

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkRequest
import java.util.concurrent.TimeUnit.MILLISECONDS


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestPermissions(arrayOf(Manifest.permission.RECEIVE_SMS),200)
        var uploadWorkRequest: WorkRequest = OneTimeWorkRequest.Builder(BackGroundService::class.java)
            .build()
        uploadWorkRequest=PeriodicWorkRequest.Builder(BackGroundService::class.java,900000
            , MILLISECONDS).build()
        WorkManager
            .getInstance(this)
            .enqueue(uploadWorkRequest)
        Log.e("TAG", "Running For Now")
    }
}