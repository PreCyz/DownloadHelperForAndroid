package android.gawa.androiddownloadhelper.component.command

interface Command {
    fun execute(commandResult: CommandResult)
    fun next(command : Command)
}