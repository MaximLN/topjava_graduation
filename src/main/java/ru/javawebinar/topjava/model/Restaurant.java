package ru.javawebinar.topjava.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.javawebinar.topjava.View;
import ru.javawebinar.topjava.util.validation.NoHtml;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
//    @OrderBy("dateTime DESC")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    private List<MenuItem> menuItems;

    public Restaurant() {
    }

    public Restaurant(Integer id, String description) {
        super(id);
        this.description = description;
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

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                " description='" + description + '\'' +
                '}';
    }
}
