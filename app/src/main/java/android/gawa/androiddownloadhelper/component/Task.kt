package android.gawa.androiddownloadhelper.component

import android.gawa.androiddownloadhelper.component.mapper.TorrentResponseMapper
import android.gawa.androiddownloadhelper.component.model.TorrentResponse
import android.gawa.androiddownloadhelper.component.web.WebClient

interface Command {
    fun execute(commandResult: CommandResult)
    fun next(command : Command)
}

class CommandResult {
    var torrentResponse: TorrentResponse? = null
    var titles: String? = null
}

class DownloadDataCommand(val url: String, val requestParamMap: Map<String, String>) : Command {

    private var nextCommand : Command? = null

    override fun next(command: Command) {
        this.nextCommand = command
    }

    override fun execute(commandResult: CommandResult) {
        val response = WebClient().getRequest(url, requestParamMap)
        val torrentResponse = TorrentResponseMapper().map(response)
        commandResult.torrentResponse = torrentResponse
        nextCommand?.execute(commandResult)
    }
}

class ConvertToText(): Command {

    private var nextCommand : Command? = null

    override fun next(command: Command) {
        nextCommand = command
    }

    override fun execute(commandResult: CommandResult) {
        commandResult.titles = commandResult.torrentResponse?.torrents?.joinToString(",\n") { torrentDetail -> torrentDetail.title }
        nextCommand?.execute(commandResult)
    }

}

