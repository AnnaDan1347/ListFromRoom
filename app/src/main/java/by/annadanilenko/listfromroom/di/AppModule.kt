package by.annadanilenko.listfromroom.di

import android.content.Context
import by.annadanilenko.listfromroom.data.MyConst
import by.annadanilenko.listfromroom.data.api.MyRetrofit
import by.annadanilenko.listfromroom.data.api.NetClientAPI
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

//    @ApplicationScope
//    @Provides
//    fun remoteLogin() = RemoteLogin(coreNetClientAPI(), appSettings())
//
//    @ApplicationScope
//    @Provides
//    fun appSettings() =
//        AppSettings(context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE))
//
//    @ApplicationScope
//    @Provides
//    fun androidSystemInfo() = AndroidSystemInfo(context)
//
//    @ApplicationScope
//    @Provides
//    fun remoteClubInfo() = RemoteClubInfo(coreNetClientAPI())
//
//    @ApplicationScope
//    @Provides
//    fun remoteTrainingsInfo() = RemoteTrainingsInfo(coreNetClientAPI())
//
//    @ApplicationScope
//    @Provides
//    fun accountDetailResponseProvider() = AccountDetailResponseProvider()

}