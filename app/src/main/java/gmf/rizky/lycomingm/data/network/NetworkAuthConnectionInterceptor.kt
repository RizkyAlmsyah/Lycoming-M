package gmf.rizky.lycomingm.data.network

import android.content.Context
import android.content.Intent
import gmf.rizky.lycomingm.data.db.AppDatabase
import gmf.rizky.lycomingm.ui.login.LoginActivity
import okhttp3.Interceptor
import okhttp3.Response

class NetworkAuthConnectionInterceptor (
    context: Context,
    private val db: AppDatabase
): Interceptor{

    private val applicationContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if(response.code == 401){
            db.getManagementDao().deletemanagement()
            val intent = Intent(applicationContext, LoginActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            applicationContext.startActivity(intent)
        }
        return response
    }
}