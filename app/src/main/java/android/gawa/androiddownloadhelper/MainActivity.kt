package android.gawa.androiddownloadhelper

import android.content.Intent
import android.gawa.androiddownloadhelper.component.command.CommandFactory
import android.gawa.androiddownloadhelper.component.command.CommandResult
import android.gawa.androiddownloadhelper.component.settings.ServerSetup
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.GridView
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

        if (!ServerSetup(this@MainActivity).isValid()) {
            Toast.makeText(this@MainActivity, "Setup application first.", Toast.LENGTH_SHORT)
            //val nextScreen = Intent(this@MainActivity, SettingsActivity::class.java )
            val nextScreen = Intent(this@MainActivity, SettingsActivity::class.java )

            //Sending data to another Activity
            //nextScreen.putExtra("name", inputName.getText().toString())
            //nextScreen.putExtra("email", inputEmail.getText().toString())
            //Log.e("n", inputName.getText() + "." + inputEmail.getText())

            startActivity(nextScreen)
        }


        val resultGridView = findViewById<GridView>(R.id.resultGridView)
        val downloadFavouritesBtn = findViewById<Button>(R.id.downloadFavouritesBtn)

        downloadFavouritesBtn.setOnClickListener {

            val command = CommandFactory().downloadAndFilterChain(this@MainActivity)

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
                    Toast.makeText(this@MainActivity, result.exMsg, Toast.LENGTH_SHORT).show()
                } else {
                    val adapter = ArrayAdapter<String>(this@MainActivity, android.R.layout.simple_list_item_activated_1, result.titles().toTypedArray())
                    resultGridView.adapter = adapter
                }
            }

        }
        downloadFavouritesBtn.requestFocus()

        settingsBtn.setOnClickListener {
            val nextScreen = Intent(this@MainActivity, SettingsActivity::class.java )

            //Sending data to another Activity
            //nextScreen.putExtra("name", inputName.getText().toString())
            //nextScreen.putExtra("email", inputEmail.getText().toString())
            //Log.e("n", inputName.getText() + "." + inputEmail.getText())

            startActivity(nextScreen)
        }
    }
}
