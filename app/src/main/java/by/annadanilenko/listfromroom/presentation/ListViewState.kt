package by.annadanilenko.listfromroom.presentation

import by.annadanilenko.listfromroom.data.model.dbroom.ItemUser

data class ListViewState(
    val showProgressBar: Boolean,
    val listStatus: ListStatus
)

sealed class ListStatus

internal data class ListStatusSuccess(
    val users:
    List<ItemUser>
) :
    ListStatus()

internal data class ListStatusError(val errorDescription: String, val code: Int) :
    ListStatus()

internal object ListStatusDoNothing : ListStatus()