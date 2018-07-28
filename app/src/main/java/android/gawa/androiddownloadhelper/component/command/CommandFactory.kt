package android.gawa.androiddownloadhelper.component.command

import android.content.Context

class CommandFactory {
    fun downloadAndFilterChain(context: Context): Command =
        DownloadDataCommand(context, FilterCommand(EndCommand()))
}