package com.example.myamplifystorageapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.amplifyframework.core.Amplify
import com.amplifyframework.storage.options.StorageDownloadFileOptions
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun uploadFile(view: View) {
        val exampleFile = File(applicationContext.filesDir, "ExampleKey")
        exampleFile.writeText("Example file contents")

        Amplify.Storage.uploadFile("ExampleKey", exampleFile,
            { Log.i("MyAmplifyApp", "Successfully uploaded: ${it.key}") },
            { Log.e("MyAmplifyApp", "Upload failed", it) }
        )
    }
    fun downloadFile(view: View) {
        val file = File("${applicationContext.filesDir}/download.txt")
        val options = StorageDownloadFileOptions.defaultInstance()
        Amplify.Storage.downloadFile("ExampleKey", file, options,
            { Log.i("MyAmplifyApp", "Fraction completed: ${it.fractionCompleted}") },
            { Log.i("MyAmplifyApp", "Successfully downloaded: ${it.file.name}") },
            { Log.e("MyAmplifyApp", "Download Failure", it) }
        )
    }
}