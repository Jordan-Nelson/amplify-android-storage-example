package com.example.myamplifystorageapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import com.amplifyframework.auth.AuthUserAttributeKey
import com.amplifyframework.auth.options.AuthSignUpOptions
import com.amplifyframework.core.Amplify
import com.amplifyframework.storage.options.StorageDownloadFileOptions
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun signUp(view: View) {
        val username = findViewById<EditText>(R.id.editTextUsername).text.toString()
        val email = findViewById<EditText>(R.id.editTextEmailAddress).text.toString()
        val password = findViewById<EditText>(R.id.editTextPassword).text.toString()
        val options = AuthSignUpOptions.builder()
            .userAttribute(AuthUserAttributeKey.email(), email)
            .build()
        Amplify.Auth.signUp(
            username,
            password,
            options,
            { Log.i("AuthTest", "Sign up succeeded: $it") },
            { Log.e ("AuthTest", "Sign up failed", it) }
        )
    }

    fun confirmSignUp(view: View) {
        val username = findViewById<EditText>(R.id.editTextUsername).text.toString()
        val code = findViewById<EditText>(R.id.editTextCode).text.toString()
        Amplify.Auth.confirmSignUp(
            username,
            code,
            { Log.i("AuthTest", "Confirm sign up succeeded: $it") },
            { Log.e ("AuthTest", "Confirm sign up failed", it) }
        )
    }

    fun signIn(view: View) {
        val username = findViewById<EditText>(R.id.editTextUsername).text.toString()
        val password = findViewById<EditText>(R.id.editTextPassword).text.toString()
        Amplify.Auth.signIn(
            username,
            password,
            { Log.i("AuthTest", "Sign in succeeded: $it") },
            { Log.e ("AuthTest", "Sign in failed", it) }
        )
    }

    fun signOut(view: View) {
        Amplify.Auth.signOut(
            { Log.i("AuthTest", "Sign out succeeded") },
            { Log.e ("AuthTest", "Sign out failed", it) }
        )
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
            { Log.i("MyAmplifyApp", "Successfully downloaded: ${it.file.name}") },
            { Log.e("MyAmplifyApp", "Download Failure", it) }
        )
    }
}