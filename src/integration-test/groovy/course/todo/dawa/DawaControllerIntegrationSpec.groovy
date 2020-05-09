package course.todo.dawa

import grails.plugins.rest.client.RestBuilder
import grails.testing.mixin.integration.Integration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import spock.lang.Shared
import spock.lang.Specification

@Integration
class DawaControllerIntegrationSpec extends Specification {

    @Value('${local.server.port}')
    Integer serverPort

    @Shared
    RestBuilder restBuilder = new RestBuilder()

    def "test rest response"() {
        when:
            def response = restBuilder.get("http://localhost:${serverPort}/dawa")
        then:
            response.statusCode == HttpStatus.OK

        and:
            response.json.first().kode == '1081'
    }

}
