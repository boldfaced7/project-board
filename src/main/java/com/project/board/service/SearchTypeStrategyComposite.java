package com.project.board.service;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class SearchTypeStrategyComposite {
    private final Map<String, SearchTypeStrategy> SearchTypeStrategyMap;

    public SearchTypeStrategyComposite(Set<SearchTypeStrategy> strategies) {
        SearchTypeStrategyMap =strategies.stream()
                .collect(Collectors.toMap(
                        SearchTypeStrategy::getSearchType,
                        Function.identity()
                ));
    }

    public SearchTypeStrategy getSearchTypeStrategy(String type) {
        return SearchTypeStrategyMap.get(type);
    }
}
