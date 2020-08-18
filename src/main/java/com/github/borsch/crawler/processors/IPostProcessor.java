package com.github.borsch.crawler.processors;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class IPostProcessor implements Comparable<IPostProcessor> {

    @XmlAttribute(name = "order")
    private int order;

    public abstract String process(String value);

    @Override
    public int compareTo(IPostProcessor o) {
        return this.order - o.order;
    }
}
