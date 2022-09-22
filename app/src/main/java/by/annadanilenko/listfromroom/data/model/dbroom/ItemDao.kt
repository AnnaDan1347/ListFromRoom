package by.annadanilenko.listfromroom.data.model.dbroom

import androidx.room.*


@Dao
interface ItemDao {
    @Query("SELECT * FROM ItemUser")
    fun getAll(): List<ItemUser?>?

    @Query("DELETE FROM ItemUser")
    fun deleteAllUsers()

    @Insert
    fun insert(itemUser: ItemUser?)

    @Update
    fun update(itemUser: ItemUser?)

    @Delete
    fun delete(itemUser: ItemUser?)
}