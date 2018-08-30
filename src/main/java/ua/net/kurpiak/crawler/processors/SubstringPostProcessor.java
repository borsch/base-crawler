package ua.net.kurpiak.crawler.processors;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "substring")
@XmlAccessorType(XmlAccessType.FIELD)
public class SubstringPostProcessor extends IPostProcessor {

    @XmlAttribute(name = "start")
    private int start;

    @XmlAttribute(name = "end")
    private int end;

    @Override
    public String process(String value) {
        if (value == null) {
            return null;
        }

        if (end == 0) {
            return value.substring(start);
        }

        return value.substring(start, end);
    }
}
