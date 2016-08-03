package com.webshop.dao.implement;

import com.webshop.dao.generic.GenericDAOImpl;
import com.webshop.dao.interfaces.IDescriptionDAO;
import com.webshop.model.classes.DescriptionPart;
import org.springframework.stereotype.Repository;

@Repository
public class DescriptionDAOImpl extends GenericDAOImpl<DescriptionPart, Integer> implements IDescriptionDAO {


}
