package android.gawa.androiddownloadhelper.component.command

import android.gawa.androiddownloadhelper.component.model.TorrentResponse

class CommandResult {
    var torrentResponses = mutableListOf<TorrentResponse>()
    var titles: String? = null

    fun titles(size: Int): String {
        return torrentResponses.flatMap { it -> it.torrents }.map { it -> it.title }.take(size).joinToString(",\n")
    }
}