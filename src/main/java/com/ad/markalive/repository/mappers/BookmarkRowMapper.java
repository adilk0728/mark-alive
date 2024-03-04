package com.ad.markalive.repository.mappers;

import com.ad.markalive.model.Bookmark;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookmarkRowMapper implements RowMapper<Bookmark> {

    public static final String ID_COLUMN = "ID";
    public static final String URL_COLUMN = "URL";
    public static final String CREATED_ON_COLUMN = "CREATED";
    public static final String  REMIND_AFTER_DAY = "REMIND_AFTER_DAY";

    public BookmarkRowMapper() {
    }

    @Nullable
    @Override
    public Bookmark mapRow(ResultSet rs, int rowNum) throws SQLException {
       Bookmark bookmark = new Bookmark();

       bookmark.setId(rs.getInt(ID_COLUMN));
       bookmark.setUrl(rs.getString(URL_COLUMN));
       bookmark.setCreatedOn(rs.getDate(CREATED_ON_COLUMN).toLocalDate());
       bookmark.setRemindAfter(rs.getInt(REMIND_AFTER_DAY));

       return bookmark;
    }
}
