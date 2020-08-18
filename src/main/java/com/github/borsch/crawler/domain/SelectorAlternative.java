package com.github.borsch.crawler.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "alternative")
public class SelectorAlternative {

    private SourceType sourceType;
    private String source;
    private String selector;

    @XmlAttribute(name = "source-type")
    public SourceType getSourceType() {
        return sourceType;
    }

    public void setSourceType(SourceType sourceType) {
        this.sourceType = sourceType;
    }

    @XmlAttribute(name = "source")
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @XmlAttribute(name = "selector")
    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }

    public static enum SourceType {
        value,
        attribute
    }

}
