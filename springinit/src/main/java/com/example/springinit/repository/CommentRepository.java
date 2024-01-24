package com.example.springinit.repository;

import com.example.springinit.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    //JpaPepo > PagingAndSortingRepo > CrudPepo > Repo

    // 특정 게시글 모든 댓글 조회
    @Query(value =
            "SELECT * " +
                    "FROM comment " +
                    "WHERE article_id = :articleId",
            nativeQuery = true)
    List<Comment> findByArticleId(Long articleId);


    // 특정 닉네임의 모든 댓글 조회
    List<Comment> findByNickname(String nickname);

}