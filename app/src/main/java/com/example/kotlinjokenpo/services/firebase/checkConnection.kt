package com.example.kotlinjokenpo.services.firebase

import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

fun checkConnection() {
    val database: DatabaseReference = FirebaseDatabase.getInstance().reference
    database.child("testConnection").setValue("Connection Test")
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("FirebaseConnection", "Connection successful")
            } else {
                Log.e("FirebaseConnection", "Connection failed", task.exception)
            }
        }
}
