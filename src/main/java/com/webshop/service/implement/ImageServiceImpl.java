package com.webshop.service.implement;

import com.webshop.dao.generic.IGenericDAO;
import com.webshop.dao.interfaces.IImageDAO;
import com.webshop.model.classes.Image;
import com.webshop.model.classes.Product;
import com.webshop.service.interfaces.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ImageServiceImpl extends ServiceImpl<IGenericDAO<Image, Integer>, Image, Integer> implements IImageService {

    @Autowired
    private IImageDAO imageDAO;

    public ImageServiceImpl() {
    }

    @Autowired
    public ImageServiceImpl(IGenericDAO<Image, Integer> imageDAO) {
        super(imageDAO);
    }

    @Override
    public List<Image> getProductImage(Product product) {
        return product.getImagesByProductId();
    }

    /**
     * Override for deleting image from FS
     *
     * @param id
     */
    @Override
    public void delete(Integer id) {
        Image image = get(id);
        image.deleteImagesFromFS();
        super.delete(id);
    }
}
