package com.github.borsch.crawler.example;

import java.io.IOException;
import java.net.URL;

import org.jsoup.Jsoup;

import com.github.borsch.crawler.PageCrawler;
import com.github.borsch.crawler.domain.PageDescription;
import com.github.borsch.crawler.example.domain.SearchResult;
import com.github.borsch.crawler.xml.IXmlProcessor;
import com.github.borsch.crawler.xml.LocalXmlProcessor;

public class Main {

    public static void main(String[] args) throws IOException {
        IXmlProcessor xmlProcessor = new LocalXmlProcessor();
        PageDescription pageDescription = xmlProcessor.parse("/search_result.xml");

        PageCrawler<SearchResult> pageCrawler = new PageCrawler<>(pageDescription, SearchResult::new);

        String html = Jsoup.parse(new URL("https://www.google.com.ua/search?q=crawler"), 20_000).html();

        System.out.println(pageCrawler.crawlHtml(html));
    }

}
