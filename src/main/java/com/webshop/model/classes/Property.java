package com.webshop.model.classes;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;


@Entity
public class Property {
    @Expose
    private int propertyId;
    @Expose
    private int propertyClassId;
    @Expose
    private String propertyValue;
    private PropertyClass propertyClassByPropertyClassId;
    private List<Product> goodsByPropertyId;
    private boolean checked = false;
    private boolean active = false;

    public Property() {
    }

    public Property(int propertyClassId, String propertyValue, PropertyClass propertyClassByPropertyClassId) {
        this();
        this.propertyClassId = propertyClassId;
        this.propertyValue = propertyValue;
        this.propertyClassByPropertyClassId = propertyClassByPropertyClassId;
    }

    @Id
    @GenericGenerator(name = "native", strategy = "increment")
    @GeneratedValue(generator = "native")
    @Column(name = "propertyId", unique = true, nullable = false)
    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    @Basic
    @Column(name = "propertyClassId", nullable = false, insertable = false, updatable = false)
    public int getPropertyClassId() {
        return propertyClassId;
    }

    public void setPropertyClassId(int propertyClassId) {
        this.propertyClassId = propertyClassId;
    }

    @Basic
    @Column(name = "propertyValue", nullable = false, length = -1)
    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Property property = (Property) o;

        if (propertyId != property.propertyId) return false;
        if (propertyClassId != property.propertyClassId) return false;
        return propertyValue != null ? propertyValue.equals(property.propertyValue) : property.propertyValue == null;

    }

    @Override
    public int hashCode() {
        int result = propertyId;
        result = 31 * result + propertyClassId;
        result = 31 * result + (propertyValue != null ? propertyValue.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "propertyClassId", referencedColumnName = "propertyClassId", nullable = false)
    public PropertyClass getPropertyClassByPropertyClassId() {
        return propertyClassByPropertyClassId;
    }

    public void setPropertyClassByPropertyClassId(PropertyClass propertyClassByPropertyClassId) {
        this.propertyClassByPropertyClassId = propertyClassByPropertyClassId;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
            org.hibernate.annotations.CascadeType.MERGE,
            org.hibernate.annotations.CascadeType.PERSIST})
    @JoinTable(name = "propertyProduct",
            joinColumns = {
                    @JoinColumn(name = "property_propertyId", nullable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "product_productId", nullable = false)})

    public List<Product> getProductsByPropertyId() {
        return goodsByPropertyId;
    }

    public void setProductsByPropertyId(List<Product> goodsByPropertyId) {
        this.goodsByPropertyId = goodsByPropertyId;
    }

    @Transient
    public boolean getChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Transient
    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
