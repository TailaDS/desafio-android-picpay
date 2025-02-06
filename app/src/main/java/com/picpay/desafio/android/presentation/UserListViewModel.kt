package com.picpay.desafio.android.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.domain.UserListUseCaseImpl
import com.picpay.desafio.android.model.User
import kotlinx.coroutines.launch

class UserListViewModel(
    private val useCase: UserListUseCaseImpl
) : ViewModel() {

    private val _usersList = MutableLiveData<List<User>>()
    val usersList: LiveData<List<User>> get() = _usersList

    fun getUsers() {
        viewModelScope.launch {
            _usersList.value = useCase.fetchUsers()
        }
    }
}