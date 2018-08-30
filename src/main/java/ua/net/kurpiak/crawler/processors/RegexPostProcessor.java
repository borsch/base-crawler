package ua.net.kurpiak.crawler.processors;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@XmlRootElement(name = "regex")
@XmlAccessorType(XmlAccessType.FIELD)
public class RegexPostProcessor extends IPostProcessor {

    @XmlAttribute(name = "expr")
    private String expr;

    @XmlAttribute(name = "group-number")
    private int groupNumber;

    @Override
    public String process(String value) {
        Pattern pattern = Pattern.compile(expr);
        Matcher matcher = pattern.matcher(value);

        if (matcher.find() && matcher.groupCount() <= groupNumber) {
            return matcher.group(groupNumber);
        }

        return null;
    }
}
