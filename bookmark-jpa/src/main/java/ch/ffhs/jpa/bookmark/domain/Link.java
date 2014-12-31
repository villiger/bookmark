package ch.ffhs.jpa.bookmark.domain;

import javax.persistence.*;

@Entity(name = "link")
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"url"})})
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 1024, nullable = false)
    private String title;

    @Column(length = 1024, nullable = false, unique = true)
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getClickableUrl() {
        if (getUrl().startsWith("https://") || getUrl().startsWith("http://")) {
            return getUrl();
        } else {
            return "http://" + getUrl();
        }
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
