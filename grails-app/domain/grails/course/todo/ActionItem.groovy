package grails.course.todo

class ActionItem {
    String name
    String description
    Date due
    boolean done

    String toString() {
        "Action($id): $name (due: $due, done: ${done ? 'yes' : 'no'}"
    }

    static belongsTo = [todo: Todo]

    static constraints = {
        name validator: { value, object ->
            if (!object.done && object.due.before(new Date() + 3)) {
                return 'too.late.buster'
            }
            if(object.done && object.id && object.getPersistentValue('name') != value) {
                return 'it.is.done'
            }
        }
        description nullable: true

    }

    static mapping = {
        description sqlType: 'text'
    }
}
