package com.Ahmed.SoltanSalman.global_functionality;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/global")
@Tag(name = "Global Data Management")
public class GlobalController {
    private final GlobalService service;

    @GetMapping
    @Operation(summary = "Get Global Data", description = "get all links and other info")
    public ResponseEntity<Global> getGlobalData(){
        return ResponseEntity.ok(service.getGlobalData());
    }
    @PutMapping
    @Operation(summary = "Update Global Data",description = "update specific data in global")
    public ResponseEntity<Global> updateGlobalData(
            @RequestBody GlobalUpdateRequest request
    ){
        return ResponseEntity.ok(service.updateGlobalData(request));
    }
}
