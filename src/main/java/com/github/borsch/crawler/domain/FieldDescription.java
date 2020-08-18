package com.github.borsch.crawler.domain;

import javax.xml.bind.annotation.*;
import java.util.List;

import com.github.borsch.crawler.processors.IPostProcessor;
import com.github.borsch.crawler.processors.PrependPostProcessor;
import com.github.borsch.crawler.processors.RegexPostProcessor;
import com.github.borsch.crawler.processors.ReplacePostProcessor;
import com.github.borsch.crawler.processors.SetValuePostProcessor;
import com.github.borsch.crawler.processors.SubstringPostProcessor;

@XmlRootElement(name = "field-description")
public class FieldDescription {

    private List<SelectorAlternative> selectors;
    private String fieldName;
    private FieldDescriptionTypeEnum type;
    private List<FieldDescription> fields;
    private List<IPostProcessor> postProcessors;

    @XmlElementWrapper(name = "selectors")
    @XmlElement(name = "alternative")
    public List<SelectorAlternative> getSelectors() {
        if (selectors == null || selectors.isEmpty())
            return null;
        return selectors;
    }

    public void setSelectors(List<SelectorAlternative> selectors) {
        this.selectors = selectors;
    }

    @XmlAttribute(name = "name")
    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @XmlAttribute(name = "type")
    public FieldDescriptionTypeEnum getType() {
        return this.type;
    }

    public void setType(FieldDescriptionTypeEnum type) {
        this.type = type;
    }

    @XmlElementWrapper(name = "fields")
    @XmlElement(name = "field-description")
    public List<FieldDescription> getFields() {
        if (fields == null || fields.isEmpty())
            return null;
        return fields;
    }

    public void setFields(List<FieldDescription> fields) {
        this.fields = fields;
    }

    @XmlElementWrapper(name = "post-processors")
    @XmlElements({
            @XmlElement(name = "substring", type = SubstringPostProcessor.class),
            @XmlElement(name = "replace", type = ReplacePostProcessor.class),
            @XmlElement(name = "prepend", type = PrependPostProcessor.class),
            @XmlElement(name = "regex", type = RegexPostProcessor.class),
            @XmlElement(name = "set-value", type = SetValuePostProcessor.class)
    })
    public List<IPostProcessor> getPostProcessors() {
        return postProcessors;
    }

    public void setPostProcessors(List<IPostProcessor> postProcessors) {
        this.postProcessors = postProcessors;
    }

    @Override
    public String toString() {
        return "FieldDescription{" +
                "fieldName='" + fieldName + '\'' +
                ", type=" + type +
                '}';
    }
}
