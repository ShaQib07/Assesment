package com.shakib.assesment.ui

import com.shakib.assesment.model.ApiResponse

interface MainVMListener {

    fun onResponseSuccessful(response: ApiResponse)

}