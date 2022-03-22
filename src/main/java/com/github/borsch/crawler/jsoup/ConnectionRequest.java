package com.github.borsch.crawler.jsoup;

import java.util.List;

import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;

/**
 * @deprecated directly pass HTML via {@link com.github.borsch.crawler.PageCrawler#crawlHtml(String)}
 */
@Deprecated
@Builder
@Value
public class ConnectionRequest {

    @NonNull
    private final String url;
    @Singular
    private final List<Integer> ignoreHttpErrors;
    @Singular
    private final List<ConnectionParameter> cookies;
    @Singular
    private final List<ConnectionParameter> headers;
    @Builder.Default
    private final int timeoutSeconds = 20;

}
