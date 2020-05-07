package course.todo

import spock.lang.Specification

class DueCommandSpec extends Specification {
    def "test validator on newDueDate"() {
        given:
            def sut = new DueCommand(newDueDate: new Date() - 5)
        when:
            sut.validate(['newDueDate'])
        then:
            sut.hasErrors()
            sut.errors.getFieldError('newDueDate').code == 'before.today'

    }
}
