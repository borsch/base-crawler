package ua.net.kurpiak.crawler.generalexample;

import ua.net.kurpiak.crawler.PageCrawler;
import ua.net.kurpiak.crawler.domain.PageDescription;
import ua.net.kurpiak.crawler.generalexample.domain.SearchResult;
import ua.net.kurpiak.crawler.utils.JsoupUtil;
import ua.net.kurpiak.crawler.xml.IXmlProcessor;
import ua.net.kurpiak.crawler.xml.LocalXmlProcessor;

public class Main {

    public static void main(String[] args) {
        JsoupUtil jsoupUtil = new JsoupUtil();
        IXmlProcessor xmlProcessor = new LocalXmlProcessor();
        PageDescription pageDescription = xmlProcessor.parse("/search_result.xml");

        PageCrawler<SearchResult> pageCrawler = new PageCrawler<>(pageDescription, jsoupUtil, SearchResult::new);

        System.out.println(pageCrawler.crawl("https://www.google.com.ua/search?q=crawler"));
    }

}
