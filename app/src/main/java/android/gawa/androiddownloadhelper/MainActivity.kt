package android.gawa.androiddownloadhelper

import android.gawa.androiddownloadhelper.component.settings.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        helloWorldTxt.text = "Pawel Gawedzki"

        var clickCounter : Int = 0

        downloadFavouritesBtn.setOnClickListener{

            val appSetting = AppSettings(ServerSetup(), ApiSetup(), RequestSetup(), FilterSetup(), OtherSetup())
            Toast.makeText(this@MainActivity, "haha it works.", Toast.LENGTH_SHORT).show()
            if (clickCounter % 2 == 0) {
                helloWorldTxt.text = appSetting.apiSetup.toString()
            } else {
                helloWorldTxt.text = appSetting.otherSetup.toString()
            }
            clickCounter++
        }
    }
}
