package course.todo

import spock.lang.Specification

class DueCommandSpock extends Specification {
    def "test validator on newDueDate"() {
        given:
            def sut = new DueCommand(newDueDate: new Date() - 5)
        when:
            sut.validate()
        then:
            sut.hasErrors()
            sut.errors.getFieldError('newDueDate').code == 'before.today'
    }
}
