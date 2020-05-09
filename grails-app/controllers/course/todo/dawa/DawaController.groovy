package course.todo.dawa

import grails.converters.JSON

class DawaController {

    DawaService dawaService

    def index() {
        render dawaService.regioner as JSON
    }
}
