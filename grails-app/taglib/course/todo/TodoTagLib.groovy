package course.todo

class TodoTagLib {
    static defaultEncodeAs = [taglib: 'html']
    static encodeAsForTags = [todoItems: [taglib:'raw'], otherTagName: [taglib:'none']]

    def todoItems = { attrs, body ->
        List<ActionItem> items = attrs.items as List<ActionItem>
        if (items == null) {
            throwTagError('TagLib [t:todoItems] missing attribute [items]')
        }

        List<ActionItem> pending = items.findAll { !it.done && it.due.after(new Date()) }
        List<ActionItem> overdue = items.findAll { !it.done && it.due.before(new Date()) }
        List<ActionItem> done = items.findAll { it.done }

        Closure render = body ?: { it }

        renderList('Pending', pending, render)
        renderList('Overdue', overdue, render)
        renderList('Done', done, render)
    }

    private void renderList(String title, List<ActionItem> items, Closure render) {
        if(items) {
            out << "<h3>$title:</h3><ul>"

            items.sort { a,b -> a.due <=> b.due } each {
                pageScope.item = it
                out << "<li>${render.call(it)}</li>"
            }
            out << "</li>"

        }
    }
}
