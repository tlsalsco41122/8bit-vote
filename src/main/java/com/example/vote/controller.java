package com.example.vote;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class controller {

    @GetMapping(value = "/call")
    public ResponseEntity<List<DTO>> Call(@RequestParam("name") String name)  throws Exception {
        List<DTO> data = new ArrayList<>();
        return ResponseEntity.ok(data);
    }

}
