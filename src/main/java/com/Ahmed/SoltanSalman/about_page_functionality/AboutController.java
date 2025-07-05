package com.Ahmed.SoltanSalman.about_page_functionality;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/about-page")
@Tag(name = "About-Page Management")
public class AboutController {
    private final AboutService service;

    @GetMapping
    @Operation(summary = "Get About Page Data", description = "Get About Page")
    public ResponseEntity<About> getAboutPage() {
        return ResponseEntity.ok(service.getAboutPage());
    }

    @PutMapping
    @Operation(summary = "Update About Page", description = "Update About Page Data")
    public ResponseEntity<About> updateAboutPage(
            @RequestBody AboutUpdateRequest request
    ) {
        return ResponseEntity.ok(service.updateAboutData(request));
    }

}
