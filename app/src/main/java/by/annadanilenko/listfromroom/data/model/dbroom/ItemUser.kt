package by.annadanilenko.listfromroom.data.model.dbroom

import androidx.room.Entity

@Entity(primaryKeys = ["id"])
data class ItemUser(

    var id: String,

    var userName: String,

    var imageUrl: String,

    var originalApi: String

)