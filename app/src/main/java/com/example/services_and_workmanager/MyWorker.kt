package com.example.services_and_workmanager

import android.app.job.JobInfo
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.JobIntentService
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf

class MyWorker(
    context: Context,
   private val workerParameters: WorkerParameters
) : Worker(context, workerParameters) {
    override fun doWork(): Result {
        log("doWork")
        val page = workerParameters.inputData.getInt(PAGE, 0)
        for (i in 0 until 5) {
            Thread.sleep(1000)
            log("Timer $i $page")
        }
        return Result.success()
    }

    private fun log(message: String) {
        Log.d("MyService", "MyWorker: $message")
    }

    companion object {
        private const val PAGE = "page"
        const val WORK_NAME = "work_NAME"

        fun makeRequest( page:Int ): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<MyWorker>().apply{
                 setInputData(workDataOf(PAGE to page))
                setConstraints(makeConstraints())
            }
                .build()
        }

        private fun makeConstraints(): Constraints{
            return Constraints.Builder()
                .setRequiresCharging((true))
                .setRequiresCharging(false)
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .build()
        }
    }
}