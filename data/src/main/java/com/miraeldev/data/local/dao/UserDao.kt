package com.miraeldev.data.local.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.miraeldev.data.local.models.user.UserDbModel
import kotlinx.coroutines.flow.Flow


@Dao
internal interface UserDao {

    @Query("SELECT * FROM user")
    fun getUserFlow(): Flow<UserDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserDbModel)

    @Query("DELETE  FROM user")
    suspend fun deleteOldUser()

}