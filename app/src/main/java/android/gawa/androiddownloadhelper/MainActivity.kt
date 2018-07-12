package android.gawa.androiddownloadhelper

import android.gawa.androiddownloadhelper.component.CommandResult
import android.gawa.androiddownloadhelper.component.ConvertToText
import android.gawa.androiddownloadhelper.component.DownloadDataCommand
import android.gawa.androiddownloadhelper.component.settings.*
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        helloWorldTxt.text = "Press button to download favourites"


        downloadFavouritesBtn.setOnClickListener {

            val appSetting = AppSettings(ServerSetup(), ApiSetup(), RequestSetup(), FilterSetup(), OtherSetup())
            Toast.makeText(this@MainActivity, "haha it works.", Toast.LENGTH_SHORT).show()

            val url = "https://eztv.ag/api/get-torrents"
            val params = mapOf("limit" to "2", "page" to "1")

            val convertToText = ConvertToText()
            val downloadData = DownloadDataCommand(url, params)
            downloadData.next(convertToText)
            val commandResult = CommandResult()

            launch(UI) {
                val titles = async(CommonPool) {
                    try {
                        downloadData.execute(commandResult)
                        commandResult.titles
                    } catch (ex: Exception) {
                        println(ex)
                        ex.message
                    }
                }.await()
                helloWorldTxt.text = titles
            }

            //helloWorldTxt.text = appSetting.apiSetup.toString()
            //helloWorldTxt.text = appSetting.otherSetup.toString()
        }
    }
}
