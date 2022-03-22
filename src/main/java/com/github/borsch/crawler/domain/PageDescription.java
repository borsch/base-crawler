package com.github.borsch.crawler.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "page")
public class PageDescription {

    @Deprecated
    private int crawlerDelay;
    private List<FieldDescription> fieldDescriptions;
    @Deprecated
    private List<Integer> allowedHttpErrorCodes;

    @Deprecated
    @XmlElement(name = "crawler-delay")
    public int getCrawlerDelay() {
        return crawlerDelay;
    }

    @Deprecated
    public void setCrawlerDelay(int crawlerDelay) {
        this.crawlerDelay = crawlerDelay;
    }

    @XmlElementWrapper(name = "fields")
    @XmlElement(name = "field-description")
    public List<FieldDescription> getFieldDescriptions() {
        return fieldDescriptions;
    }

    public void setFieldDescriptions(List<FieldDescription> fieldDescriptions) {
        this.fieldDescriptions = fieldDescriptions;
    }

    @Deprecated
    @XmlElementWrapper(name = "allowed-http-error-codes")
    @XmlElement(name = "code")
    public List<Integer> getAllowedHttpErrorCodes() {
        return allowedHttpErrorCodes;
    }

    @Deprecated
    public void setAllowedHttpErrorCodes(List<Integer> allowedHttpErrorCodes) {
        this.allowedHttpErrorCodes = allowedHttpErrorCodes;
    }
}
