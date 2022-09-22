package by.annadanilenko.listfromroom.data.model.dbroom

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [ItemUser::class /*, AnotherEntityType.class, AThirdEntityType.class */],
    version = 5
)
abstract class AppDatabase : RoomDatabase() {
    abstract val itemDao: ItemDao?
}

fun getDataBase(context: Context): AppDatabase {
    return Room.databaseBuilder(context, AppDatabase::class.java, "database")
        .fallbackToDestructiveMigration()
        .build()
}