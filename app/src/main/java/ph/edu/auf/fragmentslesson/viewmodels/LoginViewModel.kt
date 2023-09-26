package ph.edu.auf.fragmentslesson.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
     val username = MutableLiveData<String>()
     val password = MutableLiveData<String>()

    fun sendDetails(username: String, password: String){
        this.username.value = username
        this.password.value = password
    }
}