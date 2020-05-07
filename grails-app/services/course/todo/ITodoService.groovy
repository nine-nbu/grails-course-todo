package course.todo

import grails.gorm.services.Service

interface ITodoService {

    Todo get(Serializable id)

    List<Todo> list(Map args)

    Long count()

    void delete(Serializable id)

    Todo save(Todo todo)

    Todo findByTitle(String titi)

}
