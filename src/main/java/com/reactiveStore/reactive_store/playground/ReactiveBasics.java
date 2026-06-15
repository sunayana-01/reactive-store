package com.reactiveStore.reactive_store.playground;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ReactiveBasics {

    ProductService productService;

    ReactiveBasics(ProductService productService){
        this.productService=productService;
    }

    //0 or 1
    Mono<String> mono = Mono.just("Hello Reactive");
    Mono<String> empty = Mono.empty();
    Mono<String> errorMono = Mono.error(new RuntimeException("failed"));

    //0 to N
    Flux<Integer> numbers = Flux.range(1, 5);
    Flux<String> names = Flux.just("Alice", "Bob", "Carol");

    @PostConstruct
    public void run() throws InterruptedException {

//        Mono<Product> productMono = Mono.fromCallable(() -> productService.findById("P1"));
//        productMono.subscribe(
//                product -> System.out.println("Mono: " + product),
//                error -> System.out.println("Error: " + error),
//                () -> System.out.println("Product not found")   );
//        productMono.subscribe(System.out::println);

//        Mono.fromCallable(() -> productService.findById("P1"))
//                .doOnNext(System.out::println)
//                .switchIfEmpty(Mono.fromRunnable(() -> System.out.println("product not found")))
//                .subscribe();

        Flux<Product> fromList = Flux.fromIterable(productService.findAll());
        fromList.subscribe(System.out::println); //silent failures if null

//        // COLD: each subscribe() replays from the start
//        Flux<Long> cold = Flux.interval(Duration.ofSeconds(1)).take(5);
//        cold.subscribe(v -> System.out.println("Sub1: " + v));
//        // 1 second later...Sub2 ALSO starts from 0 — independent sequence
//        Thread.sleep(2000);
//        cold.subscribe(v -> System.out.println("Sub2: " + v));

//        // HOT: share() multicasts to all current subscribers
//        Flux<Long> hot = Flux.interval(Duration.ofSeconds(1)).take(5).share(); // makes it hot
//        hot.subscribe(v -> System.out.println("Sub1: " + v));
//        Thread.sleep(2000);
//        // Sub2 joins at item 2 — misses 0 and 1
//        hot.subscribe(v -> System.out.println("Sub2: " + v));

//        Hooks.onOperatorDebug();
        Flux.just("1", "2", "abc", "4")
                .map(Integer::parseInt)
                .subscribe(
                        System.out::println,
                        Throwable::printStackTrace
                );
    }
}
