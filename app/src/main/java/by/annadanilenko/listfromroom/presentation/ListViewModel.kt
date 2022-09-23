package by.annadanilenko.listfromroom.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.annadanilenko.listfromroom.ListRoomApplication
import by.annadanilenko.listfromroom.data.RemoteUsersInfo
import by.annadanilenko.listfromroom.data.model.dbroom.ItemUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ListViewModel : ViewModel() {
    @Inject
    lateinit var remoteUsersInfo: RemoteUsersInfo

    private val _viewState: MutableStateFlow<ListViewState> = MutableStateFlow(
        ListViewState(
            showProgressBar = false,
            listStatus = ListStatusDoNothing,
        )
    )
    val viewState: Flow<ListViewState> get() = _viewState

    init {
        ListRoomApplication.appComponent.inject(this)
    }

    private suspend fun getUsersInfo(): Boolean {
        return remoteUsersInfo.getUsersInfo()
    }

    fun startLoadUsersInfo() {
        viewModelScope.launch {
            sendProgressBarStatus(true)
            val usersStatus = if (!getUsersInfo()) {
                ListStatusError(
                    "Error",
                    400)//TODO
                            } else {
                var dbItems: List<ItemUser?>? = null
                withContext(Dispatchers.IO) {
                    dbItems = remoteUsersInfo.getUsers()
                    ListStatusSuccess(dbItems as List<ItemUser>)
                }
            }

            sendProgressBarStatus(false)

            _viewState.update { viewState -> viewState.copy(listStatus = usersStatus) }
        }
    }

    private fun sendProgressBarStatus(show: Boolean) {
        _viewState.update { trainingDetailViewState ->
            trainingDetailViewState.copy(
                showProgressBar = show
            )
        }
    }

}