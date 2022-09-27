package by.annadanilenko.listfromroom.di

import android.content.Context
import by.annadanilenko.listfromroom.data.MyConst
import by.annadanilenko.listfromroom.data.RemoteUsersInfo
import by.annadanilenko.listfromroom.data.api.MyRetrofit
import by.annadanilenko.listfromroom.data.api.NetClientAPI
import by.annadanilenko.listfromroom.data.model.dbroom.getDataBase
import dagger.Module
import dagger.Provides


@Module
class AppModule(private val context: Context) {

    //    @ApplicationScope
//    @Provides
//    fun showInfoOnUI() = ShowInfoOnUI(context = context)
//
//    @ApplicationScope
//    @Provides
//    fun showToast() = ShowToast(showInfoOnUI())
//
    @ApplicationScope
    @Provides
    fun clientHTTP() = MyRetrofit(baseURL = MyConst.BASE_URL).createClient()

    @ApplicationScope
    @Provides
    fun netClientAPI() = NetClientAPI(clientHTTP())

    @ApplicationScope
    @Provides
    fun appDatabase() = getDataBase(context)

    @ApplicationScope
    @Provides
    fun getUsersInfo() = RemoteUsersInfo(netClientAPI(), appDatabase())

}