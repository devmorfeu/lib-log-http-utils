package br.com.libloghttputils.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class ControllerTest {

    private final AtomicLong count = new AtomicLong();

    @GetMapping("test/{id}")
    public ResponseTest getTest(@PathVariable("id") Long id) {
        return ResponseTest.builder().id(id).message("Hello world!").build();
    }

    @GetMapping("test/{id}/{testTwo}")
    public ResponseTest getTestMultiPathVariable(@PathVariable("id") Long id, @PathVariable("testTwo") String testTwo) {
        return ResponseTest.builder().id(id).message("Hello world!").build();
    }

    @GetMapping("test")
    public ResponseTest getTestMultiParam(@RequestParam("valueOne") Long testOne, @RequestParam("valueTwo") Long testTwo) {
        return ResponseTest.builder().id(count.incrementAndGet()).message("Hello world!").build();
    }

    @PostMapping("test")
    public ResponseTest creatTest(@RequestBody RequestTest requestTest) {
        return ResponseTest.builder().id(count.incrementAndGet()).message(requestTest.getMessage()).build();
    }

    @PostMapping("noContentTest")
    public ResponseEntity<?> noContentTest(@RequestBody RequestTest requestTest) {
        return ResponseEntity.noContent().build();
    }
}
