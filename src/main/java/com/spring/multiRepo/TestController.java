package com.spring.multiRepo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @RequestMapping(path = "/multiRepo", method = RequestMethod.GET)
    public ResponseEntity testMultiRepo(){
        return ResponseEntity.ok("==== success ====");
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ResponseEntity test(){
        return ResponseEntity.ok("===== fail =====");
    }
}
