package android.gawa.androiddownloadhelper.component.settings

import android.content.Context
import android.gawa.androiddownloadhelper.R
import android.preference.PreferenceManager

class ApiSetup(context: Context) {

    private val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    val url : String = sharedPreferences.getString(context.getString(R.string.pref_torrent_api_url_key), "https://eztv.ag/api/get-torrents")
    val queryLimit : Int = sharedPreferences.getInt(context.getString(R.string.pref_torrent_api_limit_key), 100)
    val queryPage : Int = sharedPreferences.getInt(context.getString(R.string.pref_torrent_api_page_key), 20)

    override fun toString(): String {
        return "ApiSetup(url='$url', queryLimit=$queryLimit, queryPage=$queryPage)"
    }

}