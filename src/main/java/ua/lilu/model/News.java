package ua.lilu.model;

/**
 * Created by Lilu on 19.12.2016.
 */
public class News {
    private String header;
    private String contentHTML;
    private String imgLink;
    private String link;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getContentHTML() {
        return contentHTML;
    }

    public void setContentHTML(String contentHTML) {
        this.contentHTML = contentHTML;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        News news = (News) o;

        return header != null ? header.equals(news.header) : news.header == null;
    }

    @Override
    public int hashCode() {
        return header != null ? header.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "News{" +
                "header='" + header + '\'' +
                ", contentHTML='" + contentHTML + '\'' +
                ", imgLink='" + imgLink + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
