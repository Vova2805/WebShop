package com.webshop.model.classes;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
public class Comment {
    @Expose
    private int commentId;
    @Expose
    private int productId;
    @Expose
    private int customerId;
    @Expose
    private Date commentDate;
    @Expose
    private int starsValue;
    @Expose
    private String commentBody;
    @Expose
    private String advantages;
    @Expose
    private String disadvantages;
    @Expose
    private Integer useful;
    @Expose
    private Integer useless;

    private List<Answer> answersByCommentId;
    private Product productByProductId;
    private Customer customerByCustomerId;
    private List<Report> reportsByCommentId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentId", nullable = false)
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
    @Column(name = "productId", nullable = false, insertable = false, updatable = false)
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "commentDate", nullable = false)
    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    @Basic
    @Column(name = "starsValue", nullable = false)
    public int getStarsValue() {
        return starsValue;
    }

    public void setStarsValue(int starsValue) {
        this.starsValue = starsValue;
    }

    @Basic
    @Column(name = "commentBody", nullable = false, length = -1)
    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    @Basic
    @Column(name = "advantages", nullable = true, length = -1)
    public String getAdvantages() {
        return advantages;
    }

    public void setAdvantages(String advantages) {
        this.advantages = advantages;
    }

    @Basic
    @Column(name = "disadvantages", nullable = true, length = -1)
    public String getDisadvantages() {
        return disadvantages;
    }

    public void setDisadvantages(String disadvantages) {
        this.disadvantages = disadvantages;
    }

    @Basic
    @Column(name = "useful", nullable = true)
    public Integer getUseful() {
        return useful;
    }

    public void setUseful(Integer useful) {
        this.useful = useful;
    }

    @Basic
    @Column(name = "useless", nullable = true)
    public Integer getUseless() {
        return useless;
    }

    public void setUseless(Integer useless) {
        this.useless = useless;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (commentId != comment.commentId) return false;
        if (customerId != comment.customerId) return false;
        if (productId != comment.productId) return false;
        if (starsValue != comment.starsValue) return false;
        if (commentDate != null ? !commentDate.equals(comment.commentDate) : comment.commentDate != null) return false;
        if (commentBody != null ? !commentBody.equals(comment.commentBody) : comment.commentBody != null) return false;
        if (advantages != null ? !advantages.equals(comment.advantages) : comment.advantages != null) return false;
        if (disadvantages != null ? !disadvantages.equals(comment.disadvantages) : comment.disadvantages != null)
            return false;
        if (useful != null ? !useful.equals(comment.useful) : comment.useful != null) return false;
        if (useless != null ? !useless.equals(comment.useless) : comment.useless != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = commentId;
        result = 31 * result + customerId;
        result = 31 * result + productId;
        result = 31 * result + (commentDate != null ? commentDate.hashCode() : 0);
        result = 31 * result + starsValue;
        result = 31 * result + (commentBody != null ? commentBody.hashCode() : 0);
        result = 31 * result + (advantages != null ? advantages.hashCode() : 0);
        result = 31 * result + (disadvantages != null ? disadvantages.hashCode() : 0);
        result = 31 * result + (useful != null ? useful.hashCode() : 0);
        result = 31 * result + (useless != null ? useless.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "commentByCommentId", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
            org.hibernate.annotations.CascadeType.DELETE,
            org.hibernate.annotations.CascadeType.MERGE,
            org.hibernate.annotations.CascadeType.PERSIST,
            org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    public List<Answer> getAnswersByCommentId() {
        return answersByCommentId;
    }

    public void setAnswersByCommentId(List<Answer> answersByCommentId) {
        this.answersByCommentId = answersByCommentId;
    }

    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "productId", nullable = false)
    public Product getProductByProductId() {
        return productByProductId;
    }

    public void setProductByProductId(Product productByProductId) {
        this.productByProductId = productByProductId;
    }

    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "customerId", nullable = false)
    public Customer getCustomerByCustomerId() {
        return customerByCustomerId;
    }

    public void setCustomerByCustomerId(Customer customerByCustomerId) {
        this.customerByCustomerId = customerByCustomerId;
    }

    @OneToMany(mappedBy = "commentByCommentId", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
            org.hibernate.annotations.CascadeType.DELETE,
            org.hibernate.annotations.CascadeType.MERGE,
            org.hibernate.annotations.CascadeType.PERSIST,
            org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    public List<Report> getReportsByCommentId() {
        return reportsByCommentId;
    }

    public void setReportsByCommentId(List<Report> reportsByCommentId) {
        this.reportsByCommentId = reportsByCommentId;
    }
}
