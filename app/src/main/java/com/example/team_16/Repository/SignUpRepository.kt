package com.example.team_16.Repository

import android.content.ContentValues
import android.util.Log
import com.example.team_16.UserModel
import com.example.team_16.databinding.FragmentMypageAndLogoutBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDate

class SignUpRepository {

    private val db = FirebaseFirestore.getInstance()

    //파이어스토어
    val userRef = db.collection("Users")

    fun makeHash(major: String?, email: String?, kauid: String?,
                 name: String?, nickname: String?): HashMap<String, *> {
        val data = hashMapOf(
            "major" to major,
            "email" to email,
            "kauid" to kauid,
            "name" to name,
            "nickname" to nickname
        )
        return data
    }

    fun setData(data: HashMap<String, *>, email: String){
        userRef.document(email)
            .set(data).addOnSuccessListener {
                Log.v("tag", "데이터가 들어갔습니다")
            }
            .addOnFailureListener { exception ->
                Log.w("MainActivity", "Error getting documents: $exception")
            }

    }
}