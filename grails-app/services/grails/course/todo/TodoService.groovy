package grails.course.todo

import grails.gorm.services.Service

@Service(Todo)
interface TodoService {

    Todo get(Serializable id)

    List<Todo> list(Map args)

    Long count()

    void delete(Serializable id)

    Todo save(Todo todo)

    Todo findByTitle(String titi)

}
