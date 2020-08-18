package com.github.borsch.crawler.xml;


import com.github.borsch.crawler.domain.PageDescription;

public interface IXmlProcessor {

    PageDescription parse(String location);

}
