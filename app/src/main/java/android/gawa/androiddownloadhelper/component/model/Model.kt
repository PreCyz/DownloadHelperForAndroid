package android.gawa.androiddownloadhelper.component.model

import java.util.*


data class TorrentResponse(
        val torrentsCount: Long,
        val limit: Int,
        val page: Int,
        val torrents: List<TorrentDetail>
)

data class TorrentDetail(
        val id: Long,
        val hash: String,
        val filename: String,
        val episodeUrl: String,
        val torrentUrl: String,
        val magnetUrl: String,
        val title: String,
        val imdbId: String,
        val season: String,
        val episode: String,
        val smallScreenShot: String,
        val largeScreenShot: String,
        val seeds: Int,
        val peers: Int,
        val dateReleaseUnix: Long,
        val sizeBytes: String
) {
    val imdbIdLong: Long
        get() = this.imdbId.toLong()
    val seasonInt: Int
        get() = this.season.toInt()
    val episodeInt: Int
        get() = this.episode.toInt()
    val releaseDate: Date
        get() = Date(this.dateReleaseUnix)
    val sizeInBytes: Long
        get() = this.sizeBytes.toLong()
}

