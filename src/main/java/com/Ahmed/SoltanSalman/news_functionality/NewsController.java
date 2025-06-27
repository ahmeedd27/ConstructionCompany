package com.Ahmed.SoltanSalman.news_functionality;

import com.Ahmed.SoltanSalman.comman_helpers.COARequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
@Tag(name = "News Management")
public class NewsController {

    private final NewsService newsService;

    @Operation(summary = "Get All News", description = "Fetch the list of all news articles")
    @GetMapping
    public ResponseEntity<List<New>> getAllNews() {
        return ResponseEntity.ok(newsService.getAllNews());
    }

    @Operation(summary = "Get New By Slug", description = "Fetch specific new by it is slug")
    @GetMapping("/{slug}")
    public ResponseEntity<New> getNew(
            @PathVariable String slug
    ) {
        return ResponseEntity.ok(newsService.getNewBySlug(slug));
    }

    @Operation(summary = "Add News", description = "Create a new news article")
    @PostMapping
    public ResponseEntity<New> addNews(@RequestBody @Valid CreateNewsRequest request) {
        return ResponseEntity.ok(newsService.addNew(request));
    }

    @Operation(summary = "Update News", description = "Update an existing new by its slug")
    @PutMapping("/{slug}")
    public ResponseEntity<New> updateNews(@PathVariable String slug,
                                          @RequestBody NewRequest request) {
        return ResponseEntity.ok(newsService.updateNew(slug, request));
    }

    @Operation(summary = "Delete News", description = "Delete a news article by its slug")
    @DeleteMapping("/{slug}")
    public ResponseEntity<String> deleteNews(@PathVariable String slug) {
        return ResponseEntity.ok(newsService.deleteNewBySlug(slug));
    }

}
