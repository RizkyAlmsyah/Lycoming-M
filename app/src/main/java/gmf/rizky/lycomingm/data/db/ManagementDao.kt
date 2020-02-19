package gmf.rizky.lycomingm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import gmf.rizky.lycomingm.data.db.entities.CURRENT_MANAGEMENT_ID
import gmf.rizky.lycomingm.data.db.entities.Management

@Dao
interface ManagementDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(management: Management) : Long

    @Query("SELECT * FROM management WHERE uid = $CURRENT_MANAGEMENT_ID")
    fun getmanagement() : LiveData<Management>

    @Query("DELETE FROM management")
    fun deletemanagement()
}