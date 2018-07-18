package android.gawa.androiddownloadhelper.component.settings

class ServerSetup {
    val url : String = "gawa.myds.me"
    val port  : Int = 5001
    val username : String = ""
    val password : String = ""
    val downloadFolderName : String = "downloads"
    val apiInfo : String = "/webapi/query.cgi?api=SYNO.API.Info&version=1&method=query&query=SYNO.API.Auth,SYNO.DownloadStation.Task,SYNO.FileStation.List"


    override fun toString(): String {
        return "ServerSetup(url='$url', port=$port, username='$username', password='$password', downloadFolderName='$downloadFolderName', apiInfo='$apiInfo')"
    }
}