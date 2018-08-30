package ua.net.kurpiak.crawler.tester;

import ua.net.kurpiak.crawler.PageCrawler;
import ua.net.kurpiak.crawler.domain.PageDescription;
import ua.net.kurpiak.crawler.domain.callback.ICrawlerCallback;
import ua.net.kurpiak.crawler.utils.JsoupUtil;
import ua.net.kurpiak.crawler.xml.IXmlProcessor;
import ua.net.kurpiak.crawler.xml.LocalXmlProcessor;

public class Tester {

    public static void main(String[] args) {
        IXmlProcessor xmlProcessor = new LocalXmlProcessor();
        PageDescription pageDescription = xmlProcessor.parse("/test_geoscienceworld.xml");
        JsoupUtil jsoupUtil = new JsoupUtil();

        PageCrawler<Article> pageCrawler = new PageCrawler<>(pageDescription, jsoupUtil, new ICrawlerCallback<Article>() {
            @Override
            public Article newInstance() {
                return new Article();
            }

            @Override
            public void handle(Article object) {
                System.out.println(object);
            }
        });

        pageCrawler.crawl("https://pubs.geoscienceworld.org/gsa/geosphere/article/14/4/1385/531128/the-relative-roles-of-inheritance-and-long-term");
    }

}
