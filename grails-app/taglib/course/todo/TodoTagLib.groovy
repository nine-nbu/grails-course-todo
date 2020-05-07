package course.todo

class TodoTagLib {
    static defaultEncodeAs = [taglib: 'html']
    static encodeAsForTags = [todoItems: [taglib:'raw'], otherTagName: [taglib:'none']]

    def todoItems = { attrs ->
        List<ActionItem> items = attrs.items as List<ActionItem>
        if (items == null) {
            throwTagError('TagLib [t:todoItems] missing attribute [items]')
        }

        List<ActionItem> pending = items.findAll { !it.done && it.due.after(new Date()) }
        List<ActionItem> overdue = items.findAll { !it.done && it.due.before(new Date()) }
        List<ActionItem> done = items.findAll { it.done }

        if(pending) {
            out << "<h3>Pending:</h3><ul>"

            pending.each {
                out << "<li>${it}</li>"
            }
        }
        if(overdue) {
            out << "<h3>Overdue:</h3><ul>"

            overdue.each {
                out << "<li>${it}</li>"
            }
        }
        if(done) {
            out << "<h3>Done:</h3><ul>"

            done.each {
                out << "<li>${it}</li>"
            }
        }
    }
}
