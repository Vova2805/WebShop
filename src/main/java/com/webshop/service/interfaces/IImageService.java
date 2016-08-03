package com.webshop.service.interfaces;

import com.webshop.dao.generic.IGenericDAO;
import com.webshop.model.classes.Image;
import com.webshop.model.classes.Product;

import java.util.List;


public interface IImageService extends IService<IGenericDAO<Image, Integer>, Image, Integer> {

    List<Image> getProductImage(Product product);
}
