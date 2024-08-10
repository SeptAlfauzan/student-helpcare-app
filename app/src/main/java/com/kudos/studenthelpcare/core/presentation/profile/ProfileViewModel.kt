package com.kudos.studenthelpcare.core.presentation.profile

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kudos.studenthelpcare.core.data.source.remote.response.UserProfileResponse
import com.kudos.studenthelpcare.core.domain.repositories.ProfileRepository
import com.kudos.studenthelpcare.core.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {
    var _profileState: MutableStateFlow<ResultState<UserProfileResponse>> =
        MutableStateFlow(ResultState.Empty)
    val profileState: StateFlow<ResultState<UserProfileResponse>> = _profileState

    fun getProfile() {
        viewModelScope.launch {
            _profileState.value = ResultState.Loading
            profileRepository.getProfile().catch {
                _profileState.value = ResultState.Fail(it)
            }.collect {
                _profileState.value = ResultState.Success(it)
            }
        }
    }
}