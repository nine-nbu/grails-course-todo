package grails.course.todo

class BootStrap {

    def init = { servletContext ->
        def map = [title: 'Learn Grails', items: [
                [name: 'The basics', description: 'Learn what a Grails project is', due: Date.parse('yyyy-MM-dd', '2020-04-30'), done: false],
                [name: 'Domain objects', description: 'How to use Domain objects in Grails', due: Date.parse('yyyy-MM-dd', '2020-04-30'), done: false]
        ]]

        Todo.withNewSession {
            new Todo(map).save(failOnError: true)
        }
    }
    def destroy = {
    }
}
