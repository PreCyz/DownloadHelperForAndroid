package android.gawa.androiddownloadhelper.component.command

internal class EndCommand : AbstractCommand(null) {
    override fun execute(commandResult: CommandResult) {
        println("This last command. Result: " + commandResult.toString())
    }
}