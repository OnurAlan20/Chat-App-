package com.monuralan.chatapp.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.monuralan.chatapp.navigation.Screens
import com.monuralan.chatapp.network.DataModelRegister
import com.monuralan.chatapp.network.RegisterResponse
import com.monuralan.chatapp.network.RetrofitAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Composable
fun RegisterScreen(navController: NavController){
    val ctx = LocalContext.current

    var registerEmail by remember {
        mutableStateOf("")
    }
    var registerUserName by remember {
        mutableStateOf("")
    }
    var registerPassword by remember {
        mutableStateOf("")
    }
    var response = remember {
        mutableStateOf("")
    }

    Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFFE7E4E4)) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(15.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(150.dp))
            Text(text = "Register", fontSize = 45.sp, style = TextStyle.Default, color = Color(0xFFD33F10), fontWeight = FontWeight.Bold )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier.fillMaxWidth(),
                value = registerEmail,
                colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = Color(
                    0xFFD14C21
                )
                ),
                leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "emailIcon", tint = Color(0xFFD14C21)) },
                onValueChange = {
                    registerEmail = it
                },
                label = { Text(text = "Email address", fontSize = 18.sp,color = Color(0xFFD14C21)) },
                placeholder = { Text(text = "Enter your e-mail",fontSize = 18.sp,color = Color(0xFFD14C21)) },
            )
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier.fillMaxWidth(),
                value = registerUserName,
                colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = Color(
                    0xFFD14C21
                )
                ),
                leadingIcon = { Icon(imageVector = Icons.Default.AccountBox, contentDescription = "AccountIcon", tint = Color(0xFFD14C21)) },
                onValueChange = {
                    registerUserName = it
                },
                label = { Text(text = "User Name", fontSize = 18.sp,color = Color(0xFFD14C21)) },
                placeholder = { Text(text = "Enter User Name",fontSize = 18.sp,color = Color(0xFFD14C21)) },
            )
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier.fillMaxWidth(),
                value = registerPassword,
                colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = Color(0xFFD14C21)),
                leadingIcon = { Icon(imageVector = Icons.Default.Star, contentDescription = "emailIcon", tint = Color(0xFFD14C21)) },
                onValueChange = {
                    registerPassword = it
                },
                label = { Text(text = "Enter Your Password", fontSize = 18.sp,color = Color(0xFFD14C21)) },
                placeholder = { Text(text = "Enter Your Password",fontSize = 18.sp,color = Color(0xFFD14C21)) },
            )
            Spacer(modifier = Modifier.height(35.dp))
            Button(
                onClick = {
                    postData(
                        ctx, registerEmail,registerUserName , registerPassword, response
                    )
                },
                modifier = Modifier.width(300.dp),
                elevation = ButtonDefaults.elevation(4.dp),
                colors = ButtonDefaults.outlinedButtonColors(Color(0xFFC22C2C)),
                shape = RoundedCornerShape(15.dp),


                ) {
                Text(text = "Register",fontSize = 30.sp, style = TextStyle.Default, color = Color.Black)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Text(text = "Do you already have an account?",
                    style = TextStyle.Default, color = Color.DarkGray,
                    fontSize = 18.sp,

                    modifier = Modifier
                        .padding(end = 10.dp)
                        .clickable {
                            navController.navigate(Screens.LoginScreen.route)
                        })
            }
            Text(
                text = response.value,
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold, modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

    }

}

@Preview(showSystemUi = true)
@Composable
fun deneme(){

}

fun postData(
    ctx: Context,
    registerEmail:String,
    registerUserName: String,
    registerPassword: String,
    result: MutableState <String>,
){
    var url = "http://192.168.1.102:8000/"

    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val retrofitAPI = retrofit.create(RetrofitAPI::class.java)

    val dataModelRegister = DataModelRegister(registerEmail,registerUserName,registerPassword,result)

    val call: Call<RegisterResponse?>? = retrofitAPI.postData(dataModelRegister)
    call!!.enqueue(object : Callback<RegisterResponse?> {
        override fun onResponse(
            call: Call<RegisterResponse?>,
            response: Response<RegisterResponse?>
        ) {

            Toast.makeText(ctx,response.body()!!.response,Toast.LENGTH_LONG).show()
        }
        override fun onFailure(call: Call<RegisterResponse?>, t: Throwable) {
            Toast.makeText(ctx,t.message,Toast.LENGTH_LONG).show()
        }

    })
}


