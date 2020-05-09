import course.todo.dawa.DawaResource
import grails.plugins.rest.client.RestBuilder

// Place your Spring DSL code here
beans = {
    restBuilder(RestBuilder)
    dawaResource(DawaResource)
}
