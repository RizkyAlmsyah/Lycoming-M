package gmf.rizky.lycomingm.ui.splashcreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import gmf.rizky.lycomingm.R
import gmf.rizky.lycomingm.ui.login.LoginActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.requestFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            //start main activity
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            //finish this activity
            finish()
        },2000)
    }
}
