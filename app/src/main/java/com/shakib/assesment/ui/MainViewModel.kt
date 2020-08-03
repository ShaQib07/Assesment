package com.shakib.assesment.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.shakib.assesment.repository.Repository

class MainViewModel : ViewModel() {

    var mainVMListener: MainVMListener? = null

    init {
        Repository.observeResponse().observeForever { mainVMListener?.onResponseSuccessful(it) }
    }

    fun fetchUsers(page: Int){
        Log.e("GSK", "VM - Page $page")
        Repository.fetchUsers(page)
    }


}