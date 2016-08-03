package com.mapping;

import com.webshop.model.classes.Product;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class CopyTest {

    @Test
    public void testCopyMapToPOJO() throws Exception {
        final Map<String, String> map = new HashMap<String, String>(4);
        map.put("title", "Vova");
        map.put("price", "34.5");
        map.put("cource", "3ab");
        Product product = new Product();
        BeanUtils.populate(product, map);
        System.out.println(product.getTitle());
    }
}
