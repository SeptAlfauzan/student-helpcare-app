package com.kudos.studenthelpcare.core.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kudos.studenthelpcare.core.domain.entities.Complaint
import com.kudos.studenthelpcare.core.domain.repositories.ComplaintsRepository
import com.kudos.studenthelpcare.core.utils.ComplaintResponseMapper
import com.kudos.studenthelpcare.core.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComplaintsViewModel @Inject constructor(
    private val complaintsRepository: ComplaintsRepository
) : ViewModel() {
    private var _complaints: MutableStateFlow<ResultState<List<Complaint>>> =
        MutableStateFlow(ResultState.Empty)
    val complaints: StateFlow<ResultState<List<Complaint>>> = _complaints


    fun getMyComplaints() {
        viewModelScope.launch(Dispatchers.IO) {
            _complaints.value = ResultState.Loading
            complaintsRepository.getMyComplaints().catch {
                Log.d("TAG", "getSchool: $it")
                _complaints.value = ResultState.Fail(it.cause)
            }.collect {
                _complaints.value = ResultState.Success(ComplaintResponseMapper.map(it))
            }
        }
    }
}