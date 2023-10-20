package com.project.board.service;

import com.project.board.domain.Article;
import com.project.board.domain.type.SearchType;
import com.project.board.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

public interface SearchTypeStrategy {
    Page<Article> searchArticles(String keyword, Pageable pageable);
    String getSearchType();

    @Component
    @RequiredArgsConstructor
    public static class TitleStrategy implements SearchTypeStrategy {

        private final ArticleRepository articleRepository;

        @Override
        public Page<Article> searchArticles(String keyword, Pageable pageable) {
            return articleRepository.findByTitleContaining(keyword, pageable);
        }

        @Override
        public String getSearchType() {
            return SearchType.TITLE.name();
        }
    }
    @Component
    @RequiredArgsConstructor
    public static class ContentStrategy implements SearchTypeStrategy {

        private final ArticleRepository articleRepository;

        @Override
        public Page<Article> searchArticles(String keyword, Pageable pageable) {
            return articleRepository.findByContentContaining(keyword, pageable);
        }

        @Override
        public String getSearchType() {
            return SearchType.CONTENT.name();
        }
    }

    @Component
    @RequiredArgsConstructor
    public static class IdStrategy implements SearchTypeStrategy {

        private final ArticleRepository articleRepository;

        @Override
        public Page<Article> searchArticles(String keyword, Pageable pageable) {
            return articleRepository.findByUserAccountUserIdContaining(keyword, pageable);
        }

        @Override
        public String getSearchType() {
            return SearchType.ID.name();
        }
    }

    @Component
    @RequiredArgsConstructor
    public static class NicknameStrategy implements SearchTypeStrategy {

        private final ArticleRepository articleRepository;

        @Override
        public Page<Article> searchArticles(String keyword, Pageable pageable) {
            return articleRepository.findByUserAccountNicknameContaining(keyword, pageable);
        }

        @Override
        public String getSearchType() {
            return SearchType.NICKNAME.name();
        }
    }

    @Component
    @RequiredArgsConstructor
    public static class HashtagStrategy implements SearchTypeStrategy {

        private final ArticleRepository articleRepository;

        @Override
        public Page<Article> searchArticles(String keyword, Pageable pageable) {
            return articleRepository.findByHashtag(keyword, pageable);
        }

        @Override
        public String getSearchType() {
            return SearchType.HASHTAG.name();
        }
    }
}
