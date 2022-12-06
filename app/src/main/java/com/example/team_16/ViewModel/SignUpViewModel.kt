package com.example.team_16.ViewModel

import android.text.Editable
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel() : ViewModel() {
    private var _email = MutableLiveData<Editable>()
    private var _password = MutableLiveData<EditText>()
    private var _confirmPW = MutableLiveData<EditText>()
    private var _name = MutableLiveData<EditText>()
    private var _nickname = MutableLiveData<EditText>()
    private var _kauId = MutableLiveData<EditText>()

    val email: LiveData<Editable> get() = _email
    val password: LiveData<EditText> get() = _password
    val confirmPW: LiveData<EditText> get() = _confirmPW
    val name: LiveData<EditText> get() = _name
    val nickname: LiveData<EditText> get() = _nickname
    val kauId: LiveData<EditText> get() = _kauId



}