package exercise.domain;

import io.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import io.ebean.annotation.NotNull;
import io.ebeaninternal.server.util.Str;

@Entity
public class Article extends Model {

    @Id
    private long id;

    private String title;

    @Lob
    private String body;

    @ManyToOne
    @NotNull
    private Category category;

    // BEGIN
    public Article(String ptitle, String pbody, Category pcategory) {
        this.body = pbody;
        this.category = pcategory;
        this.title = ptitle;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Category getCategory() {
        return category;
    }

    // END
}
