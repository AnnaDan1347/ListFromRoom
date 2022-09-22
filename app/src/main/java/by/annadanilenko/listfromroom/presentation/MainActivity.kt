package by.annadanilenko.listfromroom.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.annadanilenko.listfromroom.ListRoomApplication
import by.annadanilenko.listfromroom.R

class MainActivity : AppCompatActivity() {

    init {
        ListRoomApplication.appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}