package com.kudos.studenthelpcare.core.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kudos.studenthelpcare.core.data.source.remote.response.SchoolsResponse
import com.kudos.studenthelpcare.core.domain.repositories.SchoolRespository
import com.kudos.studenthelpcare.core.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolViewModel @Inject constructor(
    private val schoolRespository: SchoolRespository
) : ViewModel() {
    private var _schools: MutableStateFlow<ResultState<SchoolsResponse>> =
        MutableStateFlow(ResultState.Empty)
    val schools: StateFlow<ResultState<SchoolsResponse>> = _schools


    fun getSchool() {
        viewModelScope.launch(Dispatchers.IO) {
            _schools.value = ResultState.Loading
            schoolRespository.getSchools().catch {
                Log.d("TAG", "getSchool: $it")
                _schools.value = ResultState.Fail(it.cause)
            }.collect {
                _schools.value = ResultState.Success(it)
            }
        }
    }
}