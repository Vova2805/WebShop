package com.webshop.service.implement;

import com.webshop.dao.generic.IGenericDAO;
import com.webshop.dao.interfaces.IAdvertisementDAO;
import com.webshop.model.classes.Advertisement;
import com.webshop.service.interfaces.IAdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdvertisementServiceImpl extends ServiceImpl<IGenericDAO<Advertisement, Integer>, Advertisement, Integer> implements IAdvertisementService {

    @Autowired
    private IAdvertisementDAO advertisementDAO;

    public AdvertisementServiceImpl() {
    }

    @Autowired
    public AdvertisementServiceImpl(IGenericDAO<Advertisement, Integer> advertisementDAO) {
        super(advertisementDAO);
    }

    @Override
    public void delete(Integer id) {
        Advertisement advertisement = advertisementDAO.get(id);
        advertisement.deleteImagesFromFS();
        super.delete(id);
    }
}
