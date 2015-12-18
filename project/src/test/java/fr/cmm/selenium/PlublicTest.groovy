import org.fluentlenium.adapter.FluentTest
import org.junit.Test

class PlublicTest extends FluentTest {
    @Test
    void 'visit home page'() {
        goTo 'http://localhost:8080/'

        assert $('h1').text == 'Des recettes, des idées pour déguster'

    }
}