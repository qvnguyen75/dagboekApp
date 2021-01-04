package com.example.dagboek.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.dagboek.domain.Item
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

@Database(entities = [Item::class], version = 4, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ItemDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile
        private var INSTANCE: ItemDatabase? = null
        fun getInstance(context: Context): ItemDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ItemDatabase::class.java,
                        "dagboek_items_database"
                    )
                        .addCallback(RoomDBPopulator())
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

//    companion object {
//
//        @Volatile
//        private var INSTANCE: ItemDatabase? = null
//
//        fun getInstance(context: Context): ItemDatabase {
//            if (INSTANCE == null) {
//                synchronized(this) {
//                    if (INSTANCE == null) {
//                        val instance = Room.databaseBuilder(
//                            context.applicationContext,
//                            ItemDatabase::class.java,
//                            "myRoomDatabase"
//                        ).addCallback(RoomDBPopulator())
//                            .build()
//                        INSTANCE = instance
//                    }
//                }
//            }
//
//            return INSTANCE!!
//        }
//    }


    // zorg er voor dat er iets in de db staat
    private class RoomDBPopulator : RoomDatabase.Callback() {

        private fun addItems() {
            INSTANCE?.let {
                // globalScope.launch loopt gelijk aan de mainThread
                GlobalScope.launch {
                    val itemDao = it.itemDao()
                    itemDao.deleteAll()
                    itemDao.insert(Item(1, "27/12/20", "title post 1", "body post 1"))
                    itemDao.insert(Item(2, "27/12/20", "title post 2", "body post 2"))
                    itemDao.insert(Item(3, "27/12/20", "title post 3", "body post 3"))

                }

            }
        }

//        override fun onOpen(db: SupportSQLiteDatabase) {
//            super.onOpen(db)
//            addItems()
//        }

        override fun onDestructiveMigration(db: SupportSQLiteDatabase) {
            super.onDestructiveMigration(db)
            addItems()
        }

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            addItems()

        }
    }
}