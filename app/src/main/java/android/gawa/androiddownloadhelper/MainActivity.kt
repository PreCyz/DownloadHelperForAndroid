package android.gawa.androiddownloadhelper

import android.gawa.androiddownloadhelper.component.command.CommandFactory
import android.gawa.androiddownloadhelper.component.command.CommandResult
import android.gawa.androiddownloadhelper.component.settings.SettingUtils
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

class MainActivity : AppCompatActivity() {

    private val titlesToShow = 20
    private val settingUtils = SettingUtils(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        downloadFavouritesBtn.setOnClickListener {

            var limit = settingUtils.readLimitFromSharedPreferences()
            val newLimit = torrentsNbr.text.toString().toInt()

            if (newLimit != limit) {
                settingUtils.writeLimitToSharedPreferences(newLimit)
                limit = newLimit
            }

            println("Limit from shared preferences: $limit")

            //val appSetting = AppSettings(ServerSetup(), ApiSetup(), RequestSetup(), FilterSetup(), OtherSetup())
            Toast.makeText(this@MainActivity, "Downloading $limit torrents ", Toast.LENGTH_SHORT).show()

            val command = CommandFactory().downloadAndFilterChain(limit)

            launch(UI) {
                val result = async(CommonPool) {
                    val commandResult = CommandResult()
                    try {
                        command.execute(commandResult)
                        commandResult
                    } catch (ex: Exception) {
                        println(ex)
                        commandResult.exMsg = ex.message
                        commandResult
                    }
                }.await()
                if (!result.exMsg.isNullOrEmpty()) {
                    helloWorldTxt.text = result.exMsg
                }
                else {
                    helloWorldTxt.text = result.titles(titlesToShow)
                }
            }

        }
        downloadFavouritesBtn.requestFocus()
    }
}
