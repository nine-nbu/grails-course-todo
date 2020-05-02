package grails.course.todo

class ActionItem {
    String name
    String description
    Date due
    boolean done

    String toString() {
        "Action($id): $name (due: $due, done: ${done ? 'yes':'no'}"
    }

    static belongsTo = [todo: Todo]

    static constraints = {
        name validate: { value, object ->
            if (due.before(new Date() + 3)) {
                return 'too.late.buster'
            }
        description nullable: true

        }
    }

    static mapping = {
        description sqlType: 'text'
    }
}
