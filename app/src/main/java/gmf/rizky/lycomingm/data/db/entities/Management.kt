package gmf.rizky.lycomingm.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val CURRENT_MANAGEMENT_ID = 0

@Entity
data class Management(
    @ColumnInfo(name = "management_id")
    var management_id : Int,
    @ColumnInfo(name = "management_user_name")
    var management_user_name : String,
    @ColumnInfo(name = "management_full_name")
    var management_full_name : String,
    @ColumnInfo(name = "created_at")
    var created_at : String,
    @ColumnInfo(name = "update_at")
    var updated_at : String,
    @ColumnInfo(name = "token")
    var token: String
){
    @PrimaryKey(autoGenerate = false)
    var  uid: Int =
        CURRENT_MANAGEMENT_ID
}