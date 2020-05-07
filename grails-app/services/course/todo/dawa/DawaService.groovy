package course.todo.dawa

import grails.gorm.transactions.Transactional
import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import grails.web.databinding.DataBinder
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus

@Transactional
class DawaService implements DataBinder {

    DawaResource dawaResource

    List<Region> getRegioner() {

        def json = dawaResource.getJson('regionxer')
        return json.collect {
            def region = new Region()
            bindData(region, it)
            region
        }
    }
}
