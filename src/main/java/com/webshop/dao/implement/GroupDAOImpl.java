package com.webshop.dao.implement;

import com.webshop.dao.generic.GenericDAOImpl;
import com.webshop.dao.interfaces.IGroupDAO;
import com.webshop.model.classes.SubcategoryGroup;
import org.springframework.stereotype.Repository;

@Repository
public class GroupDAOImpl extends GenericDAOImpl<SubcategoryGroup, Integer> implements IGroupDAO {

}
