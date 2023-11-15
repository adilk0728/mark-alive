package com.ad.markalive.controller;

import com.ad.markalive.model.Bookmark;
import com.ad.markalive.service.BookmarkService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookmarkController {

    private final BookmarkService bookmarkService;

    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @GetMapping("/bookmarks")
    public List<Bookmark> getAllBookmarks(){
        return bookmarkService.getAllBookmarks();
    }
    @PostMapping("/bookmark/{url}")
    public void addBookmark(@PathVariable String url){
        bookmarkService.createBookmark(url);
    }
}
