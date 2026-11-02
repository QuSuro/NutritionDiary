package com.example.nutritiondiary.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.nutritiondiary.data.entity.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user WHERE userId = :userId")
    suspend fun getUserById(userId: Int): User?

    @Query("SELECT * FROM user LIMIT 1")
    fun getCurrentUser(): Flow<User?>
}