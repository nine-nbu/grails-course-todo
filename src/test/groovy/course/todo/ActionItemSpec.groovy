package course.todo

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class ActionItemSpec extends Specification implements DomainUnitTest<ActionItem> {

    void "test normal validation"() {
        given:
            def ai = new ActionItem()

        when:
            ai.validate()

        then:
            ai.hasErrors()
            with(ai.errors.fieldErrors) {
                it*.field == ['name', 'due', 'todo']
                it.each { assert it.code == 'nullable' }
            }
    }


    void "test custom validation on name"() {
        given:
            def ai = new ActionItem(due: new Date() - 5, name: 'changed')

        when:
            ai.validate(['name'])
        then:
            ai.hasErrors()
            ai.errors.getFieldError('name').code == 'too.late.buster'
    }

    void "test custom validation on name with saved value"() {
        given:
            def todo = new Todo(name: 'Todo')
            todo.addToItems(due: new Date() - 5, name: 'change me', done: true)
        expect:
            todo.save(flush: true)

            def ai = todo.items.first()
        when:
            ai.name = "you're changed!"
        and:
            ai.validate(['name'])
        then:
            ai.hasErrors()
            ai.errors.getFieldError('name').code == 'it.is.done'

    }
}
