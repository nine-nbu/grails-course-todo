package course.todo.dawa

import grails.plugins.rest.client.RestBuilder
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus

@Slf4j
class DawaResource {

    @Value('${dawa.url}')
    String baseUrl

    @Autowired
    RestBuilder restBuilder

    Iterable getJson(String uri) {
        def response = restBuilder.get("$baseUrl/$uri")
        if (response.statusCode == HttpStatus.OK) {
            return response.json as Iterable

        } else {
            log.error("Response: $response.statusCode")
            throw new RuntimeException("Dawa resource error: $response.status")
        }

    }
}
