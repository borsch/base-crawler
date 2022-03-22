package com.github.borsch.crawler.jsoup;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * @deprecated directly pass HTML via {@link com.github.borsch.crawler.PageCrawler#crawlHtml(String)}
 */
@Deprecated
public class JsoupUtil {

    public Document parse(String url) {
        return parse(ConnectionRequest.builder().url(url).build());
    }

    public Document parse(ConnectionRequest request) {
        boolean allowedErrors = request.getIgnoreHttpErrors() != null && !request.getIgnoreHttpErrors().isEmpty();

        try {
            Connection connection = Jsoup.connect(request.getUrl())
                .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")
                .ignoreHttpErrors(allowedErrors)
                .timeout(1000 * request.getTimeoutSeconds());

            if (request.getCookies() != null) {
                request.getCookies().forEach(cookie -> connection.cookie(cookie.getName(), cookie.getValue()));
            }

            if (request.getHeaders() != null) {
                request.getHeaders().forEach(cookie -> connection.header(cookie.getName(), cookie.getValue()));
            }

            Connection.Response response = connection.execute();

            int classOfCode = response.statusCode() / 100;

            if (classOfCode != 2 && classOfCode != 3) {
                if (allowedErrors) {
                    for (Integer errorCode : request.getIgnoreHttpErrors()) {
                        if (errorCode.equals(response.statusCode())) {
                            return response.parse();
                        }
                    }
                }

                return null;
            }

            return response.parse();
        } catch (IOException e) {
            e.printStackTrace();

            return null;
        }
    }

}
