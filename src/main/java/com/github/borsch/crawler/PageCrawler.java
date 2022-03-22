package com.github.borsch.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.github.borsch.crawler.domain.FieldDescription;
import com.github.borsch.crawler.domain.FieldDescriptionType;
import com.github.borsch.crawler.jsoup.ConnectionRequest;
import com.github.borsch.crawler.processors.IPostProcessor;
import com.github.borsch.crawler.jsoup.JsoupUtil;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import com.github.borsch.crawler.domain.PageDescription;
import com.github.borsch.crawler.domain.SelectorAlternative;

public class PageCrawler<T> {

    private final PageDescription pageDescription;
    private final JsoupUtil jsoupUtil;
    private final Supplier<T> supplier;

    /**
     *
     * @param pageDescription - page description extracted from XML page description
     * @param jsoupUtil - used as parameter just not to create own property but use DI
     * @param supplier - generator of new empty instance of class T
     *
     * @deprecated use {@link #PageCrawler(PageDescription, Supplier)}
     */
    public PageCrawler(PageDescription pageDescription, JsoupUtil jsoupUtil, Supplier<T> supplier) {
        if (pageDescription == null || jsoupUtil == null || supplier == null) {
            throw new IllegalArgumentException("All fields are required");
        }

        this.jsoupUtil = jsoupUtil;
        this.pageDescription = pageDescription;
        this.supplier = supplier;
    }

    /**
     *
     * @param pageDescription - page description extracted from XML page description
     * @param supplier - generator of new empty instance of class T
     */
    public PageCrawler(PageDescription pageDescription, Supplier<T> supplier) {
        if (pageDescription == null || supplier == null) {
            throw new IllegalArgumentException("All fields are required");
        }

        this.jsoupUtil = new JsoupUtil();
        this.pageDescription = pageDescription;
        this.supplier = supplier;
    }


    /**
     * crawl page based on page description
     *
     * @param url - url which must be crawled
     *
     * @throws RuntimeException - in case if schema is bad formatted or it contains invalid selectors
     *
     * @deprecated use {@link #crawlHtml(String)}
     * @return object in case crawling was successful, null - otherwise
     */
    @Deprecated
    public T crawl(String url) {
        return crawl(
            ConnectionRequest.builder()
                .url(url)
                .ignoreHttpErrors(pageDescription.getAllowedHttpErrorCodes())
                .build()
        );
    }

    /**
     * crawl page based on page description
     *
     * @param request - object that contains information about request
     *
     * @throws RuntimeException - in case if schema is bad formatted or it contains invalid selectors
     *
     * @deprecated use {@link #crawlHtml(String)}
     * @return object in case crawling was successful, null - otherwise
     */
    @Deprecated
    public T crawl(ConnectionRequest request) {
        return crawl(jsoupUtil.parse(
            request
        ));
    }


    /**
     * crawl document based on page description
     *
     * @param document - document which must be crawled
     *
     * @throws RuntimeException - in case if schema is bad formatted or it contains invalid selectors
     *
     * @return object in case crawling was successful, null - otherwise
     */
    public T crawl(Document document) {
        if (document == null) {
            return null;
        }
        T object = supplier.get();

        for (FieldDescription fieldDescription : pageDescription.getFieldDescriptions()) {
            initializeField(document, fieldDescription, object);
        }

        return object;
    }

    /**
     * crawl document based on page description
     *
     * @param html - html document to be parsed to object
     *
     * @throws RuntimeException - in case if schema is bad formatted or it contains invalid selectors
     *
     * @return object in case crawling was successful, null - otherwise
     */
    public T crawlHtml(String html) {
        if (html == null) {
            return null;
        }

        return crawl(Jsoup.parse(html));
    }

    private void initializeField(Element document, FieldDescription fieldDescription, Object object) {
        try {
            if (fieldDescription.getFields() != null && !fieldDescription.getFields().isEmpty()) {
                if (fieldDescription.getType() == FieldDescriptionType.list) {
                    Elements rootElement = null;

                    for (SelectorAlternative selector : fieldDescription.getSelectors()) {
                        rootElement = document.select(selector.getSelector());
                        if (!rootElement.isEmpty()) {
                            break;
                        }
                    }

                    if (rootElement != null) {
                        List<Object> list = new ArrayList<>();

                        Field collectionField = object.getClass().getDeclaredField(fieldDescription.getFieldName());
                        boolean accessible = collectionField.isAccessible();

                        collectionField.setAccessible(true);
                        collectionField.set(object, list);
                        collectionField.setAccessible(accessible);

                        Class<?> collectionTypeClass = genericFieldType(collectionField);

                        for (Element container : rootElement) {
                            Object subObject = collectionTypeClass.newInstance();

                            for (FieldDescription subField : fieldDescription.getFields()) {
                                initializeField(container, subField, subObject);
                            }

                            list.add(subObject);
                        }
                    }
                } else {
                    Field field = object.getClass().getDeclaredField(fieldDescription.getFieldName());
                    Object instance = field.getDeclaringClass().newInstance();
                    boolean accessible = field.isAccessible();

                    field.setAccessible(true);
                    field.set(object, instance);
                    field.setAccessible(accessible);

                    for (FieldDescription subField : fieldDescription.getFields()) {
                        initializeField(document, subField, instance);
                    }
                }
            } else if (fieldDescription.getSelectors() != null && !fieldDescription.getSelectors().isEmpty()) {
                for (SelectorAlternative selector : fieldDescription.getSelectors()) {
                    Elements elements = document.select(selector.getSelector());

                    if (!elements.isEmpty()) {
                        Field field = object.getClass().getDeclaredField(fieldDescription.getFieldName());
                        boolean accessible = field.isAccessible();

                        field.setAccessible(true);

                        if (fieldDescription.getType() == FieldDescriptionType.list) {
                            List<Object> list = new ArrayList<>();
                            field.set(object, list);

                            for (Element element : elements) {
                                String value = getValue(selector, element);
                                value = postProcessing(value, fieldDescription.getPostProcessors());

                                list.add(value);
                            }
                        } else {
                            String value = getValue(selector, elements.first());
                            value = postProcessing(value, fieldDescription.getPostProcessors());

                            field.set(object, value);
                        }

                        field.setAccessible(accessible);

                        break;
                    }
                }
            } else {
                throw new RuntimeException("Invalid schema");
            }
        } catch (Exception e) {
            throw new RuntimeException("Problem during crawling", e);
        }
    }

    private Class<?> genericFieldType(Field field) {
        ParameterizedType type = (ParameterizedType)field.getGenericType();
        return (Class<?>)type.getActualTypeArguments()[0];
    }

    private String getValue(SelectorAlternative selector, Element source) {
        if (selector.getSourceType() == SelectorAlternative.SourceType.attribute) {
            return source.attr(selector.getSource());
        }

        return source.text();
    }

    private String postProcessing(String value, List<IPostProcessor> processors) {
        if (processors == null || processors.isEmpty()) {
            return value;
        }

        Collections.sort(processors);

        for (IPostProcessor postProcessor : processors) {
            value = postProcessor.process(value);
        }

        return value;
    }

}
