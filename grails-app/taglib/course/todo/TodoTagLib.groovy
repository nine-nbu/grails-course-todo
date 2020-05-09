package course.todo

class TodoTagLib {
    static defaultEncodeAs = [taglib:'html']
    static encodeAsForTags = [todoItems: [taglib:'raw']]

    def todoItems = { attrs, body ->
        List<ActionItem> items = attrs.items as List<ActionItem>
        if(items == null) {
            throwTagError("Remember 'items' on 'todoItems'")
        }
        List<ActionItem> pending = items.findAll { !it.done && it.due.after(new Date()) }
        List<ActionItem> overdue = items.findAll { !it.done && it.due.before(new Date()) }
        List<ActionItem> done = items.findAll { it.done }

        if(pending) {
            out << "<h3>Pending</h3><ul>"
            pending.each {
                out << "<li>${it.encodeAsHTML()}</li>"
            }
            out << "</ul>"
        }
        if(overdue) {
            out << "<h3>overdue</h3><ul>"
            overdue.each {
                out << "<li>${it}</li>"
            }
            out << "</ul>"
        }
        if(done) {
            out << "<h3>done</h3><ul>"
            done.each {
                out << "<li>${it.encodeAsHTML()}</li>"
            }
            out << "</ul>"
        }
    }
}

/*
<g:todoItems items="${todo.items}">
   <p>Kryf plyf</p>
</g:todoItems>
 */
