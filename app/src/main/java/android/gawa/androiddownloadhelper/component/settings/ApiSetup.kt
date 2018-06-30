package android.gawa.androiddownloadhelper.component.settings

class ApiSetup {
    val url : String = "https://eztv.ag/api/get-torrents"
    val queryLimit : Int = 100
    val queryPage : Int = 20

    override fun toString(): String {
        return "ApiSetup(url='$url', queryLimit=$queryLimit, queryPage=$queryPage)"
    }

}