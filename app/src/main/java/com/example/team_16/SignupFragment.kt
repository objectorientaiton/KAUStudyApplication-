package com.example.team_16

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.team_16.Repository.SignUpRepository
import com.example.team_16.ViewModel.SignUpViewModel
import com.example.team_16.databinding.FragmentSignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignupFragment : Fragment() {

    private lateinit var binding : FragmentSignupBinding
    private val viewModel: SignUpViewModel by activityViewModels()
    private lateinit var auth : FirebaseAuth
    private val repository = SignUpRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var email:String? = null
        binding.btnSignupRegister.setOnClickListener {
            viewModel.email.observe(viewLifecycleOwner){
                email = binding.etEnterEmail.text.toString()
            }
            viewModel.password.observe(viewLifecycleOwner){
                binding.etEnterPw.text.toString()
            }
            viewModel.confirmPW.observe(viewLifecycleOwner){
                binding.etEnterRepw.text.toString()
            }
            viewModel.name.observe(viewLifecycleOwner){
                binding.etName.text.toString()
            }
            viewModel.nickname.observe(viewLifecycleOwner){
                binding.etNickname.text.toString()
            }
            viewModel.kauId.observe(viewLifecycleOwner){
                binding.etKauID.text.toString()
            }

            //회원가입
            /*
            val password = binding.etEnterPw.text.toString()
            val confirmPW = binding.etEnterRepw.text.toString()


            val kauId = binding.etKauID.text.toString()*/

            if (TextUtils.isEmpty(viewModel.email.value.toString())) {
                binding.etEnterEmail.setError("이메일을 입력하세요")
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(viewModel.password.value.toString())) {
                binding.etEnterPw.setError("비밀번호를 입력하세요")
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(viewModel.confirmPW.value.toString())) {
                binding.etEnterRepw.setError("비밀번호를 다시 입력하세요")
                return@setOnClickListener
            } else if (viewModel.password.value?.equals(viewModel.confirmPW.value) == false) {
                binding.etEnterRepw.setError("비밀번호가 일치 하지 않습니다.")
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(viewModel.nickname.value.toString())) {
                binding.etNickname.setError("닉네임을 입력하세요")
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(viewModel.name.value.toString())) {
                binding.etName.setError("이름을 입력하세요")
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(viewModel.kauId.value.toString())) {
                binding.etKauID.setError("학번을 입력하세요")
                return@setOnClickListener
            } /*else if (viewModel.kauId.value?.length() != 10) {
                binding.etKauID.setError("학번은 10자리의 숫자 입니다.")
                return@setOnClickListener
            }*/

            /* //파이어스토어
             val user_info = hashMapOf(
                 "email" to email,
                 "nickname" to binding?.etNickname?.text.toString(),
                 "name" to binding?.etName?.text.toString(),
                 "department" to binding?.spDep?.selectedItem.toString(),
                 "kauid" to binding?.etKauID?.text.toString(),
             )*/

            val data = repository.makeHash(binding?.spDep?.selectedItem.toString(), viewModel.email.value.toString(),
                viewModel.kauId.value.toString(), viewModel.name.value.toString(), viewModel.nickname.value.toString())
            repository.setData(data, viewModel.email.value.toString())
            auth = FirebaseAuth.getInstance()
            auth.createUserWithEmailAndPassword(binding.etEnterEmail.text.toString(), binding.etEnterPw.text.toString())
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(activity, "회원가입 성공", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_signupFragment_to_entryFragment)
                    } else { Toast.makeText(activity, "${viewModel.email.value}", Toast.LENGTH_SHORT).show() }
                }
        }
    }
}