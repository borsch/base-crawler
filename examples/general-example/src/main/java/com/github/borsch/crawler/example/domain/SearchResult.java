package com.github.borsch.crawler.example.domain;

import java.util.List;

public class SearchResult {

    private String query;
    private List<SearchResultLink> links;
    private List<AlternativeSearch> alternativeSearches;

    @Override
    public String toString() {
        return "SearchResult{" +
                "query='" + query + '\'' +
                ", links=" + links +
                ", alternativeSearches=" + alternativeSearches +
                '}';
    }
}
