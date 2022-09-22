package by.annadanilenko.listfromroom

import android.app.Application
import android.content.Context
import by.annadanilenko.listfromroom.di.AppModule
import by.annadanilenko.listfromroom.di.ApplicationComponent
import by.annadanilenko.listfromroom.di.DaggerApplicationComponent

class ListRoomApplication : Application() {

    companion object {
        lateinit var appComponent: ApplicationComponent
        lateinit var globalContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        initDaggerComponent()
    }

    private fun initDaggerComponent() {
        appComponent = DaggerApplicationComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }
}