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
        Todo.saveAll(
                new Todo(title: 'Item 1'),
                new Todo(title: 'Item 2'),
                new Todo(title: 'Item 3', items: [
                        new ActionItem(name: 'a1', description: 'd', due: new Date() + 4),
                        new ActionItem(name: 'a2', description: 'd', due: new Date() + 5),
                ]),
                new Todo(title: 'Item 4'),
                new Todo(title: 'Item 5'),
        )
        Todo.findByTitle('Item 3').id
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
            todoList*.title == ['Item 3', 'Item 4']
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

    void "test ChangeDueDate"() {
        given:
            Long id = setupData()
            Todo todo = todoService.get(id)
            Date sixDaysAhead = new Date() + 6

        expect:
            todo.items.size() == 2
            todo.items[0].due != todo.items[1].due
        when:
            todoService.changeDueDate(todo, sixDaysAhead)

        then:
            todo.items.every { it.due[Calendar.DATE] == sixDaysAhead[Calendar.DATE] }
    }
}
