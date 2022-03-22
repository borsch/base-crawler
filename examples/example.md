### Example

Assume there is a simple web page which you want to crawl and extract all required data

```html
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div class="name">Ferrari 458 Italia</div>
    <div class="numberOfHP">500</div>
    <a href="https://uk.wikipedia.org/wiki/Ferrari_458_Italia#/media/File:Ferrari_458_Italia_--_05-18-2011.jpg">Link</a>
</body>
</html>
```

And result of this page should be extracted to following object

```java
public class CarTestEntity {
    private String name;
    private String numberOfHP;
    private String linkToPicture;

    // .. getters & setters
}
```

To do so you would need to create a simple xml files that contains rules of how that HTML should be parsed

```xml
<?xml version="1.0" encoding="UTF-8"?>
<page xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:noNamespaceSchemaLocation="https://docs.crawler.kurpiak.net.ua/ns/crawler-1.1.xsd">

    <fields>
        <field-description name="name">       <!-- CarTestEntity.name field  -->
            <selectors>
                <!-- CSS selector that should be used to extract value. In this case by CSS class -->
                <alternative selector=".name"/>
            </selectors>
        </field-description>
        <field-description name="numberOfHP">     <!-- CarTestEntity.numberOfHP field  -->
            <selectors>
                <!-- Again extract value by CSS class -->
                <alternative selector=".numberOfHP"/>
            </selectors>
        </field-description>
        <field-description name="linkToPicture">    <!-- CarTestEntity.linkToPicture field  -->
            <selectors>
                <!-- Extract result from element a on page and get value from its attribute href -->
                <alternative selector="a" source-type="attribute" source="href"/>
            </selectors>
        </field-description>
    </fields>
</page>
```

Now to get actual `CarTestEntity` object from above HTML using these rules you have to run following

```java
import com.github.borsch.crawler.domain.PageDescription;
import com.github.borsch.crawler.xml.LocalXmlProcessor;

IXmlProcessor xmlProcessor = new LocalXmlProcessor();
PageDescription description = xmlProcessor.parse("/xml/car.xml");   // location on location machine to XML rules
PageCrawler<CarTestEntity> carCrawler = new PageCrawler<>(description, CarTestEntity::new);
String html = ; // actual HTML to be parsed

CarTestEntity carTestEntity = carCrawler.crawlHtml(html);  // parsing result

```