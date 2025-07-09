/*
 * Copyright © 2021 Anthony DePaul
 */
package dev.adepaul.website.model;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class ProjectDetails {

    private boolean isPublished;
    private String articleLink;
    private String title;
    private String repositoryLink;
    private String imgSrc;
    private String imgAlt;
    private LocalDate publicationDate;
    private boolean publicationDateHideDay;
    private LocalDate completionDate;
    private boolean completionDateHideDay;
    private List<String> technologies;
    private List<String> languages;
    private String summary;

    public boolean isPublished() {
        return isPublished;
    }

    public String getArticleLink() {
        return articleLink;
    }

    public String getTitle() {
        return title;
    }

    public String getRepositoryLink() {
        return repositoryLink;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public String getImgAlt() {
        return imgAlt;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public boolean getPublicationDateHideDay() {
        return publicationDateHideDay;
    }

    public String getPublicationDateStr() {
        if (publicationDateHideDay) {
            return getAbbreviatedMonth(publicationDate.getMonth()) + " " + publicationDate.getYear();
        } else {
            return getAbbreviatedMonth(publicationDate.getMonth()) + " " + publicationDate.getDayOfMonth() + ", " + publicationDate.getYear();
        }
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public boolean getCompletionDateHideDay() {
        return completionDateHideDay;
    }

    public String getCompletionDateStr() {
        if (completionDateHideDay) {
            return getAbbreviatedMonth(completionDate.getMonth()) + " " + completionDate.getYear();
        } else {
            return getAbbreviatedMonth(completionDate.getMonth()) + " " + completionDate.getDayOfMonth() + ", " + completionDate.getYear();
        }
    }

    private String getAbbreviatedMonth(Month month) {
        switch (month) {
            case JANUARY -> { return "Jan."; }
            case FEBRUARY -> { return "Feb."; }
            case MARCH -> { return "Mar."; }
            case APRIL -> { return "Apr."; }
            case MAY -> { return "May"; }
            case JUNE -> { return "Jun."; }
            case JULY -> { return "Jul."; }
            case AUGUST -> { return "Aug."; }
            case SEPTEMBER -> { return "Sep."; }
            case OCTOBER -> { return "Oct."; }
            case NOVEMBER -> { return "Nov."; }
            case DECEMBER -> { return "Dec."; }
            default -> throw new IllegalStateException("Should never happen");
        }
    }

    public List<String> getTechnologies() {
        return technologies;
    }

    public String getTechnologiesStr() {
        return String.join(",", technologies);
    }

    public List<String> getLanguages() {
        return languages;
    }

    public String getLanguagesStr() {
        return String.join(",", languages);
    }

    public String getSummary() {
        return summary;
    }
}
