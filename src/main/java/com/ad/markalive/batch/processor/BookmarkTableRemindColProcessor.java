package com.ad.markalive.batch.processor;

import com.ad.markalive.model.Bookmark;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.Nullable;

import java.time.LocalDate;

public class BookmarkTableRemindColProcessor implements ItemProcessor<Bookmark, Bookmark> {
    @Nullable
    @Override
    public Bookmark process(Bookmark item) throws Exception {
        if(!item.getRemind() &&
        (item.getCreatedOn().plusDays(item.getRemindAfter()).isBefore(LocalDate.now()) ||
         item.getCreatedOn().plusDays(item.getRemindAfter()).isEqual(LocalDate.now()))) {
            item.setRemind(true);
        }
        return item;
    }
}
