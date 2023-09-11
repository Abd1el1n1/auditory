import java.lang.reflect.Field
import java.lang.reflect.Modifier

class DiffTool {

    fun diff(prev: Any?, curr: Any?): List<ChangeType> {
        if (prev == null && curr == null) return emptyList()
        if (prev?.javaClass != curr?.javaClass) throw IllegalArgumentException("Objects must be of the same type")

        return computeDiff("", prev, curr)
    }

    private fun computeDiff(prefix: String, prev: Any?, curr: Any?): List<ChangeType> {
        val changes = mutableListOf<ChangeType>()
        val fields = (prev?.javaClass ?: curr?.javaClass)?.declaredFields ?: arrayOf()

        for (field in fields) {
            if (Modifier.isStatic(field.modifiers)) continue

            field.isAccessible = true

            val prevValue = field.get(prev)
            val currValue = field.get(curr)

            if (List::class.java.isAssignableFrom(field.type)) {
                val prevList = prevValue as? List<Any?> ?: emptyList()
                val currList = currValue as? List<Any?> ?: emptyList()

                val addedItems = currList - prevList
                val removedItems = prevList - currList
                if (addedItems.isNotEmpty() || removedItems.isNotEmpty()) {
                    changes.add(
                        ChangeType.ListUpdate(
                            property = prefix + field.name,
                            added = addedItems,
                            removed = removedItems
                        )
                    )
                }

            } else if (prevValue != currValue) {
                changes.add(ChangeType.PropertyUpdate(prefix + field.name, prevValue, currValue))
            }
        }

        return changes
    }
}
