package com.example.mydb
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mydb.ui.theme.DBHelper
import com.example.mydb.ui.theme.MydbTheme
import com.example.mydb.ui.theme.UserModel

class MainActivity : ComponentActivity() {
    //lateinit var db: SQLiteDatabase
    var dbHelper: DBHelper = DBHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MydbTheme {
                // A surface container using the 'background' color from the theme

                Surface(modifier = Modifier, color = MaterialTheme.colors.background) {
                    SimpleOutlinedTextFieldSample()
                }
            }

        }
        try {
            var user1= UserModel("ToB", "50")
            dbHelper.insertUser(user1)
            var user2= UserModel("ToB", "50")
            dbHelper.insertUser(user2)
            //dbHelper.delUser("To")

        }catch (e: Exception){
            Toast.makeText(applicationContext, e.toString(), Toast.LENGTH_LONG).show()
        }
        var users = dbHelper.showAllUser()
        var tstr =""
        for(user in users ){
            tstr += user.name+ ","+ user.age+"\n"
        }
        totalStr.value= tstr
        // Toast.makeText(applicationContext , totalText, Toast.LENGTH_LONG).show()


    }
}

@Composable
fun SimpleOutlinedTextFieldSample(/*onClick: () -> Unit*/) {
    Column (horizontalAlignment = Alignment.CenterHorizontally){
        var text by remember { mutableStateOf("") }
            TextField(
                value = text,
                modifier = Modifier.fillMaxWidth(),
                placeholder = {Text("User ID",textAlign =TextAlign.Center) },
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                onValueChange = { text = it },
                colors = TextFieldDefaults.textFieldColors(backgroundColor= Transparent,
                    focusedIndicatorColor = Red,unfocusedIndicatorColor = Red),

            )
            var text1 by remember { mutableStateOf("") }
        TextField(
            value = text1,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { text1 = it },
            placeholder = { Text(text="User Name",textAlign = TextAlign.Center) },
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            colors = TextFieldDefaults.textFieldColors(backgroundColor= Transparent,
                focusedIndicatorColor = Gray,unfocusedIndicatorColor = Gray),

            )
        var text2 by remember { mutableStateOf("") }
        TextField(
            value = text2,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { text2 = it },
            placeholder = { Text(textAlign = TextAlign.Center,text="User Age") },
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            colors = TextFieldDefaults.textFieldColors(backgroundColor= Transparent,
                focusedIndicatorColor = Gray,unfocusedIndicatorColor = Gray),

            )
        Row( modifier = Modifier.fillMaxWidth().width(20.dp).height(50.dp).aspectRatio(ratio = 2f),verticalAlignment=Alignment.CenterVertically,horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(
                    onClick = {},
                    modifier = Modifier.clickable {}
                ) {
                    Text(
                        text = "ADD",
                    )
                }

                Button(
                    onClick = {
                    },
                    modifier = Modifier.clickable {
                    }
                ) {
                    Text(
                        text = "DELETE",
                    )
                }
                Button(
                    onClick = {

                    },
                    modifier = Modifier.clickable {
                    }
                ) {
                    Text(
                        text = "SHOW ALL",
                    )
                }
        }
    }
}
@Composable
fun WithConstraintsComposable() {
    BoxWithConstraints {
        Text("My minHeight is $minHeight while my maxWidth is $maxWidth")
    }
}
var totalStr = mutableStateOf("")
@Composable
fun Greeting(name: String) {
    Text(text = "Hello ${totalStr.value}!")
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MydbTheme {
        Greeting("Android")
    }
}
@Preview()
@Composable
fun FlexibleComposable() {
    Row(Modifier.width(210.dp)) {
        Box(Modifier.weight(2f).height(50.dp).background(Blue))
        Box(Modifier.weight(1f).height(50.dp).background(Red))
    }
}
@Preview()
@Composable
fun boxWithConstraintsTest(){
    BoxWithConstraints(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopStart,
        propagateMinConstraints = true,
    ){
        val itemW = 50.dp
        val spaceW = 2.dp
        val count = (maxWidth.value/(itemW.value+spaceW.value)).toInt()
        if (count>0) {
            Row() {
                for(i in 0 until count){
                    Box(Modifier.size(50.dp, 50.dp).background(Blue))
                    Spacer(Modifier.size(2.dp))
                }
            }
        }
    }
}
