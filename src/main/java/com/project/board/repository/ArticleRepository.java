package com.project.board.repository;

import com.project.board.domain.Article;
import com.project.board.domain.QArticle;
import com.project.board.repository.qureydsl.ArticleRepositoryCustom;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        ArticleRepositoryCustom,
        QuerydslPredicateExecutor<Article>,
        QuerydslBinderCustomizer<QArticle> {

    @Override
    @EntityGraph(attributePaths = {"userAccount"})
    Page<Article> findAll(Pageable pageable);

    @Override
    @EntityGraph(attributePaths = {"userAccount"})
    Optional<Article> findById(Long id);

    @EntityGraph(attributePaths = {"userAccount"})
    Page<Article> findByTitleContaining(String title, Pageable pageable);

    @EntityGraph(attributePaths = {"userAccount"})
    Page<Article> findByContentContaining(String content, Pageable pageable);

    @EntityGraph(attributePaths = {"userAccount"})
    Page<Article> findByUserAccountUserIdContaining(String userId, Pageable pageable);

    @EntityGraph(attributePaths = {"userAccount"})
    Page<Article> findByUserAccountNicknameContaining(String nickname, Pageable pageable);

    @EntityGraph(attributePaths = {"userAccount"})
    Page<Article> findByHashtag(String hashtag, Pageable pageable);

    void deleteByIdAndUserAccountUserId(Long articleId, String  userId);

    @Override
    default void customize(QuerydslBindings bindings, QArticle root) {
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.title, root.content, root.hashtag, root.createdAt, root.createdBy);

        bindings.bind(root.title).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
    }
}
