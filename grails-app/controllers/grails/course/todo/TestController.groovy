package grails.course.todo

import grails.converters.JSON
import grails.converters.XML

class TestController {

    def index() {
        render([text: 'Hej med dig'] as XML)

    }
}
