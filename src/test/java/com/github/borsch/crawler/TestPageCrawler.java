package com.github.borsch.crawler;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import com.github.borsch.crawler.domain.CarTestEntity;
import com.github.borsch.crawler.domain.PageDescription;
import com.github.borsch.crawler.jsoup.JsoupUtil;
import com.github.borsch.crawler.xml.IXmlProcessor;
import com.github.borsch.crawler.xml.LocalXmlProcessor;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPageCrawler {

    @Test
    public void testCrawlMethod() {
        IXmlProcessor xmlProcessor = new LocalXmlProcessor();
        PageDescription description = xmlProcessor.parse("/xml/car.xml");
        PageCrawler<CarTestEntity> carCrawler = new PageCrawler<>(description, new JsoupUtil(), CarTestEntity::new);
        String html =
                "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "    <title>Title</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "    <div class=\"name\">Ferrari 458 Italia</div>\n" +
                        "    <div class=\"numberOfHP\">500</div>\n" +
                        "    <a href=\"https://uk.wikipedia.org/wiki/Ferrari_458_Italia#/media/File:Ferrari_458_Italia_--_05-18-2011.jpg\">Link</a>\n" +
                        "</body>\n" +
                        "</html>";
        Document document = Jsoup.parse(html);

        CarTestEntity carTestEntity = carCrawler.crawl(document);
        assertEquals("Ferrari 458 Italia", carTestEntity.getName());
        assertEquals("500", carTestEntity.getNumberOfHP());
        assertEquals("https://uk.wikipedia.org/wiki/Ferrari_458_Italia#/media/File:Ferrari_458_Italia_--_05-18-2011.jpg", carTestEntity.getLinkToPicture());
    }


}
