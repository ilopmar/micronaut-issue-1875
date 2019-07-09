package validator.header.controller

import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MicronautTest
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest
class VersionedControllerSpec extends Specification {

    @Client("/")
    @Inject
    RxHttpClient client

    void 'no version header'() {
        given:
        def book = new Book(title)

        when:
        HttpResponse<Book> response = client.toBlocking().exchange(HttpRequest.POST("/no-version", book), Argument.of(Book))

        then:
        notThrown(Exception)
        response.status == HttpStatus.OK
        response.body().title == title.toUpperCase()

        where:
        title = 'The Stand'
    }

    void 'the version header is the last parameter in controller'() {
        given:
        def book = new Book(title)

        when:
        HttpResponse<Book> response = client.toBlocking().exchange(
            HttpRequest.POST("/version-header-last", book).header(VersionedController.VERSION_HEADER, "1.0"),
            Argument.of(Book)
        )

        then:
        notThrown(Exception)
        response.status == HttpStatus.OK
        response.body().title == title.toUpperCase()

        where:
        title = 'The Stand'
    }

    void 'the version header is the first parameter in controller'() {
        given:
        def book = new Book(title)

        when:
        HttpResponse<Book> response = client.toBlocking().exchange(
            HttpRequest.POST("/version-header-first", book).header(VersionedController.VERSION_HEADER, "1.0"),
            Argument.of(Book)
        )

        then:
        notThrown(Exception)
        response.status == HttpStatus.OK
        response.body().title == title.toUpperCase()

        where:
        title = 'The Stand'
    }
}
