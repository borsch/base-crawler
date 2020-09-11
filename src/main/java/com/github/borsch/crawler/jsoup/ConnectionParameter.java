package com.github.borsch.crawler.jsoup;

import lombok.Value;

@Value(staticConstructor = "of")
public class ConnectionParameter {

    private final String name;
    private final String value;

}
