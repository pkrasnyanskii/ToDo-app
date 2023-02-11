package data.model

class CommandExecutor(
    private val model: Model
) {

    fun executeAll() {
        model.commands.forEach { it.execute(model) }
    }
}