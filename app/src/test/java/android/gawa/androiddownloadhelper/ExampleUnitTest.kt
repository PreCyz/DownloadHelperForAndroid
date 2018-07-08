package android.gawa.androiddownloadhelper

import android.gawa.androiddownloadhelper.component.mapper.TorrentResponseMapper
import android.gawa.androiddownloadhelper.component.web.WebClient
import khttp.responses.Response
import org.hamcrest.core.IsEqual.equalTo
import org.json.JSONArray
import org.json.JSONObject
import org.junit.Assert.*
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun givenResponse_when_map_thenReturnTorrentResponse() {
        val url = "https://eztv.ag/api/get-torrents"
        val params = mapOf("limit" to "2", "page" to "1")
        val response: Response = WebClient().getRequest(url, params)

        val torrentResponse = TorrentResponseMapper().map(response)

        assertThat(torrentResponse.torrents.size, equalTo(2))
    }
}
