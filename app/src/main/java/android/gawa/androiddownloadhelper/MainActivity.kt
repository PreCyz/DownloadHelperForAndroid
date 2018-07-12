package android.gawa.androiddownloadhelper

import android.gawa.androiddownloadhelper.component.command.CommandResult
import android.gawa.androiddownloadhelper.component.command.DownloadDataCommand
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

        downloadFavouritesBtn.setOnClickListener {

            val numberOfTorrents = getNumberOfTorrents(torrentsNbr.text.toString())

            //val appSetting = AppSettings(ServerSetup(), ApiSetup(), RequestSetup(), FilterSetup(), OtherSetup())
            Toast.makeText(this@MainActivity, "Downloading $numberOfTorrents torrents ", Toast.LENGTH_SHORT).show()

            val downloadData = DownloadDataCommand(numberOfTorrents)

            launch(UI) {
                val titles = async(CommonPool) {
                    try {
                        val commandResult = CommandResult()
                        downloadData.execute(commandResult)
                        commandResult.titles(if (numberOfTorrents < 35) numberOfTorrents else 35)
                    } catch (ex: Exception) {
                        println(ex)
                        ex.message
                    }
                }.await()
                helloWorldTxt.text = titles
            }
        }
        downloadFavouritesBtn.requestFocus()
    }

    private fun getNumberOfTorrents(torrentsNumber: String): Int {
        return if (torrentsNumber.isEmpty()) 100
        else torrentsNumber.toInt()
    }
}
