package com.ad.markalive.controller;

import com.ad.markalive.model.Bookmark;
import com.ad.markalive.service.BookmarkService;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/bookmark")
    public void addBookmark(@RequestParam String url){
        bookmarkService.createBookmark(url);
    }
    @DeleteMapping("/bookmark")
    public void deleteBookmark(@RequestParam int id){
        bookmarkService.deleteBookmark(id);
    }
}
