package course.todo.dawa

import grails.gorm.transactions.Transactional
import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import grails.web.databinding.DataBinder
import org.springframework.http.HttpStatus

@Transactional
class DawaService implements DataBinder {

    String baseUrl = 'https://dawa.aws.dk'

    List<Region> getRegioner() {
        RestBuilder restBuilder = new RestBuilder()
        RestResponse response = restBuilder.get("$baseUrl/regioner")

        if(response.statusCode == HttpStatus.OK) {
            return response.json.collect {
                def region = new Region()
                bindData(region, it)
                region
            }
        }
        return []
    }
}
