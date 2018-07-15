package android.gawa.androiddownloadhelper.component.command

import android.gawa.androiddownloadhelper.component.filter.FilterFactory

internal class FilterCommand(nextCommand: Command?) : AbstractCommand(nextCommand) {

    override fun execute(commandResult: CommandResult) {
        println("Here torrents must be filtered")
        val filters = FilterFactory().getFilters()
        var torrentDetails = commandResult.torrentResponses.flatMap {it -> it.torrents}.toList()
        for (filter in filters) {
            torrentDetails = filter.filter(torrentDetails)
        }
        commandResult.filteredTorrentDetails = torrentDetails
        nextCommand?.execute(commandResult)
    }

}