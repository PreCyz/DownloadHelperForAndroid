package android.gawa.androiddownloadhelper.component.command

import android.gawa.androiddownloadhelper.component.model.DsApiDetails
import android.gawa.androiddownloadhelper.component.web.WebClient
import junit.framework.TestCase.assertEquals
import org.json.JSONObject
import org.junit.Test

class DsInfoMappingTest {
    @Test
    fun dsResponseTest() {
        val url = "https://gawa.myds.me:5001//webapi/query.cgi"
        val params = mapOf(
                "api" to "SYNO.API.Info",
                "version" to "1",
                "method" to "query",
                "query" to "SYNO.API.Auth,SYNO.DownloadStation.Task,SYNO.FileStation.Upload,SYNO.FileStation.Download"
                )
        val jsonObject = WebClient().getRequest(url, params).jsonObject

        val map = mutableMapOf<String, DsApiDetails>()

        val data = jsonObject["data"] as JSONObject
        var obj = data["SYNO.API.Auth"] as JSONObject
        map["SYNO.API.Auth"] = DsApiDetails(obj.getInt("maxVersion"), obj.getInt("minVersion"), obj.getString("path"))
        obj = data["SYNO.DownloadStation.Task"] as JSONObject
        map["SYNO.DownloadStation.Task"] = DsApiDetails(obj.getInt("maxVersion"), obj.getInt("minVersion"), obj.getString("path"))
        obj = data["SYNO.FileStation.Upload"] as JSONObject
        map["SYNO.FileStation.Upload"] = DsApiDetails(obj.getInt("maxVersion"), obj.getInt("minVersion"), obj.getString("path"))
        obj = data["SYNO.FileStation.Download"] as JSONObject
        map["SYNO.FileStation.Download"] = DsApiDetails(obj.getInt("maxVersion"), obj.getInt("minVersion"), obj.getString("path"))

        assertEquals(map.size, 4)

    }

    @Test
    fun dsAllApisTest() {
        val url = "https://gawa.myds.me:5001//webapi/query.cgi"
        val params = mapOf(
                "api" to "SYNO.API.Info",
                "version" to "1",
                "method" to "query",
                "query" to "all"
        )
        val jsonObject = WebClient().getRequest(url, params).jsonObject

        val map = mutableMapOf<String, DsApiDetails>()

        val data = jsonObject["data"] as JSONObject
        var obj = data["SYNO.API.Auth"] as JSONObject
        map.put("SYNO.API.Auth", DsApiDetails(obj.getInt("maxVersion"), obj.getInt("minVersion"), obj.getString("path")))
        obj = data["SYNO.DownloadStation.Task"] as JSONObject
        map.put("SYNO.DownloadStation.Task", DsApiDetails(obj.getInt("maxVersion"), obj.getInt("minVersion"), obj.getString("path")))
        obj = data["SYNO.FileStation.Upload"] as JSONObject
        map.put("SYNO.FileStation.Upload", DsApiDetails(obj.getInt("maxVersion"), obj.getInt("minVersion"), obj.getString("path")))
        obj = data["SYNO.FileStation.Download"] as JSONObject
        map.put("SYNO.FileStation.Download", DsApiDetails(obj.getInt("maxVersion"), obj.getInt("minVersion"), obj.getString("path")))

        assertEquals(map.size, 4)

    }
}