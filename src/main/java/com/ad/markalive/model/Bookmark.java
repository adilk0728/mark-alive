package com.ad.markalive.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDate;

public class Bookmark {
    @Id
    private int id;

    @Column("URL")
    private String url;
    @Column("CREATED")
    private LocalDate createdOn;

    @Column("REMIND_AFTER_DAY")
    private int remindAfter;
    public Bookmark(String url) {
        this.url = url;
    }

    public Bookmark(String url, LocalDate createdOn, int remindAfter){
        this(url);
        this.createdOn = createdOn;
        this.remindAfter = remindAfter;
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

    public int getRemindAfter() {
        return remindAfter;
    }

    public void setRemindAfter(int remindAfter) {
        this.remindAfter = remindAfter;
    }
}
