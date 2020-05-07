package course.todo

import grails.gorm.services.Service

@Service(Todo)
abstract class TodoService implements ITodoService {
    void changeDueDate(Todo todo, Date newDueDate) {
        todo.attach()
        todo.items*.due = newDueDate
    }
}
