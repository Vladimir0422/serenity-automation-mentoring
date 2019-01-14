package serenity.bdd.models;

import serenity.bdd.statuses.PetStatus;

import javax.swing.text.html.HTML;
import java.util.List;
import java.util.Objects;

/**
 * Created by : Volodymyr_Silitskyi
 * Created at : 11/25/2018
 */


public class Pet {
    private long id;
    private Category category;
    private String name;
    private List<String> photoUrls;
    private List<HTML.Tag> tags;
    private PetStatus status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public List<HTML.Tag> getTags() {
        return tags;
    }

    public void setTags(List<HTML.Tag> tags) {
        this.tags = tags;
    }

    public PetStatus getStatus() {
        return status;
    }

    public void setStatus(PetStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pet)) return false;
        Pet that = (Pet) o;
        return getId() == that.getId() &&
                getCategory() == that.getCategory() &&
                getName() == that.getName() &&
                getPhotoUrls() == that.getPhotoUrls() &&
                getTags() == that.getTags() &&
                Objects.equals(getStatus(), that.getStatus());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getCategory(), getName(), getPhotoUrls(), getTags(), getStatus());
    }
}
