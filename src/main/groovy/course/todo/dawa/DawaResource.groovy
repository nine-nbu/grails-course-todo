package course.todo.dawa

import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import org.grails.web.json.JSONElement
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus

class DawaResource {
    @Autowired
    RestBuilder restBuilder

    @Value('${dawa.url}')
    String baseUrl

    JSONElement getJson(String uri) {
        RestResponse response = restBuilder.get("$baseUrl/$uri")

        if (response.statusCode == HttpStatus.OK) {
            return response.json
        } else {
            throw new Exception("Dawa request failed: ${response.status}")
        }
    }
}
