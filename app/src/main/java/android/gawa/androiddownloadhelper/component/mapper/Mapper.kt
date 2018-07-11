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

    override fun map(response: Response): TorrentResponse {
        val jsonObj = response.jsonObject
        val torrentArray = jsonObj["torrents"] as JSONArray
        val torrents =  mutableListOf<TorrentDetail>()
        for (jsonObject : JSONObject in jsonArrayToJsonObjectList(torrentArray)) {
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

    private fun jsonArrayToJsonObjectList(jsonArray: JSONArray): List<JSONObject> {
        val list = mutableListOf<JSONObject>()
        for (i: Int in 0..(jsonArray.length() - 1)) {
            list.add(jsonArray.getJSONObject(i))
        }
        return list
    }
}