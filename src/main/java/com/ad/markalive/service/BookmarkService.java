package com.ad.markalive.service;

import com.ad.markalive.model.Bookmark;
import com.ad.markalive.repository.BookmarkRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;

    public BookmarkService(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    public List<Bookmark> getAllBookmarks(){
        List<Bookmark> bookmarkList = new ArrayList<>();
        bookmarkRepository.findAll().forEach(bookmarkList::add);
        return bookmarkList;
    }

    public void createBookmark(String url){
        Bookmark bookmarkToWrite = new Bookmark(url);
        bookmarkRepository.save(bookmarkToWrite);
    }
}
