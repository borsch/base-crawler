package ua.net.kurpiak.crawler.processors;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "replace")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReplacePostProcessor extends IPostProcessor {

    @XmlAttribute(name = "old")
    private String oldValue;

    @XmlAttribute(name = "new")
    private String newValue;

    @Override
    public String process(String value) {
        if (value == null)
            return null;

        return value.replace(oldValue, newValue);
    }
}
