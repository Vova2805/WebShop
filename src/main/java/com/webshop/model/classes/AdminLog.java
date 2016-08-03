package com.webshop.model.classes;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.persistence.*;
import java.sql.Date;


@Entity
public class AdminLog {
    private int adminLogId;
    private int customerId;
    private Date date;
    private String body;
    private Customer customerByCustomerId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adminLogId", nullable = false)
    public int getAdminLogId() {
        return adminLogId;
    }

    public void setAdminLogId(int adminLogId) {
        this.adminLogId = adminLogId;
    }

    @Basic
    @Column(name = "customerId", nullable = false, insertable = false, updatable = false)
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "body", nullable = false, length = -1)
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdminLog adminLog = (AdminLog) o;
        return Objects.equal(this.adminLogId, adminLog.adminLogId)
                && Objects.equal(this.customerId, adminLog.customerId)
                && Objects.equal(this.date, adminLog.date)
                && Objects.equal(this.body, adminLog.body);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.adminLogId, this.customerId, this.date, this.body);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("adminLogId", adminLogId)
                .add("customerId", customerId)
                .add("date", date)
                .add("body", body)
                .toString();
    }

    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "customerId", nullable = false)
    public Customer getCustomerByCustomerId() {
        return customerByCustomerId;
    }

    public void setCustomerByCustomerId(Customer customerByCustomerId) {
        this.customerByCustomerId = customerByCustomerId;
    }
}
