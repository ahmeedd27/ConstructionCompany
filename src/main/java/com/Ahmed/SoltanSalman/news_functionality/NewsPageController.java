package com.Ahmed.SoltanSalman.news_functionality;


import com.Ahmed.SoltanSalman.comman_helpers.PageUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/news-page")
@RequiredArgsConstructor
@Tag(name = "News Page")
public class NewsPageController {

    private final NewsPageService newsPageService;

    @Operation(summary = "Get News Page", description = "Fetch news page content including header and news list")
    @GetMapping
    public ResponseEntity<NewsPage> getNewsPage() {
        return ResponseEntity.ok(newsPageService.getNewsPage());
    }

    @Operation(summary = "Update News Page Header", description = "Update the header (title, desc, imgUrl) of the news page")
    @PutMapping
    public ResponseEntity<NewsPage> updateNewsPageHeader(@RequestBody PageUpdateRequest request) {
        NewsPage updated = newsPageService.updateNewsPageHeader(request);
        return ResponseEntity.ok(updated);
    }
}
