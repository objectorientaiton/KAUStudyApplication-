package com.example.team_16.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.team_16.Repository.MyPageRepository

class MyPageViewModel: ViewModel() {
    val repository = MyPageRepository()

    private var _email = MutableLiveData<String>("email")
    private var _name = MutableLiveData<String>("name")


    val email: LiveData<String> = repository.getEmail(_email)
    val name: LiveData<String> = repository.getName(_name)


}