package gmf.rizky.lycomingm.data.preferenfces

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

private const val KEY_SAVED_AT = "key_saved_at"
private const val TOKEN = "token"

class PreferenceProvider(
    context: Context
) {
    private val appContext = context.applicationContext
    private val preference: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    fun saveLastSavedAt(savedAt: String){
        preference.edit().putString(
            KEY_SAVED_AT,
            savedAt
        ).apply()
    }

    fun getLastSavedAt(): String?{
        return preference.getString(KEY_SAVED_AT, null)
    }

    fun saveToken(token: String){
        preference.edit().putString(
            TOKEN,
            token
        ).apply()
    }

    fun getToken(): String?{
        return preference.getString(TOKEN, null)
    }
}