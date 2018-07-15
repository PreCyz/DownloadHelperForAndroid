package android.gawa.androiddownloadhelper.component.filter

import android.gawa.androiddownloadhelper.component.model.TorrentDetail

interface Filter {
    fun filter(torrents: List<TorrentDetail>): List<TorrentDetail>
}