class AuditSystem {
    companion object {
        fun compare(previousObj: Any?, currentObj: Any?, propertyName: String = ""): AuditInfo {
            val auditInfo = AuditInfo(propertyName)

            if (previousObj != currentObj) {
                if (previousObj is Int || previousObj is Float || previousObj is String || previousObj is Boolean) {
                    auditInfo.previous = previousObj
                    auditInfo.current = currentObj
                } else if (previousObj is List<*> && currentObj is List<*>) {
                    val addedItems = currentObj - previousObj
                    val removedItems = previousObj - currentObj

                    if (addedItems.isNotEmpty() || removedItems.isNotEmpty()) {
                        auditInfo.added = addedItems
                        auditInfo.removed = removedItems
                    }
                } else if (previousObj is Map<*, *> && currentObj is Map<*, *>) {
                    val allKeys = previousObj.keys + currentObj.keys

                    for (key in allKeys) {
                        val subPropertyName = if (propertyName.isEmpty()) key.toString() else "$propertyName.$key"
                        val subAuditInfo = compare(previousObj[key], currentObj[key], subPropertyName)

                        if (subAuditInfo.previous != null || subAuditInfo.current != null || subAuditInfo.added != null || subAuditInfo.removed != null) {
                            auditInfo.previous = previousObj
                            auditInfo.current = currentObj
                            auditInfo.added = subAuditInfo.added
                            auditInfo.removed = subAuditInfo.removed
                        }
                    }
                }
            }

            return auditInfo
        }
    }
}

// Extension function to find added and removed items in lists
private operator fun List<*>.minus(other: List<*>): List<Any> {
    return this.filterNot { other.contains(it) }.mapNotNull { it }
}
