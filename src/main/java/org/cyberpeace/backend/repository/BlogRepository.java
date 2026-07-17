package org.cyberpeace.backend.repository;

import org.cyberpeace.backend.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findByCategory(String category);
    List<Blog> findByTitleContainingIgnoreCaseOrTagsContainingIgnoreCase(String titleQuery, String tagQuery);
}
