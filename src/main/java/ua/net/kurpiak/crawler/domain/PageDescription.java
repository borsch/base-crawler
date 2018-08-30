package ua.net.kurpiak.crawler.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "page")
public class PageDescription {

    private int crawlerDelay;
    private boolean allowHttpErrors;
    private List<FieldDescription> fieldDescriptions;

    @XmlElement(name = "crawler-delay")
    public int getCrawlerDelay() {
        return crawlerDelay;
    }

    public void setCrawlerDelay(int crawlerDelay) {
        this.crawlerDelay = crawlerDelay;
    }

    @XmlElement(name = "allow-http-errors")
    public boolean isAllowHttpErrors() {
        return allowHttpErrors;
    }

    public void setAllowHttpErrors(boolean allowHttpErrors) {
        this.allowHttpErrors = allowHttpErrors;
    }

    @XmlElementWrapper(name = "fields")
    @XmlElement(name = "field-description")
    public List<FieldDescription> getFieldDescriptions() {
        return fieldDescriptions;
    }

    public void setFieldDescriptions(List<FieldDescription> fieldDescriptions) {
        this.fieldDescriptions = fieldDescriptions;
    }
}
