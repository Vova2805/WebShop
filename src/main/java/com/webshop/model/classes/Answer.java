package com.webshop.model.classes;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.sql.Date;


@Entity
public class Answer {
    @Expose
    private int answerId;
    @Expose
    private int commentId;
    @Expose
    private String answerBody;
    @Expose
    private int customerId;
    @Expose
    private Date answerDate;
    private Comment commentByCommentId;
    private Customer customerByCustomerId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answerId", nullable = false)
    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
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
    @Column(name = "answerBody", nullable = false, length = -1)
    public String getAnswerBody() {
        return answerBody;
    }

    public void setAnswerBody(String answerBody) {
        this.answerBody = answerBody;
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
    @Column(name = "answerDate", nullable = false)
    public Date getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(Date answerDate) {
        this.answerDate = answerDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Answer answer = (Answer) o;

        if (answerId != answer.answerId) return false;
        if (commentId != answer.commentId) return false;
        if (customerId != answer.customerId) return false;
        if (answerBody != null ? !answerBody.equals(answer.answerBody) : answer.answerBody != null) return false;
        if (answerDate != null ? !answerDate.equals(answer.answerDate) : answer.answerDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = answerId;
        result = 31 * result + commentId;
        result = 31 * result + (answerBody != null ? answerBody.hashCode() : 0);
        result = 31 * result + customerId;
        result = 31 * result + (answerDate != null ? answerDate.hashCode() : 0);
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
