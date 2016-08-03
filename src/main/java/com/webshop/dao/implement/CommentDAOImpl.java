package com.webshop.dao.implement;

import com.webshop.dao.generic.GenericDAOImpl;
import com.webshop.dao.interfaces.ICommentDAO;
import com.webshop.model.classes.Comment;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDAOImpl extends GenericDAOImpl<Comment, Integer> implements ICommentDAO {

}
