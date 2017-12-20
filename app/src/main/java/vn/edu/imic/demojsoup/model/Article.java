package vn.edu.imic.demojsoup.model;

import java.io.Serializable;

/**
 * Created by MyPC on 18/12/2017.
 */

public class Article implements Serializable{
    private String title;
    private String url;
    private String thumbnail;
    private String description;

    public Article() {
    }

    public Article(String title, String url, String thumbnail, String description) {
        this.title = title;
        this.url = url;
        this.thumbnail = thumbnail;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
