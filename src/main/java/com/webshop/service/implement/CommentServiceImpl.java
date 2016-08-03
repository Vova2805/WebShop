package com.webshop.service.implement;

import com.webshop.dao.generic.IGenericDAO;
import com.webshop.dao.interfaces.ICommentDAO;
import com.webshop.model.classes.Comment;
import com.webshop.service.interfaces.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommentServiceImpl extends ServiceImpl<IGenericDAO<Comment, Integer>, Comment, Integer> implements ICommentService {

    @Autowired
    private ICommentDAO commentDAO;

    public CommentServiceImpl() {
    }

    @Autowired
    public CommentServiceImpl(IGenericDAO<Comment, Integer> commentDAO) {
        super(commentDAO);
    }
}
