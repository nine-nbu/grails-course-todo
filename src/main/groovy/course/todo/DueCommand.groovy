package course.todo

import grails.validation.Validateable

class DueCommand implements Validateable {
    Todo todo
    Date newDueDate

    static constraints = {
        todo nullable: false
        newDueDate validator: { val ->
            if(val?.before(new Date())) {
                return 'before.today'
            }
        }
    }

}
