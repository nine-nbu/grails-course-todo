package course.todo

import geb.spock.GebSpec
import grails.testing.mixin.integration.Integration

@Integration
class TodoGebSpec extends GebSpec {
    def "test list todo items"() {
        when:
            go '/todo/index'

        then:
            $('h1').text() == 'Todo List'

        and:
            $('tbody').children().size() == 0
    }

    def "test create todo item"() {
        given:
            go '/todo/index'
            $('a.create').click()

        when:
            $('form').with {
                title = 'GEB'
                description = 'Learning GEB'
            }
            $('*[name="create"]').click()
        then:
            $('h1').text() == 'Show Todo'
            $('*[aria-labelledby="title-label"]').text() == 'GEB'
            $('*[aria-labelledby="description-label"]').text() == 'Learning GEB'
    }
}
