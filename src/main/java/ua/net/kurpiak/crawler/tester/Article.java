package ua.net.kurpiak.crawler.tester;


import java.util.ArrayList;
import java.util.List;

public class Article {

    private String link;
    private String title;
    private List<Author> authors;
    private List<Reference> references;
    private List<String> keywords;
    private List<String> subjectWords;
    private List<String> classificationCodes;
    private List<String> classificationMethods;
    private String abstractText;
    private String doi;
    private String startEndPages;
    private List<String> issns;
    private String published;
    private String fullTextPdf;
    private String copyright;
    private String publisherName;
    private String publisherLocation;
    private String journalTitle;
    private String publisherId;



    @Override
    public String toString() {
        return "Article{" +
                "\n\tlink='" + link + '\'' +
                ", \n\ttitle='" + title + '\'' +
                ", \n\tauthors=" + authors +
                ", \n\treferences=" + references +
                ", \n\tkeywords=" + keywords +
                ", \n\tsubjectWords=" + subjectWords +
                ", \n\tclassificationCodes=" + classificationCodes +
                ", \n\tclassificationMethods=" + classificationMethods +
                ", \n\tabstractText='" + abstractText + '\'' +
                ", \n\tdoi='" + doi + '\'' +
                ", \n\tstartEndPages='" + startEndPages + '\'' +
                ", \n\tissns='" + issns + '\'' +
                ", \n\tpublished='" + published + '\'' +
                ", \n\tfullTextPdf='" + fullTextPdf + '\'' +
                '}';
    }
}
