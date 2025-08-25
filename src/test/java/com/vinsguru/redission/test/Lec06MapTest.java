package com.vinsguru.redission.test;

import org.junit.jupiter.api.Test;
import org.redisson.api.RMapReactive;
import org.redisson.client.codec.StringCodec;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.HashMap;
import java.util.Map;

public class Lec06MapTest extends BaseTest{

    @Test
    public void mapTest() {
        RMapReactive<String, String> map = this.client.getMap("user:1", StringCodec.INSTANCE);
        Mono<String> name = map.put("name", "sam");
        Mono<String> age = map.put("age", "10");
        Mono<String> city = map.put("city", "atlanta");

        StepVerifier.create(name.concatWith(age).concatWith(city).then())
                .verifyComplete();
    }

    @Test
    public void mapTest2() {
        RMapReactive<String, String> map = this.client.getMap("user:2", StringCodec.INSTANCE);

        Map<String, String> javaMap = new HashMap<>();
        javaMap.put("name", "jake");
        javaMap.put("age", "30");
        javaMap.put("city", "miami");


        StepVerifier.create(map.putAll(javaMap).then())
                .verifyComplete();
    }
}
