package com.github.borsch.crawler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.github.borsch.crawler.domain.CarTestEntity;
import com.github.borsch.crawler.domain.PageDescription;
import com.github.borsch.crawler.xml.LocalXmlProcessor;

class TestPageCrawler {

    @Test
    void testCrawlMethod() {
        PageDescription description = new LocalXmlProcessor().parse("/xml/car.xml");
        PageCrawler<CarTestEntity> carCrawler = new PageCrawler<>(description, CarTestEntity::new);
        String html = FileUtils.getResourceContent("testdata/car.html");

        CarTestEntity carTestEntity = carCrawler.crawlHtml(html);
        assertEquals("Ferrari 458 Italia", carTestEntity.getName());
        assertEquals("500", carTestEntity.getNumberOfHP());
        assertEquals("https://uk.wikipedia.org/wiki/Ferrari_458_Italia#/media/File:Ferrari_458_Italia_--_05-18-2011.jpg", carTestEntity.getLinkToPicture());
    }


}
