package com.solikiev.intake.data.database.dao

import androidx.room.*
import com.solikiev.intake.data.database.entities.DailyTarget
import kotlinx.coroutines.flow.Flow

@Dao
interface DailyTargetDao {
    @Query("SELECT * FROM daily_targets WHERE date = :date")
    fun getTargetByDate(date: String): Flow<DailyTarget?>

    @Query("SELECT * FROM daily_targets ORDER BY date DESC")
    fun getAllTargets(): Flow<List<DailyTarget>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTarget(target: DailyTarget)

    @Update
    suspend fun updateTarget(target: DailyTarget)

    @Delete
    suspend fun deleteTarget(target: DailyTarget)

    @Query("DELETE FROM daily_targets WHERE date = :date")
    suspend fun deleteTargetByDate(date: String)
}
