package com.project.board.domain.constant;

import lombok.Getter;

import java.util.Locale;

public enum SearchType {
    TITLE("제목"),
    CONTENT("본문"),
    ID("유저 ID"),
    NICKNAME("닉네임"),
    HASHTAG("해시태그");

    @Getter private final String description;

    SearchType(String description) {
        this.description = description;
    }

    public static SearchType fromName(String type) {
        return SearchType.valueOf(type.toUpperCase(Locale.ENGLISH));
    }
}
