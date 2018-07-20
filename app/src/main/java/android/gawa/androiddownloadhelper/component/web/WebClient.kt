package android.gawa.androiddownloadhelper.component.web

import khttp.responses.Response

class WebClient {

    fun getRequest(url: String, params: Map<String, String>): Response {
        val response: Response = khttp.get(
                url = url,
                params = params)
        if (response.statusCode != 200) {
            println(response.text)
        } else {
            response.encoding = Charsets.UTF_8
            println("Response code: ${response.statusCode}")
        }
        return response
    }
}

