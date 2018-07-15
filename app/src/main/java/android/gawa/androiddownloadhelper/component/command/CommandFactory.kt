package android.gawa.androiddownloadhelper.component.command

class CommandFactory {
    fun downloadAndFilterChain(numberOfTorrents: Int): Command =
        DownloadDataCommand(numberOfTorrents, FilterCommand(EndCommand()))
}