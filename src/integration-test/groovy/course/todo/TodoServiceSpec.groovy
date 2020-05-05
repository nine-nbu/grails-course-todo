package course.todo

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class TodoServiceSpec extends Specification {

    TodoService todoService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        new Todo(title: 'Item 1').save(flush: true, failOnError: true)
        new Todo(title: 'Item 2').save(flush: true, failOnError: true)
        Todo todo = new Todo(title: 'Item 3').save(flush: true, failOnError: true)
        new Todo(title: 'Item 4').save(flush: true, failOnError: true)
        new Todo(title: 'Item 5').save(flush: true, failOnError: true)
        todo.id
    }

    void "test get"() {
        setupData()

        expect:
        todoService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Todo> todoList = todoService.list(max: 2, offset: 2)

        then:
        todoList.size() == 2
        todoList*.title == ['Item 3','Item 4']
    }

    void "test count"() {
        setupData()

        expect:
        todoService.count() == 5
    }

    void "test delete"() {
        Long todoId = setupData()

        expect:
        todoService.count() == 5

        when:
        todoService.delete(todoId)
        sessionFactory.currentSession.flush()

        then:
        todoService.count() == 4
    }

    void "test save"() {
        when:
        Todo todo = new Todo(title: 'New todo')
        todoService.save(todo)

        then:
        todo.id != null
    }
}
