package com.Ahmed.SoltanSalman.controller;

import com.Ahmed.SoltanSalman.global_helpers.Global;
import com.Ahmed.SoltanSalman.service.GlobalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Global", description = "Endpoints for global data")
public class GlobalController {
    private final GlobalService service;

    @GetMapping("/global")
    @Operation(summary = "Retrieve global data", description = "Fetches global configuration or data")
    public Global getData() {
        return service.getData();
    }
}