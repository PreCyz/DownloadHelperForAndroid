package android.gawa.androiddownloadhelper.component.command

internal class ConvertToTextCommand: Command {

    private var nextCommand : Command? = null

    override fun next(command: Command) {
        nextCommand = command
    }

    override fun execute(commandResult: CommandResult) {
        commandResult.titles = commandResult.torrentResponse?.torrents?.joinToString(",\n") { torrentDetail -> torrentDetail.title }
        nextCommand?.execute(commandResult)
    }

}