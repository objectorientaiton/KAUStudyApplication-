package com.example.team_16

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.team_16.databinding.FragmentMypageAndLogoutBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class MypageAndLogoutFragment : Fragment() {

    lateinit var auth: FirebaseAuth

    private lateinit var binding: FragmentMypageAndLogoutBinding
    private lateinit var database: DatabaseReference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //  Inflate the layout for this fragment


        binding = FragmentMypageAndLogoutBinding.inflate(inflater)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser?.uid

        readData(user.toString())

        binding?.btnLogout?.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            Toast.makeText(activity, "로그아웃에 성공하였습니다.", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_mypageAndLogoutFragment_to_entryFragment)
        }
    }

    private fun readData(userUid: Any) {

        database = FirebaseDatabase.getInstance().getReference("Users")

        database.child(auth.currentUser?.uid.toString()).get().addOnSuccessListener {

            if(it.exists()){

                val email = it.child("email").value
                val nickname = it.child("nickname").value
                val name = it.child("name").value
                val kauId = it.child("kauId").value
                val department = it.child("department").value

                binding?.txtMyEmail?.text = email.toString()
                binding?.txtMyName?.text = name.toString()
                binding?.txtMyNickname?.text = nickname.toString()
                binding?.txtMyKauId?.text = kauId.toString()
                binding?.txtMyDept?.text = department.toString()

            }else{
                Toast.makeText(activity, "User Doesn't Exist", Toast.LENGTH_SHORT).show()
            }


        }
    }

}