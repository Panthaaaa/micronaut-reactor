package io.micronaut.reactor.http.client;

import io.micronaut.core.io.buffer.ByteBuffer;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import reactor.core.publisher.Flux;

/**
 * Reactor variation of the {@link HttpClient} interface.
 *
 * @author graemerocher
 * @since 1.0.0
 */
public interface ReactorHttpClient extends HttpClient {

    @Override
    default <I, O> Flux<HttpResponse<O>> exchange(HttpRequest<I> request, Argument<O> bodyType) {
        return Flux.from(HttpClient.super.exchange(request, bodyType));
    }

    @Override
    <I, O, E> Flux<HttpResponse<O>> exchange(HttpRequest<I> request, Argument<O> bodyType, Argument<E> errorType);

    @Override
    default <I, O, E> Flux<O> retrieve(HttpRequest<I> request, Argument<O> bodyType, Argument<E> errorType) {
        return Flux.from(HttpClient.super.retrieve(request, bodyType, errorType));
    }

    @Override
    default <I> Flux<HttpResponse<ByteBuffer>> exchange(HttpRequest<I> request) {
        return Flux.from(HttpClient.super.exchange(request));
    }

    @Override
    default Flux<HttpResponse<ByteBuffer>> exchange(String uri) {
        return Flux.from(HttpClient.super.exchange(uri));
    }

    @Override
    default <O> Flux<HttpResponse<O>> exchange(String uri, Class<O> bodyType) {
        return Flux.from(HttpClient.super.exchange(uri, bodyType));
    }

    @Override
    default <I, O> Flux<HttpResponse<O>> exchange(HttpRequest<I> request, Class<O> bodyType) {
        return Flux.from(HttpClient.super.exchange(request, bodyType));
    }

    @Override
    default <I, O> Flux<O> retrieve(HttpRequest<I> request, Argument<O> bodyType) {
        return Flux.from(HttpClient.super.retrieve(request, bodyType));
    }

    @Override
    default <I, O> Flux<O> retrieve(HttpRequest<I> request, Class<O> bodyType) {
        return retrieve(
                request,
                Argument.of(bodyType),
                DEFAULT_ERROR_TYPE
        );
    }

    @Override
    default <I> Flux<String> retrieve(HttpRequest<I> request) {
        return retrieve(
                request,
                Argument.STRING,
                DEFAULT_ERROR_TYPE
        );
    }

    @Override
    default Flux<String> retrieve(String uri) {
        return retrieve(
                HttpRequest.GET(uri),
                Argument.STRING,
                DEFAULT_ERROR_TYPE
        );
    }

}
