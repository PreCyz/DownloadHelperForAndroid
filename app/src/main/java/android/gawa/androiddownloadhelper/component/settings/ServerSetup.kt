package android.gawa.androiddownloadhelper.component.settings

import android.content.Context
import android.gawa.androiddownloadhelper.R
import android.preference.PreferenceManager

class ServerSetup (private val context: Context) {

    private val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    private val url : String = sharedPreferences.getString(context.getString(R.string.pref_server_url_key), "")
    private val username : String = sharedPreferences.getString(context.getString(R.string.pref_server_username_key), "")
    private val password : String = sharedPreferences.getString(context.getString(R.string.pref_server_password_key), "")
    private val downloadFolderName : String = sharedPreferences.getString(context.getString(R.string.pref_server_download_folder_key), "")
    private val apiInfo : String = sharedPreferences.getString(context.getString(R.string.pref_server_password_key),
            "/webapi/query.cgi?api=SYNO.API.Info&version=1&method=query&query=SYNO.API.Auth,SYNO.DownloadStation.Task,SYNO.FileStation.List")
    private val taskCreationMethod: String = sharedPreferences.getString(context.getString(R.string.pref_server_task_creation_method_key), "")
    private val torrentUrlType: String = sharedPreferences.getString(context.getString(R.string.pref_server_torrent_url_type_key), "")

    fun isValid(): Boolean {
        return url != "" && port() > 0 && username != "" && password != "" && downloadFolderName != ""
                && apiInfo != "" && taskCreationMethod != "" && torrentUrlType != ""
    }

    private fun port(): Int {
        val port = sharedPreferences.getString(context.getString(R.string.pref_server_protocol_key), "")
        if (port == "") {
            return -1
        }

        return ServerPort.valueOf(port.toUpperCase()).port()
    }

    override fun toString(): String {
        return "ServerSetup(url='$url', port=${port()}, username='$username', password='$password', downloadFolderName='$downloadFolderName', apiInfo='$apiInfo')"
    }
}