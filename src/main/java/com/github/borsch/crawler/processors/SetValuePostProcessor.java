package com.github.borsch.crawler.processors;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "set-value")
@XmlAccessorType(XmlAccessType.FIELD)
public class SetValuePostProcessor extends IPostProcessor {

    @XmlAttribute(name = "what")
    private String what;

    @Override
    public String process(String value) {
        return what;
    }
}
