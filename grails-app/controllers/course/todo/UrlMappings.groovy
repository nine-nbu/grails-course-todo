package course.todo

class UrlMappings {

    static mappings = {
        "/api/todo"(resources: "todo")

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }



        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
