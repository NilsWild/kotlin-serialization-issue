import com.example.Id
import com.example.PersonId
import com.example.PersonOrThing
import io.github.projectmapk.jackson.module.kogera.jacksonObjectMapper
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.util.*

class SerializationTest {

    @Test
    fun `test serialization of value class`() {
        val id = PersonId(UUID.randomUUID())
        val json = jacksonObjectMapper().writeValueAsString(id)
        json shouldBe "[\"PersonId\",\"${id.value}\"]"
    }

    @Test
    fun `test serialization of data class with value class attribute`() {
        val obj = PersonOrThing(PersonId(UUID.randomUUID()))
        val json = jacksonObjectMapper().writeValueAsString(obj)
        json shouldBe "{\"id\":[\"PersonId\",\"${obj.id.value}\"]}"
    }

    @Test
    fun `test deserialization of value class by string`() {
        val id = PersonId(UUID.randomUUID())
        val deserialized = jacksonObjectMapper().readValue("[\"PersonId\",\"${id.value}\"]", Id::class.java)
        id shouldBe deserialized
    }

    @Test
    fun `test deserialization of data class with value class attribute by string`() {
        val obj = PersonOrThing(PersonId(UUID.randomUUID()))
        val deserialized = jacksonObjectMapper().readValue("{\"id\":[\"PersonId\",\"${obj.id.value}\"]}", PersonOrThing::class.java)
        obj shouldBe deserialized
    }
}