package android.gawa.androiddownloadhelper

import android.gawa.androiddownloadhelper.component.settings.*
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import khttp.responses.Response
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        helloWorldTxt.text = "Pawel Gawedzki"

        var clickCounter = 0

        getRequest()

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

    fun getRequest() {
        val response : Response = khttp.get(
                url = "https://eztv.ag/api/get-torrents",
                params = mapOf("limit" to "10", "page" to "1"))
        if(response.statusCode == 200) {
            val obj : JSONObject = response.jsonObject
            println(obj["someProperty"])
        } else {
            println("Response code: ${response.statusCode}")
        }
    }
}
