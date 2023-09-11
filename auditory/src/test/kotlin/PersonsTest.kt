import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DiffToolTest {

    private lateinit var diffTool: DiffTool

    @BeforeEach
    fun setup() {
        diffTool = DiffTool()
    }

    @Test
    fun `test property update`() {
        val prev = Person("James", "Smith", 25, listOf())
        val curr = Person("Jim", "Smith", 25, listOf())

        val changes = diffTool.diff(prev, curr)

        assertTrue(changes.any { it is ChangeType.PropertyUpdate })
        val change = changes[0] as ChangeType.PropertyUpdate
        assertEquals("firstName", change.property)
        assertEquals("James", change.previous)
        assertEquals("Jim", change.current)
    }

    @Test
    fun `test list update`() {
        val prev = Person("James", "Smith", 25, listOf("Oil Change", "Wash"))
        val curr = Person("James", "Smith", 25, listOf("Oil Change"))

        val changes = diffTool.diff(prev, curr)

        assertTrue(changes.any { it is ChangeType.ListUpdate })
        val change = changes[0] as ChangeType.ListUpdate
        assertEquals("services", change.property)
//        assertEquals(listOf(), change.added)
        assertEquals(listOf("Wash"), change.removed)
    }

    // More tests can be added here...
}
