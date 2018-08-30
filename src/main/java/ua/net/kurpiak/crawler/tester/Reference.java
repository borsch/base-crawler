package ua.net.kurpiak.crawler.tester;

import java.util.List;

public class Reference {

    private List<String> authors;
    private String published;
    private String title;
    private String source;
    private String volume;
    private String fpage;
    private String lpage;
    private String link;

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getFpage() {
        return fpage;
    }

    public void setFpage(String fpage) {
        this.fpage = fpage;
    }

    public String getLpage() {
        return lpage;
    }

    public void setLpage(String lpage) {
        this.lpage = lpage;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "\n\tReference{" +
                "\n\t\tauthors=" + authors +
                ", \n\t\tpublished='" + published + '\'' +
                ", \n\t\ttitle='" + title + '\'' +
                ", \n\t\tsource='" + source + '\'' +
                ", \n\t\tvolume='" + volume + '\'' +
                ", \n\t\tfpage='" + fpage + '\'' +
                ", \n\t\tlpage='" + lpage + '\'' +
                ", \n\t\tlink='" + link + '\'' +
                '}';
    }
}
