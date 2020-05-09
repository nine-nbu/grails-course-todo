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
}
