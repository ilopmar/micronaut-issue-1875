package validator.header.controller;

import io.micronaut.core.version.annotation.Version;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Post;

import javax.validation.Valid;

@Controller("/")
public class VersionedController {

    public static final String VERSION_HEADER = "X-API-Version";

    @Post("/no-version")
    public Book noVersion(@Valid Book book) {
        return new Book(book.getTitle().toUpperCase());
    }

    @Version("1.0")
    @Post("/version-header-last")
    public Book versionHeaderLast(@Valid Book book, @Header(VERSION_HEADER) String apiVersion) {
        return new Book(book.getTitle().toUpperCase());
    }

    @Version("1.0")
    @Post("/version-header-first")
    public Book versionHeaderFirst(@Header(VERSION_HEADER) String apiVersion, @Valid Book book) {
        return new Book(book.getTitle().toUpperCase());
    }

}
