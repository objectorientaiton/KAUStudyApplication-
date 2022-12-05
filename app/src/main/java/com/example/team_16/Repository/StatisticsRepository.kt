package com.example.team_16.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class StatisticsRepository {
    private val email = FirebaseAuth.getInstance().currentUser?.email ?: "None"
    private val db = FirebaseFirestore.getInstance()
    val userRef = db.collection("Users")

    fun getHour(date: String, hour: MutableLiveData<Long>): LiveData<Long>{
        userRef.document("$email").collection("studyTime").document(date)
            .get().addOnSuccessListener { document ->
                hour.value = document.getString("hour")?.toLong() ?: 0
            }
        return hour
    }
}