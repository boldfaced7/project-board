package com.project.board.domain.type;

import java.util.Locale;

public enum SearchType {
    TITLE, CONTENT, ID, NICKNAME, HASHTAG;

    public static SearchType fromName(String type) {
        return SearchType.valueOf(type.toUpperCase(Locale.ENGLISH));
    }
}
