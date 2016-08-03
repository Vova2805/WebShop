package com.webshop.model.classes;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class PropertyClass {
    @Expose
    private int propertyClassId;
    @Expose
    private String propertyClassTitle;
    @Expose
    private String propertyClassDescr;
    private List<Property> propertiesByPropertyClassId;

    public PropertyClass() {
    }

    public PropertyClass(String propertyClassTitle, String propertyClassDescr) {
        this();
        this.propertyClassTitle = propertyClassTitle;
        this.propertyClassDescr = propertyClassDescr;
        propertiesByPropertyClassId = new ArrayList<Property>();
    }

    public PropertyClass(String propertyClassTitle) {
        this.propertyClassTitle = propertyClassTitle;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "propertyClassId", nullable = false)
    public int getPropertyClassId() {
        return propertyClassId;
    }

    public void setPropertyClassId(int propertyClassId) {
        this.propertyClassId = propertyClassId;
    }

    @Basic
    @Column(name = "propertyClassTitle", nullable = false)
    public String getPropertyClassTitle() {
        return propertyClassTitle;
    }

    public void setPropertyClassTitle(String propertyClassTitle) {
        this.propertyClassTitle = propertyClassTitle;
    }

    @Basic
    @Column(name = "propertyClassDescr", nullable = true)
    public String getPropertyClassDescr() {
        return propertyClassDescr;
    }

    public void setPropertyClassDescr(String propertyClassDescr) {
        this.propertyClassDescr = propertyClassDescr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PropertyClass that = (PropertyClass) o;

        if (propertyClassId != that.propertyClassId) return false;
        if (propertyClassTitle != null ? !propertyClassTitle.equals(that.propertyClassTitle) : that.propertyClassTitle != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = propertyClassId;
        result = 31 * result + (propertyClassTitle != null ? propertyClassTitle.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "propertyClassByPropertyClassId", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
            org.hibernate.annotations.CascadeType.DELETE,
            org.hibernate.annotations.CascadeType.MERGE,
            org.hibernate.annotations.CascadeType.PERSIST,
            org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    public List<Property> getPropertiesByPropertyClassId() {
        return propertiesByPropertyClassId;
    }

    public void setPropertiesByPropertyClassId(List<Property> propertiesByPropertyClassId) {
        this.propertiesByPropertyClassId = propertiesByPropertyClassId;
    }

    public void initFlags() {
        this.propertiesByPropertyClassId.forEach(property -> {
            property.setActive(false);
        });
    }
}
