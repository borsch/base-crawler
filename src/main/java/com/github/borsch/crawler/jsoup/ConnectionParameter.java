package com.github.borsch.crawler.jsoup;

import lombok.Value;

/**
 * @deprecated directly pass HTML via {@link com.github.borsch.crawler.PageCrawler#crawlHtml(String)}
 */
@Deprecated
@Value(staticConstructor = "of")
public class ConnectionParameter {

    private final String name;
    private final String value;

}
