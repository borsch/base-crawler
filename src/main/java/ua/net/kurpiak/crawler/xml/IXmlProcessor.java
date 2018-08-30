package ua.net.kurpiak.crawler.xml;


import ua.net.kurpiak.crawler.domain.PageDescription;

public interface IXmlProcessor {

    PageDescription parse(String location);

}
