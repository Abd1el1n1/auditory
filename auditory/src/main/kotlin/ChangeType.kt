sealed class ChangeType {
    data class PropertyUpdate(
        val property: String,
        val previous: Any?,
        val current: Any?
    ) : ChangeType()

    data class ListUpdate(
        val property: String,
        val added: List<Any?>,
        val removed: List<Any?>
    ) : ChangeType()
}
