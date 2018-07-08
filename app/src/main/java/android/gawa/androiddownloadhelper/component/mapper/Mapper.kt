package android.gawa.androiddownloadhelper.component.mapper

import android.gawa.androiddownloadhelper.component.model.TorrentDetail
import android.gawa.androiddownloadhelper.component.model.TorrentResponse
import khttp.responses.Response
import org.json.JSONArray
import org.json.JSONObject

interface Mapper {
    fun map(response: Response): TorrentResponse
}

class TorrentResponseMapper : Mapper {

    operator fun JSONArray.iterator(): Iterator<JSONObject> = (0 until length()).asSequence()
            .map { get(it) as JSONObject }
            .iterator()

    override fun map(response: Response): TorrentResponse {
        val jsonObj = response.jsonObject
        val torrentArray = jsonObj["torrents"] as JSONArray
        val torrents =  mutableListOf<TorrentDetail>()
        for (obj : Any in torrentArray.iterator().toList()) {
            val jsonObject = obj as JSONObject
            torrents.add(TorrentDetail(
                    jsonObject.getLong("id"),
                    jsonObject.getString("hash"),
                    jsonObject.getString("filename"),
                    jsonObject.getString("episode_url"),
                    jsonObject.getString("torrent_url"),
                    jsonObject.getString("magnet_url"),
                    jsonObject.getString("title"),
                    jsonObject.getString("imdb_id"),
                    jsonObject.getString("season"),
                    jsonObject.getString("episode"),
                    jsonObject.getString("small_screenshot"),
                    jsonObject.getString("large_screenshot"),
                    jsonObject.getInt("seeds"),
                    jsonObject.getInt("peers"),
                    jsonObject.getLong("date_released_unix"),
                    jsonObject.getString("size_bytes")
            ))
        }
        return TorrentResponse(
                jsonObj.getLong("torrents_count"),
                jsonObj.getInt("limit"),
                jsonObj.getInt("page"),
                torrents
        )
    }

    private fun <T> Iterator<T>.toList(): List<T> =
            ArrayList<T>().apply {
                while (hasNext())
                    this += next()
            }
}