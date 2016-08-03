package com.webshop.model.classes;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.sql.Date;


@Entity
public class Report {
    @Expose
    private int reportId;
    @Expose
    private int commentId;
    @Expose
    private int customerId;
    @Expose
    private String reportBody;
    @Expose
    private Date reportDate;
    private Comment commentByCommentId;
    private Customer customerByCustomerId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reportId", nullable = false)
    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    @Basic
    @Column(name = "commentId", nullable = false, insertable = false, updatable = false)
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
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
    @Column(name = "reportBody", nullable = false, length = -1)
    public String getReportBody() {
        return reportBody;
    }

    public void setReportBody(String reportBody) {
        this.reportBody = reportBody;
    }

    @Basic
    @Column(name = "reportDate", nullable = false)
    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Report report = (Report) o;

        if (reportId != report.reportId) return false;
        if (commentId != report.commentId) return false;
        if (customerId != report.customerId) return false;
        if (reportBody != null ? !reportBody.equals(report.reportBody) : report.reportBody != null) return false;
        if (reportDate != null ? !reportDate.equals(report.reportDate) : report.reportDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = reportId;
        result = 31 * result + commentId;
        result = 31 * result + customerId;
        result = 31 * result + (reportBody != null ? reportBody.hashCode() : 0);
        result = 31 * result + (reportDate != null ? reportDate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "commentId", referencedColumnName = "commentId", nullable = false)
    public Comment getCommentByCommentId() {
        return commentByCommentId;
    }

    public void setCommentByCommentId(Comment commentByCommentId) {
        this.commentByCommentId = commentByCommentId;
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
