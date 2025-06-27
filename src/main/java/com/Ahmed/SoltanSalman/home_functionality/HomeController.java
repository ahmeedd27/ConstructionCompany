package com.Ahmed.SoltanSalman.home_functionality;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
@Tag(name = "Home Page")
public class HomeController {
    private final HomeService service;
    @GetMapping
    @Operation(summary = "Get Home Page", description = "Fetch Home page content")
    public ResponseEntity<Home> home(){
        return ResponseEntity.ok(service.getHome());
    }
    @PutMapping
    @Operation(summary = "Update Home Page", description = "Update the home page")
    public ResponseEntity<Home> updateHomePage(
            @RequestBody HomeUpdateRequest request
    ){
        return ResponseEntity.ok(service.updateHomePage(request));
    }


}
