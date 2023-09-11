class AuditInfo(val property: String) {
    var previous: Any? = null
    var current: Any? = null
    var added: List<Any>? = null
    var removed: List<Any>? = null

    override fun toString(): String {
        return """{
            "property": "$property",
            "previous": $previous,
            "current": $current,
            "added": $added,
            "removed": $removed
        }""".trimIndent()
    }
}
