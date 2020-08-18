package com.github.borsch.crawler.example.domain;

public class SearchResultLink {

    private String link;
    private String title;
    private String shortDescription;

    @Override
    public String toString() {
        return "SearchResultLink{" +
                "link='" + link + '\'' +
                ", title='" + title + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                "}\n";
    }
}
