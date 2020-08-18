package com.github.borsch.crawler.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "page")
public class PageDescription {

    private int crawlerDelay;
    private List<FieldDescription> fieldDescriptions;
    private List<Integer> allowedHttpErrorCodes;

    @XmlElement(name = "crawler-delay")
    public int getCrawlerDelay() {
        return crawlerDelay;
    }

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

    @XmlElementWrapper(name = "allowed-http-error-codes")
    @XmlElement(name = "code")
    public List<Integer> getAllowedHttpErrorCodes() {
        return allowedHttpErrorCodes;
    }

    public void setAllowedHttpErrorCodes(List<Integer> allowedHttpErrorCodes) {
        this.allowedHttpErrorCodes = allowedHttpErrorCodes;
    }
}
