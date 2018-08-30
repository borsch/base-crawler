package ua.net.kurpiak.crawler.tester;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olehkurpiak on 23.01.2018.
 */
public class Author {

    private String name;
    private List<String> affiliations = new ArrayList<>();
    private List<String> correspond = new ArrayList<>();
    private List<String> notes = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAffiliations() {
        return affiliations;
    }

    public void setAffiliations(List<String> affiliations) {
        this.affiliations = affiliations;
    }

    public void addAffiliation(String affiliation) {
        this.affiliations.add(affiliation);
    }

    public List<String> getCorrespond() {
        return correspond;
    }

    public void setCorrespond(List<String> correspond) {
        this.correspond = correspond;
    }

    public void addCorrespond(String correspond) {
        this.correspond.add(correspond);
    }

    public List<String> getNotes() {
        return notes;
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
    }

    public void addNotes(String note) {
        this.notes.add(note);
    }

    @Override
    public String toString() {
        return "\n\tAuthor{" +
                "\n\t\tname='" + name + '\'' +
                ", \n\t\taffiliations=" + affiliations +
                ", \n\t\tcorrespond=" + correspond +
                ", \n\t\tnotes=" + notes +
                '}';
    }
}
