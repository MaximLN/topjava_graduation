package ru.javawebinar.topjava.model;

import ru.javawebinar.topjava.View;
import ru.javawebinar.topjava.util.validation.NoHtml;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NamedQueries({
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=:id"),
})
@Entity
@Table(name = "restaurant", uniqueConstraints = {@UniqueConstraint(columnNames = {"description"})})
public class Restaurant extends AbstractBaseEntity {
    public static final String DELETE = "Restaurant.delete";

    @Column(name = "description", nullable = false)
    @NotBlank
    @Size(min = 2, max = 120)
    @NoHtml(groups = {View.Web.class})
    private String description;

    public Restaurant() {
    }

    public Restaurant(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                " description='" + description + '\'' +
                '}';
    }
}
