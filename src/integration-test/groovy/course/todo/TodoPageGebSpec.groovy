package course.todo

import geb.Module
import geb.Page
import geb.spock.GebSpec
import grails.testing.mixin.integration.Integration

@Integration
class TodoPageGebSpec extends GebSpec {
    def "test list todo items"() {
        when:
            to TodoIndexPage

        then:
            pageTitle == 'Todo List'

        and:
            todos.size() == 0
    }

    def "test create todo item"() {
        when:
            to TodoIndexPage
            create.click()
        then:
            at TodoCreatePage

        when:
            form.with {
                titleValue = 'GEB'
                descriptionValue = 'Learning GEB'
                submit.click()
            }

        then:
            at TodoShowPage
    }

}

class TodoIndexPage extends Page {
    static url = 'todo'

    static content = {
        pageTitle { $('h1').text() }
        create { $('a.create') }
        todos { $('table tbody tr').moduleList(TodoRow) }
    }
}

class TodoCreatePage extends Page {
    static url = '/todo/create'

    static content = {
        form { module TodoForm }
    }
}

class TodoShowPage extends Page {
    String id

    @Override
    String convertToPath(Object[] args) {
        "todo/show/$id/"
    }
    static content = {
        pageTitle { $('h1').text() }
        form { module TodoForm }
    }
}

class TodoForm extends Module {
    static base = { $('form') }

    static content = {
        titleValue { title().value() }
        descriptionValue { description().value() }
        submit { $('input[type="submit"]') }
    }
}


class TodoRow extends Module {
    static content = {
        cell { $("td", it) }
        title { cell(0).text() }
        description { cell(1).text() }
        items { cell(2).text() }
    }
}

