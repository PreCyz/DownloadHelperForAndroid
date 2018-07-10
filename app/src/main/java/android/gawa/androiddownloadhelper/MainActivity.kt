package android.gawa.androiddownloadhelper

import android.gawa.androiddownloadhelper.component.mapper.TorrentResponseMapper
import android.gawa.androiddownloadhelper.component.settings.*
import android.gawa.androiddownloadhelper.component.web.WebClient
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.async

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        helloWorldTxt.text = "Pawel Gawedzki"


        downloadFavouritesBtn.setOnClickListener {

            val appSetting = AppSettings(ServerSetup(), ApiSetup(), RequestSetup(), FilterSetup(), OtherSetup())
            Toast.makeText(this@MainActivity, "haha it works.", Toast.LENGTH_SHORT).show()

            val url = "https://eztv.ag/api/get-torrents"
            val params = mapOf("limit" to "2", "page" to "1")

            val response = async {
                WebClient().getRequest(url, params)
            }

            val titles = TorrentResponseMapper().map(response.await())

            helloWorldTxt.text = titles.torrents.joinToString(",") { torrentDetail -> torrentDetail.title }
            /*helloWorldTxt.text = appSetting.apiSetup.toString()
            helloWorldTxt.text = appSetting.otherSetup.toString()*/
        }
    }
}
