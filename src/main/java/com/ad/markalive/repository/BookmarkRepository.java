package com.ad.markalive.repository;

import com.ad.markalive.model.Bookmark;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface BookmarkRepository extends CrudRepository<Bookmark, UUID> {
}
