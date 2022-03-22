package com.github.borsch.crawler.xml;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.github.borsch.crawler.domain.PageDescription;

public abstract class IXmlProcessor {

    private final Unmarshaller unmarshaller;

    protected IXmlProcessor() {
        try {
            JAXBContext context = JAXBContext.newInstance(PageDescription.class);

            this.unmarshaller = context.createUnmarshaller();
        } catch (JAXBException e) {
            throw new RuntimeException("Could not initialize JAXB", e);
        }
    }

    public PageDescription parse(String location) {
        try {
            try (InputStream inputStream = getRulesSource(location)) {
                return (PageDescription) unmarshaller.unmarshal(inputStream);
            } catch (IOException e) {
                throw new RuntimeException("Can't read InputStream for resource: " + location, e);
            }
        } catch (JAXBException e) {
            throw new RuntimeException("Crawler description is bad formatted", e);
        }
    }

    /**
     * Get {@link InputStream} of XML rules by location
     *
     * @param location - location of XML rules
     * @return input stream
     */
    protected abstract InputStream getRulesSource(String location);

}
