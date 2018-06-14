package io.github.cd871127.hodgepodge.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class TestController {
    public TestController() {
        System.out.println("TestController");
    }

    @RequestMapping("test")
    String test() {
        return "test";
    }
}
