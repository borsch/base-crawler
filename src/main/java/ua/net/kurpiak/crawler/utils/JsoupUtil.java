package ua.net.kurpiak.crawler.utils;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

public class JsoupUtil {

    public Document parse(String url) {
        return parse(url, null);
    }

    public Document parse(String url, List<Integer> allowedHttpErrorCodes) {
        boolean allowedErrors = allowedHttpErrorCodes != null && !allowedHttpErrorCodes.isEmpty();

        try {
            Connection.Response response = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")
                    .ignoreHttpErrors(allowedErrors)
                    .timeout(1000 * 20)
                    .execute();

            int classOfCode = response.statusCode() / 100;

            if (classOfCode != 2 && classOfCode != 3) {
                if (allowedErrors) {
                    for (Integer errorCode : allowedHttpErrorCodes) {
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
