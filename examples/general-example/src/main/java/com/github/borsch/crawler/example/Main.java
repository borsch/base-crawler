package com.github.borsch.crawler.example;

import com.github.borsch.crawler.example.domain.SearchResult;

import com.github.borsch.crawler.PageCrawler;
import com.github.borsch.crawler.domain.PageDescription;
import com.github.borsch.crawler.utils.JsoupUtil;
import com.github.borsch.crawler.xml.IXmlProcessor;
import com.github.borsch.crawler.xml.LocalXmlProcessor;

public class Main {

    public static void main(String[] args) {
        JsoupUtil jsoupUtil = new JsoupUtil();
        IXmlProcessor xmlProcessor = new LocalXmlProcessor();
        PageDescription pageDescription = xmlProcessor.parse("/search_result.xml");

        PageCrawler<SearchResult> pageCrawler = new PageCrawler<>(pageDescription, jsoupUtil, SearchResult::new);

        System.out.println(pageCrawler.crawl("https://www.google.com.ua/search?q=crawler"));
    }

}
