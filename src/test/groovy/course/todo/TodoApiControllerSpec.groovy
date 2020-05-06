package course.todo

import grails.test.hibernate.HibernateSpec
import grails.testing.web.controllers.ControllerUnitTest

class TodoApiControllerSpec extends HibernateSpec implements ControllerUnitTest<TodoApiController> {

    def setup() {
        Todo.saveAll(
                new Todo(title: 'Eat'),
                new Todo(title: 'Work'),
                new Todo(title: 'Exercise'),
                new Todo(title: 'Sleep'))

    }

    void 'test the search action finds results'() {
        when: 'A query is executed that finds results'
            controller.search('E', 10)

        then: 'The response is correct'
            response.json.size() == 2
            response.json[0].title == 'Eat'
            response.json[1].title == 'Exercise'
    }
}
