package com.sample.golf.ui.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.golf.data.source.remote.NetworkResult
import com.sample.golf.domain.model.GolfCourse
import com.sample.golf.domain.usecases.GolfDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GolfViewModel @Inject constructor(
    private val golfDetailsUseCase: GolfDetailsUseCase
) : ViewModel() {


    private val _isLoading = MutableStateFlow(true);
    val isLoading = _isLoading.asStateFlow();

    init {
        viewModelScope.launch {
            delay(3000)
            _isLoading.value = false
        }
    }


    private val _golfCourses = MutableStateFlow<NetworkResult<List<GolfCourse>>>(NetworkResult.Loading)
    val golfCourses: StateFlow<NetworkResult<List<GolfCourse>>> = _golfCourses

    // Function to initiate the search
    fun searchGolfCourses(query: String) {
        viewModelScope.launch {
            golfDetailsUseCase.searchGolfLocations(query).collect { result ->
                // Collect the result and update the state accordingly
                _golfCourses.value = result
            }
        }
    }

}
