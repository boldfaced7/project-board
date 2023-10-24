package com.project.board.dto.response;

import com.project.board.dto.ArticleWithCommentsDto;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record ArticleWithCommentResponse(
        Long id,
        String title,
        String content,
        String hashtag,
        LocalDateTime createdAt,
        String email,
        String nickname,
        String userId,
        Set<ArticleCommentsResponse> articleCommentsRespons
) {

    public static ArticleWithCommentResponse of(Long id, String title, String content, String hashtag, LocalDateTime createdAt, String email, String nickname, String userId, Set<ArticleCommentsResponse> articleCommentsResponse) {
        return new ArticleWithCommentResponse(id, title, content, hashtag, createdAt, email, nickname, userId, articleCommentsResponse);
    }

    public static ArticleWithCommentResponse from(ArticleWithCommentsDto dto) {
        String nickname = dto.userAccountDto().nickname();
        if (nickname == null || nickname.isBlank()) {
            nickname = dto.userAccountDto().userId();
        }

        return new ArticleWithCommentResponse(
                dto.id(),
                dto.title(),
                dto.content(),
                dto.hashtag(),
                dto.createdAt(),
                dto.userAccountDto().email(),
                nickname,
                dto.userAccountDto().userId(),
                dto.articleCommentDtos().stream()
                        .map(ArticleCommentsResponse::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new))
        );
    }

}