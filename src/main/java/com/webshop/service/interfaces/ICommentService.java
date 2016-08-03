package com.webshop.service.interfaces;

import com.webshop.dao.generic.IGenericDAO;
import com.webshop.model.classes.Comment;


public interface ICommentService extends IService<IGenericDAO<Comment, Integer>, Comment, Integer> {

}
