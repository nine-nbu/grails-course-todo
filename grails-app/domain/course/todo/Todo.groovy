package course.todo

class Todo {
    String title
    String description

    String toString() {
        "Todo($id): $title"
    }

    static hasMany = [items: ActionItem]

    static constraints = {
        title nullable: false
        description nullable: true, maxSize: 2000
    }

    static mapping = {
        table name: 'TODO_LIST'
        description sqlType: 'TEXT'
    }
}
