package com.example.team_16.ViewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.team_16.Repository.StatisticsRepository
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class StatisticsViewModel() : ViewModel() {
    val repository = StatisticsRepository()
    private var _date = MutableLiveData<LocalDate>(LocalDate.now())
    private var _hour1 = MutableLiveData<Long>(0)
    private var _hour2 = MutableLiveData<Long>(0)
    private var _hour3 = MutableLiveData<Long>(0)
    private var _hour4 = MutableLiveData<Long>(0)
    private var _hour5 = MutableLiveData<Long>(0)
    private var _hour6 = MutableLiveData<Long>(0)
    private var _hour7 = MutableLiveData<Long>(0)
    val hour7: LiveData<Long> = repository.getHour(_date.toString(), _hour1)
    val hour6: LiveData<Long> = repository.getHour(_date.value?.minusDays(1).toString(), _hour2)
    val hour5: LiveData<Long> = repository.getHour(_date.value?.minusDays(2).toString(), _hour3)
    val hour4: LiveData<Long> = repository.getHour(_date.value?.minusDays(3).toString(), _hour4)
    val hour3: LiveData<Long> = repository.getHour(_date.value?.minusDays(4).toString(), _hour5)
    val hour2: LiveData<Long> = repository.getHour(_date.value?.minusDays(5).toString(), _hour6)
    val hour1: LiveData<Long> = repository.getHour(_date.value?.minusDays(6).toString(), _hour7)
}