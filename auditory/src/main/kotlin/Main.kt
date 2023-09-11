fun main() {
    val previousPerson = Person("James", "Smith", 25, listOf("Oil Change", "Interior/Exterior Wash"))
    val currentPerson = Person("Jim", "Smith", 26, listOf("Oil Change"))

    val previousSubscription = Subscription("ACTIVE")
    val currentSubscription = Subscription("EXPIRED")

    val previousUser = User("u_1", "John Doe", previousSubscription, listOf(previousPerson))
    val currentUser = User("u_1", "John Doe", currentSubscription, listOf(currentPerson))

    val diffTool = DiffTool()
    val changes = diffTool.diff(previousUser, currentUser)

    changes.forEach {
        when (it) {
            is ChangeType.PropertyUpdate -> println("Property: ${it.property}, Previous: ${it.previous}, Current: ${it.current}")
            is ChangeType.ListUpdate -> println("Property: ${it.property}, Added: ${it.added}, Removed: ${it.removed}")
        }
    }
}

