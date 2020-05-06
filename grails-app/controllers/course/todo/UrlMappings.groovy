package course.todo

class UrlMappings {

    static mappings = {
        "/api/todo"(resources: "todoApi") {
            collection {
                '/search'(controller: 'todoApi', action: 'search')
            }
        }

        "/$controller/$action?/$id?(.$format)?" {
            constraints {
                // apply constraints here
            }
        }


        "/"(view: "/index")
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
