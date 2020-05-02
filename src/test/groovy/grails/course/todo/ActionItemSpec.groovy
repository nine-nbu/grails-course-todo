package grails.course.todo

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class ActionItemSpec extends Specification implements DomainUnitTest<ActionItem> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        given:
        def ai = new ActionItem(due: new Date()-5, name: 'changed')

        when:
            ai.validate()
        then:
            ai.hasErrors()
            ai.errors['name'].code == ''
    }
}
