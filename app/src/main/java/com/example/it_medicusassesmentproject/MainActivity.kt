package com.example.it_medicusassesmentproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.it_medicusassesmentproject.activities.ExplorerActivity
import com.example.it_medicusassesmentproject.model.Formdata
import com.example.it_medicusassesmentproject.newTest.LoginAPI
import com.example.it_medicusassesmentproject.secTest.DataClassModel
import com.example.it_medicusassesmentproject.testingpurpose.ApiService

import com.example.it_medicusassesmentproject.testingpurpose.RegisterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.log


class MainActivity : AppCompatActivity() {

    lateinit var vape: LinearLayout
    lateinit var edibles: LinearLayout
    lateinit var flowers: LinearLayout
    lateinit var cardDiscount: CardView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        vape = findViewById(R.id.vape_id)
        edibles = findViewById(R.id.edibles_id)
        flowers = findViewById(R.id.flower_id)
        cardDiscount = findViewById(R.id.cardViewId)



    }


    fun onClick(view: View) {
        when (view.id) {
            R.id.vape_id -> {
                startActivity(Intent(this, ExplorerActivity::class.java))
            }
            R.id.edibles_id -> {
                startActivity(Intent(this, ExplorerActivity::class.java))

            }
            R.id.flower_id -> {
                startActivity(Intent(this, ExplorerActivity::class.java))

            }
            R.id.cardViewId -> {
                startActivity(Intent(this, ExplorerActivity::class.java))

            }
        }
    }

}