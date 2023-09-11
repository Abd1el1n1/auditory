data class Person(val firstName: String, val lastName: String, val age: Int, val services: List<String>)

data class Subscription(val status: String)

data class User(
    val id: String,
    val name: String,
    val subscription: Subscription,
    val friends: List<Person>
)
