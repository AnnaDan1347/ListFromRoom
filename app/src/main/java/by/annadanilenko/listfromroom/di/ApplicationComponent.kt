package by.annadanilenko.listfromroom.di

import by.annadanilenko.listfromroom.data.model.dbroom.AppDatabase
import by.annadanilenko.listfromroom.presentation.ListViewModel
import by.annadanilenko.listfromroom.presentation.MainActivity
import dagger.Component
import javax.inject.Scope

@Scope
@Retention
annotation class ApplicationScope

@ApplicationScope
@Component(modules = [AppModule::class])
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(appDatabase: AppDatabase)
    fun inject(param: ListViewModel)

}