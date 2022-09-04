/*
 * Copyright © 2021 Anthony DePaul
 * Licensed under the MIT License https://adepaul.mit-license.org/
 */
package dev.adepaul.website.model;

import java.util.List;

public class ProjectDetails {

    private String articleLink;
    private String title;
    private String projectLink;
    private String imgSrc;
    private String imgAlt;
    private String publicationDate;
    private String completionDate;
    private List<String> technologies;
    private String technologiesStr;
    private List<String> languages;
    private String languagesStr;
    private String summary;

    public String getArticleLink() {
        return articleLink;
    }

    public void setArticleLink(String articleLink) {
        this.articleLink = articleLink;
    }

    public String getTitle() {
        return title;
    }

    public String getProjectLink() {
        return projectLink;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public String getImgAlt() {
        return imgAlt;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public String getCompletionDate() {
        return completionDate;
    }

    public List<String> getTechnologies() {
        return technologies;
    }

    public String getTechnologiesStr() {
        return technologiesStr;
    }

    public void setTechnologiesStr(String technologiesStr) {
        this.technologiesStr = technologiesStr;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public String getLanguagesStr() {
        return languagesStr;
    }

    public void setLanguagesStr(String languagesStr) {
        this.languagesStr = languagesStr;
    }

    public String getSummary() {
        return summary;
    }
}
