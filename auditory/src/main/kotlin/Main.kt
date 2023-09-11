fun main() {
    val previousUser = mapOf(
        "firstName" to "James",
        "subscription" to mapOf("status" to "ACTIVE"),
        "services" to listOf("Oil Change", "Tire Rotation"),
        "vehicles" to listOf(mapOf("displayName" to "My Car", "id" to "v_1"))
    )

    val currentUser = mapOf(
        "firstName" to "Jim",
        "subscription" to mapOf("status" to "EXPIRED"),
        "services" to listOf("Oil Change", "Alignment"),
        "vehicles" to listOf(mapOf("displayName" to "23 Ferrari 296 GTS", "id" to "v_1"))
    )

    val auditInfo = AuditSystem.compare(previousUser, currentUser)
    println(auditInfo)
}
