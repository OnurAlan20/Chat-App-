package com.monuralan.chatapp.network

import androidx.compose.runtime.MutableState
import com.google.gson.annotations.SerializedName

class DataModelRegister(

    email: String,
    username: String,
    password: String,
    response: MutableState<String>
) {
    lateinit var registerEmail: String
    lateinit var registerUserName: String
    lateinit var registerPassword: String
    lateinit var registerAcceptValue: String


}