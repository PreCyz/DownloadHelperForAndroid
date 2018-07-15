package android.gawa.androiddownloadhelper.component.command

import android.gawa.androiddownloadhelper.component.model.TorrentDetail
import android.gawa.androiddownloadhelper.component.model.TorrentResponse

data class CommandResult(var exMsg: String? = "")  {
    var torrentResponses : MutableList<TorrentResponse> = mutableListOf()
    var filteredTorrentDetails: List<TorrentDetail> = listOf()

    fun titles(size: Int): String {
        return torrentResponses.flatMap { it -> it.torrents }.map { it -> it.title }.take(size).joinToString(",\n")
    }
}