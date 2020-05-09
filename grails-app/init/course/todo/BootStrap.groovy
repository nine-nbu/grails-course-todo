package course.todo

import grails.util.Environment

class BootStrap {

    def init = { servletContext ->
        if(Environment.current != Environment.DEVELOPMENT) {
            return
        }
        def list = [
                [title: 'Learn Grails', items: [
                        [name: 'The basics', description: 'Learn what a Grails project is', due: new Date() + 5, done: false],
                        [name: 'Domain objects', description: 'How to use Domain objects in Grails', due: new Date() + 10, done: false]]
                ],
                [title: 'Start new position', items: [
                        [name: 'Pick new computer', description: 'Should it be a Mac or a PC', due: new Date()-5, done: true],
                        [name: 'Install OS', description: 'If PC, pick Ubuntu :-)', due: new Date() - 4, done: true],
                        [name: 'Receive coffee', description: 'Brought by the piccolo', due: new Date() - 4, done: true]
                ]],
                [title: 'First day at work', items: [
                        [name: 'Bring cake', description: 'or two', due: new Date()+5, done: false],
                        [name: 'Look at faces on website', description: 'Who is who?', due: new Date() + 5, done: false],
                        [name: 'Check if Google Meet is working', description: '', due: new Date() + 5, done: false]
                ]]
        ]

        Todo.withNewSession {
            list.each {
                try {
                    new Todo(it).save(failOnError: true, validate: false)
                } catch (e) {
                    println it
                    throw e
                }
            }
        }
    }
    def destroy = {
    }
}
