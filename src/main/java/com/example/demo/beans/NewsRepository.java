package com.example.demo.beans;

import com.example.demo.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Long> {

    @Query("select n from News n where n.title like %?1% and n.cityId = ?2 or n.content like %?1% and n.cityId = ?2")
    Page<News> findByTitleContentCity(String title, Long cityId ,  Pageable pageable);

    @Query("select n from News n where n.title like %?1% or n.content like %?1%")
    Page<News> findByTitleContent(String key, Pageable pageable);

    @Query("select n from News n where n.cityId = ?1")
    Page<News> findByCityId(Long cityId, Pageable pageable);

    News findByContent(String content);

    Optional<News> findById(Long id);
}
