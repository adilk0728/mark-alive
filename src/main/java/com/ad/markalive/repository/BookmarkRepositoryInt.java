package com.ad.markalive.repository;

import com.ad.markalive.model.Bookmark;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface BookmarkRepositoryInt extends Repository<Bookmark, Integer> {

    @Modifying
    @Query("UPDATE Bookmark b SET b.url = :url, b.created = :created, b.remind_After_Day = :remindAfterDay WHERE b.id = :id")
    void setFixedBookmarkFor(@Param("url") String url,
                        @Param("created") LocalDate created,
                        @Param("remindAfterDay") Integer remindAfterDay,
                        @Param("id") Integer id);

}
