package com.example.myamplifystorageapp

import android.app.Application
import android.util.Log
import android.view.View
import com.amplifyframework.AmplifyException
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.core.Amplify
import com.amplifyframework.storage.s3.AWSS3StoragePlugin
import java.io.File

class MyAmplifyStorageApp : Application() {

    override fun onCreate() {
        super.onCreate()

        try {
            // Add these lines to add the AWSCognitoAuthPlugin and AWSS3StoragePlugin plugins
            Amplify.addPlugin(AWSCognitoAuthPlugin())
            Amplify.addPlugin(AWSS3StoragePlugin())
            Amplify.configure(applicationContext)

            Log.i("MyAmplifyApp", "Initialized Amplify")
        } catch (error: AmplifyException) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error)
        }
    }

//    fun uploadFile(view: View) {
//        val exampleFile = File(applicationContext.filesDir, "ExampleKey")
//        exampleFile.writeText("Example file contents")
//
//        Amplify.Storage.uploadFile("ExampleKey", exampleFile,
//            { Log.i("MyAmplifyApp", "Successfully uploaded: ${it.key}") },
//            { Log.e("MyAmplifyApp", "Upload failed", it) }
//        )
//    }


}