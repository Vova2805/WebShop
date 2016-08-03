package com.webshop.model.classes;

import com.google.gson.annotations.Expose;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SubscribedUser {
    @Expose
    private int subscribedUserId;
    @Expose
    private String email;

    public SubscribedUser() {
    }

    public SubscribedUser(String email) {
        this.email = email;
    }

    @Id
    @Column(name = "subscribedUserId", nullable = false)
    public int getSubscribedUserId() {
        return subscribedUserId;
    }

    public void setSubscribedUserId(int subscribedUserId) {
        this.subscribedUserId = subscribedUserId;
    }

    @Basic
    @Column(name = "email", nullable = false, length = -1)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubscribedUser that = (SubscribedUser) o;

        if (subscribedUserId != that.subscribedUserId) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = subscribedUserId;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
