package com.ad.markalive.model;

import com.ad.markalive.util.PropertiesLoader;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

@Component
public class Bookmark {
    @Id
    private int id;
    private String url;
    @Column("CREATED")
    private LocalDate createdOn;
    @Column("REMIND_AFTER_DAY")
    private Integer remindAfter;
    private boolean remind;
    public Bookmark(){
    }

    public Bookmark(String url){
        this.url = url;
    }
    public Bookmark(String url, LocalDate createdOn, Integer remindAfter) {
        this(url);
        this.createdOn = createdOn;
        this.remindAfter = Objects.requireNonNullElseGet(remindAfter, () -> {
            try {
                return Integer.valueOf(PropertiesLoader.loadProperties().getProperty("config.defaultRemindAfterDays"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public Integer getRemindAfter() {
        return remindAfter;
    }

    public void setRemindAfter(Integer remindAfter) {
        this.remindAfter = remindAfter;
    }

    public boolean getRemind() {
        return remind;
    }

    public void setRemind(boolean remind) {
        this.remind = remind;
    }
}
