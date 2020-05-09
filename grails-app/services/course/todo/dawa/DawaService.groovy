package course.todo.dawa


import grails.web.databinding.DataBinder
import groovy.transform.CompileStatic

@CompileStatic
class DawaService implements DataBinder {

    DawaResource dawaResource

    List<Region> getRegioner() {
        def response = dawaResource.getJson("regioner")
        return response.collect { it ->
            def region = new Region()
            bindData(region, it)
            return region
        }
    }
}
