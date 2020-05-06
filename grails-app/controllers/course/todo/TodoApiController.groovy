package course.todo

import grails.rest.RestfulController

class TodoApiController extends RestfulController {
    static responseFormats = ['json', 'xml']

    TodoApiController() {
        super(Todo)
    }

    def search(String q, Integer max) {
        if (q) {
            def query = Todo.where {
                title ==~ "%${q}%"
            }
            respond query.list(max: Math.min(max ?: 10, 100))
        } else {
            respond([])
        }
    }
}
