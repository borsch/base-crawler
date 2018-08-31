package ua.net.kurpiak.crawler.utils;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class JsoupUtil {

    public Document parse(String url) {
        return parse(url, false);
    }

    public Document parse(String url, boolean allowErrors) {
        try {
            Connection.Response response = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")
                    .ignoreHttpErrors(allowErrors)
                    .timeout(1000 * 20)
                    .cookie("__cfduid", "dd68fe86aad973df06387ecf14cd9b4221535566791")
                    .execute();

            int classOfCode = response.statusCode() / 100;

            if (classOfCode != 2 && classOfCode != 3 && !allowErrors) {
                return null;
            }

            return response.parse();
        } catch (IOException e) {
            e.printStackTrace();

            return null;
        }
    }

}
