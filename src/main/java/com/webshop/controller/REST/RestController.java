package com.webshop.controller.REST;

import com.webshop.anotation.ActiveUser;
import com.webshop.controller.base.CommonController;
import com.webshop.dao.interfaces.ICartDAO;
import com.webshop.dao.interfaces.IProductOrderDAO;
import com.webshop.dao.interfaces.ISubscribedUserDAO;
import com.webshop.exeption.UserNotFoundException;
import com.webshop.model.classes.*;
import com.webshop.model.classes.extra.ImageDecorator;
import com.webshop.model.classes.extra.UploadedImage;
import com.webshop.service.interfaces.*;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Return only json answer
 */
@Controller
@RequestMapping(value = "api")
public class RestController extends CommonController {
    private final int MAX_IMAGES_FOR_UPLOADING = 10;

    //region Services
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IGroupService groupService;
    @Autowired
    private ISubcategoryService subcategoryService;
    @Autowired
    private IProductService productService;
    @Autowired
    private IPropertyClassService propertyClassService;
    @Autowired
    private IImageService imageService;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IAdvertisementService advertisementService;
    @Autowired
    private IAddressService addressService;
    @Autowired
    private ICartDAO cartDAO;
    @Autowired
    private IProductOrderDAO productOrderDAO;
    @Autowired
    private ISubscribedUserDAO subscribedUserDAO;
    @Autowired
    private IMailService mailService;
    //endregion

    //region Categories

    /**
     * Simple operation with Categories using JSON notation
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "categories", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getCategories(HttpServletRequest request) {
        List<Category> categories = (List<Category>) (Object) categoryService.getAll();
        return toGSON(categories);
    }

    @RequestMapping(value = "categories/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getCategoryById(@PathVariable("id") String id, HttpServletRequest request) {
        Integer categoryId = getIdFromString(id);
        if (categoryId != null) {
            try {
                Category category = categoryService.get(categoryId);
                return toGSON(category);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        return null;
    }

    @RequestMapping(value = "categories/{id}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String changeCategory(@RequestBody LinkedHashMap parameters, @PathVariable("id") String id, HttpServletRequest request) throws Exception {

        Integer objectId = getIdFromString(id);
        Category original = null;
        if (null != objectId) {
            original = categoryService.get(objectId);
            BeanUtils.populate(original, parameters);
            categoryService.update(original);
        }
        return toGSON(original);
    }

    @RequestMapping(value = "categories/{id}/image", method = RequestMethod.POST)
    @ResponseBody
    public String categoryImageUpload(MultipartHttpServletRequest request,
                                      HttpServletRequest httpRequest,
                                      @PathVariable("id") String pid, ModelMap model) throws Exception {
        Iterator<String> iterator = request.getFileNames();
        MultipartFile multiFile = request.getFile(iterator.next());

        Integer categoryId = getIdFromString(pid);
        Category category = null;
        if (categoryId != null) {
            category = categoryService.get(categoryId);
            category.saveImagesToFS(multiFile.getBytes());
            category.initializeImages();
            categoryService.update(category);
        }
        return toGSON(category);
    }
    //endregion

    //region Subcategories
    @RequestMapping(value = "subcategories", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getSubcategories(HttpServletRequest request) {
        List<Subcategory> subcategories = (List<Subcategory>) (Object) subcategoryService.getAll();
        return toGSON(subcategories);

    }

    @RequestMapping(value = "subcategories/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getSubcategoryById(@PathVariable("id") String id, HttpServletRequest request) {
        Integer subcategoryId = getIdFromString(id);
        if (subcategoryId != null) {
            try {
                return toGSON(subcategoryService.get(subcategoryId));
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        return null;
    }

    @RequestMapping(value = "subcategories", params = {"categoryId"}, method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getSubcategoriesByCategoryTitle(@RequestParam(value = "categoryId", required = true) String categoryId, HttpServletRequest request) {

        Integer catId = getIdFromString(categoryId);
        if (null != catId) {
            Category categoryByName = categoryService.get(catId);
            if (categoryByName != null) {
                List<Subcategory> subcategories = categoryByName.getSubcategoriesByCategoryId();
                return toGSON(subcategories);
            }
        }
        return null;
    }

    @RequestMapping(value = "subcategories/{id}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String changeSubcategory(@RequestBody LinkedHashMap parameters, @PathVariable("id") String id, HttpServletRequest request) throws Exception {

        Integer objectId = getIdFromString(id);
        Subcategory original = null;
        if (null != objectId) {
            original = subcategoryService.get(objectId);
            BeanUtils.populate(original, parameters);
            if (parameters.containsKey("categoryId")) {
                String categoryIds = parameters.get("categoryId").toString();
                Integer categoryId = getIdFromString(categoryIds);
                if (categoryId != null) {
                    Category category = categoryService.get(categoryId);
                    original.setCategoryByCategoryId(category);
                    original.setCategoryId(categoryId);
                }
            }
            subcategoryService.update(original);
        }
        return toGSON(original);
    }

    @RequestMapping(value = "subcategories/{id}/image", method = RequestMethod.POST)
    @ResponseBody
    public String subcategoryImageUpload(MultipartHttpServletRequest request,
                                         HttpServletRequest httpRequest,
                                         @PathVariable("id") String pid, ModelMap model) throws Exception {
        Iterator<String> iterator = request.getFileNames();
        MultipartFile multiFile = request.getFile(iterator.next());

        Integer subcategoryId = getIdFromString(pid);
        Subcategory subcategory = null;
        if (subcategoryId != null) {
            subcategory = subcategoryService.get(subcategoryId);
            subcategory.saveImagesToFS(multiFile.getBytes());
            subcategory.initializeImages();
            subcategoryService.update(subcategory);
        }
        return toGSON(subcategory);
    }

    @RequestMapping(value = "categories/{id}/subcategories", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getSubcategoriesByCategoryId(@PathVariable("id") String id, HttpServletRequest request) {

        Integer objectId = getIdFromString(id);
        List<Subcategory> subcategories = new ArrayList<>();
        if (null != objectId) {
            Category category = categoryService.get(objectId);
            subcategories = category.getSubcategoriesByCategoryId();
        }
        return toGSON(subcategories);
    }

    //endregion

    //region Groups

    @RequestMapping(value = "groups", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getAllGroups(HttpServletRequest request) {
        List<SubcategoryGroup> subcategoryGroups = (List<SubcategoryGroup>) (Object) groupService.getAll();
        return toGSON(subcategoryGroups);
    }

    @RequestMapping(value = "groups/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getGroupById(@PathVariable("id") String id, HttpServletRequest request) {
        Integer groupId = getIdFromString(id);
        if (groupId != null) {
            try {
                SubcategoryGroup subcategoryGroup = groupService.get(groupId);
                return toGSON(subcategoryGroup);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        return null;
    }

    @RequestMapping(value = "groups", params = {"subcategoryId"}, method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getGroupBySubcategoryId(@RequestParam(value = "subcategoryId", required = true) String id, HttpServletRequest request) {
        Integer subcategoryId = getIdFromString(id);
        if (subcategoryId != null) {
            try {
                List<SubcategoryGroup> subcategoryGroups = subcategoryService.get(subcategoryId).getSubcategoryGroupsBySubcategoryId();
                return toGSON(subcategoryGroups);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        return null;
    }

    @RequestMapping(value = "groups/{id}", method = RequestMethod.POST,

            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String changeGroup(@RequestBody LinkedHashMap parameters, @PathVariable("id") String id, HttpServletRequest request) throws Exception {

        Integer objectId = getIdFromString(id);
        SubcategoryGroup original = null;
        if (null != objectId) {
            original = groupService.get(objectId);
            BeanUtils.populate(original, parameters);
            if (parameters.containsKey("subcategoryId")) {
                String subcategoryIds = parameters.get("subcategoryId").toString();
                Integer subcategoryId = getIdFromString(subcategoryIds);
                if (subcategoryId != null) {
                    Subcategory subcategory = subcategoryService.get(subcategoryId);
                    original.setSubcategoryBySubcategoryId(subcategory);
                    original.setSubcategoryId(subcategoryId);
                }
            }
            groupService.update(original);
        }
        return toGSON(original);
    }

    @RequestMapping(value = "groups/{id}/image", method = RequestMethod.POST)
    @ResponseBody
    public String groupImageUpload(MultipartHttpServletRequest request,
                                   HttpServletRequest httpRequest,
                                   @PathVariable("id") String pid, ModelMap model) throws Exception {
        Iterator<String> iterator = request.getFileNames();
        MultipartFile multiFile = request.getFile(iterator.next());

        Integer groupId = getIdFromString(pid);
        SubcategoryGroup group = null;
        if (groupId != null) {
            group = groupService.get(groupId);
            group.saveImagesToFS(multiFile.getBytes());
            group.initializeImages();
            groupService.update(group);
        }
        return toGSON(group);
    }

    @RequestMapping(value = "subcategories/{id}/groups", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getGroupsBySubcategoryId(@PathVariable("id") String id, HttpServletRequest request) {

        Integer objectId = getIdFromString(id);
        List<SubcategoryGroup> subcategoryGroups = null;
        if (null != objectId) {
            Subcategory subcategory = subcategoryService.get(objectId);
            subcategoryGroups = subcategory.getSubcategoryGroupsBySubcategoryId();
        }
        return toGSON(subcategoryGroups);
    }
    //endregion

    //region Goods

    @RequestMapping(value = "goods", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getProducts(ModelMap model,
                              @RequestParam(value = "q", required = false) String q,
                              @RequestParam(value = "properties", required = false) List<String> propertiesIds,
                              @RequestParam(value = "groups", required = false) List<String> groupsIds,
                              @RequestParam(value = "priceFrom", required = false) Double priceFrom,
                              @RequestParam(value = "priceTo", required = false) Double priceTo,
                              @RequestParam(value = "sortBy", required = false) String sortBy,
                              @RequestParam(value = "sortDirection", required = false) String sortDirection,
                              @RequestParam(value = "limit", required = false) Integer limit,
                              @RequestParam(value = "offset", required = false) Integer offset) {
        List<Product> goods = productService.getAll();
        if (q != null) {
            goods = goods.stream().filter(g -> g.getTitle().toLowerCase().contains(q.toLowerCase())).collect(Collectors.toList());
        }
        goods.forEach(product -> {
            product.initializeImages();
        });
        goods = filteringProducts(goods, getPropertiesBySIds(propertiesIds), getGroupsBySIds(groupsIds), priceFrom, priceTo, sortBy, sortDirection, limit, offset);
        return toGSON(goods);
    }

    @RequestMapping(value = "goods", method = RequestMethod.POST,

            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String createProduct(@RequestBody LinkedHashMap parameters, HttpServletRequest request, ModelMap model) throws Exception {
        Product newProduct = new Product();
        BeanUtils.populate(newProduct, parameters);
        productService.add(newProduct);
        return toGSON(newProduct);
    }

    private String imageUploadingCheck(Product product, Image image, byte[] bytes) throws IOException {
        if (product.getImagesByProductId().size() < MAX_IMAGES_FOR_UPLOADING) {
            image.setProductByProductId(product);
            image = imageService.add(image);
            image.saveImagesToFS(bytes);
            image.initializeImages();
            return toGSON(image);
        } else {
            //show message
        }
        return "";
    }

    @RequestMapping(value = "goods/{id}/image", method = RequestMethod.POST)
    @ResponseBody
    public String imageUpload(@ModelAttribute("imageInstance") UploadedImage imageInstance,
                              @PathVariable("id") String id,
                              BindingResult result,
                              ModelMap model,
                              HttpServletRequest request) throws IOException {
        if (result.hasErrors()) {
            logger.error("Validation errors");
        } else {
            logger.info("Fetching file");
            Integer productId = getIdFromString(id);
            if (productId != null) {
                Product product = productService.get(productId);
                Image image = new Image(productId);
                return imageUploadingCheck(product, image, imageInstance.getFile().getBytes());
            }
        }
        return "";
    }

    @RequestMapping(value = "goods/{id}/image", params = {"url"}, method = RequestMethod.POST)
    public String imageUploadByURL(@PathVariable("id") String id, @RequestParam(value = "url", required = true) String urls,
                                   HttpServletRequest request) throws IOException {
        Integer productId = getIdFromString(id);
        if (productId != null) {
            Image image = new Image(productId);
            Product product = productService.get(productId);
            BufferedImage bufferedImage = null;
            URL url = new URL(urls);
            bufferedImage = ImageIO.read(url);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, ImageDecorator.IMAGE_EXTENSION, baos);
            return imageUploadingCheck(product, image, baos.toByteArray());
        }
        return "";
    }

    @RequestMapping(value = "goods/{productId}/images/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteImage(@PathVariable("productId") String prodIds,
                              @PathVariable("id") String ids,
                              ModelMap model,
                              HttpServletRequest request) throws IOException {
        Integer imageId = getIdFromString(ids);
        Integer productId = getIdFromString(prodIds);
        if (imageId != null && productId != null) {
            //TODO check if product contains image
            Product product = productService.get(productId);
            imageService.delete(imageId);
            return "success";
        }
        return "error";
    }

    @RequestMapping(value = "goods/{pid}", method = RequestMethod.POST,

            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String updateProduct(@RequestBody LinkedHashMap parameters, HttpServletRequest request, @PathVariable("pid") String pid, ModelMap model) throws Exception {

        Integer productId = getIdFromString(pid);
        Product originalProduct = null;
        if (productId != null) {
            originalProduct = productService.get(productId);
            BeanUtils.populate(originalProduct, parameters);
            Product finalOriginalProduct = originalProduct;

            //clear product properties
            originalProduct.getPropertyProductsByProductId().forEach(property -> {
                property.getProductsByPropertyId().removeIf(product ->
                        product.getProductId() == finalOriginalProduct.getProductId());
                propertyService.update(property);
            });

            //add new properties
            if (parameters.containsKey("propertiesIds")) {
                originalProduct.getPropertyProductsByProductId().clear();
                List<String> propertyIds = ((ArrayList<String>) parameters.get("propertiesIds"));

                propertyIds.forEach(s -> {
                    Integer propertyId = Integer.parseInt(s);
                    if (propertyId != null) {
                        Property property = propertyService.get(propertyId);
                        property.getProductsByPropertyId().add(finalOriginalProduct);
                        propertyService.update(property);
                    }
                });
            }
            if (parameters.containsKey("subcategoryGroupId")) {
                Integer groupId = Integer.parseInt(parameters.get("subcategoryGroupId").toString());
                if (groupId != null) {
                    SubcategoryGroup group = groupService.get(groupId);
                    originalProduct.setSubcategoryGroupBySubcategoryGroupId(group);
                    originalProduct.setSubcategoryGroupId(groupId);
                }
            }
            productService.update(originalProduct); //submit changes
        }
        return toGSON(originalProduct);
    }

    @RequestMapping(value = "goods/{pid}/descriptions/{did}", method = RequestMethod.PUT,

            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String updateDescription(@RequestBody LinkedHashMap parameters,
                                    @PathVariable("pid") String pid,
                                    @PathVariable("did") String did,
                                    HttpServletRequest request) throws Exception {
        Integer productId = getIdFromString(pid);
        Integer descriptionId = getIdFromString(did);
        DescriptionPart descriptionPart = null;
        if (null != productId && null != descriptionId) {
            Product product = productService.get(productId);
            descriptionPart = product.getDescription(descriptionId);
            BeanUtils.populate(descriptionPart, parameters);
            productService.update(product);
        }
        return toGSON(descriptionPart);
    }


    /**
     * /**
     * Uploading descriptionPart image
     *
     * @param request
     * @param httpRequest
     * @param pid
     * @param did
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "goods/{pid}/descriptions/{did}/image", method = RequestMethod.POST)
    @ResponseBody
    public String descriptionImageUpload(MultipartHttpServletRequest request,
                                         HttpServletRequest httpRequest,
                                         @PathVariable("pid") String pid,
                                         @PathVariable("did") String did, ModelMap model) throws Exception {
        Iterator<String> iterator = request.getFileNames();
        MultipartFile multiFile = request.getFile(iterator.next());
        logger.info("Fetching file");
        Integer productId = getIdFromString(pid);
        Integer descriptionId = getIdFromString(did);
        DescriptionPart descriptionPart = null;
        if (productId != null && descriptionId != null) {
            Product product = productService.get(productId);
            descriptionPart = product.getDescription(descriptionId);
            descriptionPart.saveImagesToFS(multiFile.getBytes());
            descriptionPart.initializeImages();
            productService.update(product);
        }

        return toGSON(descriptionPart);
    }

    @RequestMapping(value = "goods/{pid}/descriptions/{did}/image", method = RequestMethod.DELETE)
    @ResponseBody
    public String removeDescriptionImage(HttpServletRequest httpRequest,
                                         @PathVariable("pid") String pid,
                                         @PathVariable("did") String did) throws Exception {
        Integer productId = getIdFromString(pid);
        Integer descriptionId = getIdFromString(did);
        DescriptionPart descriptionPart = null;
        if (productId != null && descriptionId != null) {
            Product product = productService.get(productId);
            descriptionPart = product.getDescription(descriptionId);
            descriptionPart.deleteImagesFromFS();
            productService.update(product);
        }
        return toGSON(descriptionPart);
    }

    @RequestMapping(value = "categories/{id}/goods", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getProductsByCategory(@PathVariable("id") String id, HttpServletRequest request,
                                        @RequestParam(value = "properties", required = false) List<String> propertiesIds,
                                        @RequestParam(value = "groups", required = false) List<String> groupsIds,
                                        @RequestParam(value = "priceFrom", required = false) Double priceFrom,
                                        @RequestParam(value = "priceTo", required = false) Double priceTo,
                                        @RequestParam(value = "sortBy", required = false) String sortBy,
                                        @RequestParam(value = "sortDirection", required = false) String sortDirection,
                                        @RequestParam(value = "limit", required = false) Integer limit,
                                        @RequestParam(value = "offset", required = false) Integer offset) throws Exception {
        Integer categoryId = getIdFromString(id);

        if (categoryId != null) {
            Category category = categoryService.get(categoryId);
            if (null != category) {
                List<Product> goods = category.getGoods();
                goods = filteringProducts(goods, getPropertiesBySIds(propertiesIds), getGroupsBySIds(groupsIds), priceFrom, priceTo, sortBy, sortDirection, limit, offset);
                return toGSON(goods);
            }
        }
        return "{}";
    }

    @RequestMapping(value = "subcategories/{id}/goods", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getProductsBySubcategory(@PathVariable("id") String id, HttpServletRequest request,
                                           @RequestParam(value = "properties", required = false) List<String> propertiesIds,
                                           @RequestParam(value = "groups", required = false) List<String> groupsIds,
                                           @RequestParam(value = "priceFrom", required = false) Double priceFrom,
                                           @RequestParam(value = "priceTo", required = false) Double priceTo,
                                           @RequestParam(value = "sortBy", required = false) String sortBy,
                                           @RequestParam(value = "sortDirection", required = false) String sortDirection,
                                           @RequestParam(value = "limit", required = false) Integer limit,
                                           @RequestParam(value = "offset", required = false) Integer offset) throws Exception {
        Integer subcategoryId = getIdFromString(id);

        if (subcategoryId != null) {
            Subcategory subcategory = subcategoryService.get(subcategoryId);
            if (null != subcategory) {
                List<Product> goods = subcategory.getGoods();
                goods = filteringProducts(goods, getPropertiesBySIds(propertiesIds), getGroupsBySIds(groupsIds), priceFrom, priceTo, sortBy, sortDirection, limit, offset);
                return toGSON(goods);
            }
        }
        return "{}";
    }

    @RequestMapping(value = "groups/{id}/goods", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getProductsByGroup(@PathVariable("id") String id, HttpServletRequest request,
                                     @RequestParam(value = "properties", required = false) List<String> propertiesIds,
                                     @RequestParam(value = "priceFrom", required = false) Double priceFrom,
                                     @RequestParam(value = "priceTo", required = false) Double priceTo,
                                     @RequestParam(value = "sortBy", required = false) String sortBy,
                                     @RequestParam(value = "sortDirection", required = false) String sortDirection,
                                     @RequestParam(value = "limit", required = false) Integer limit,
                                     @RequestParam(value = "offset", required = false) Integer offset) throws Exception {
        Integer groupId = getIdFromString(id);

        if (groupId != null) {
            SubcategoryGroup group = groupService.get(groupId);
            if (null != group) {
                List<Product> goods = group.getGoodsBySubcategoryGroupId();
                //skip groups filter
                goods = filteringProducts(goods, getPropertiesBySIds(propertiesIds), new ArrayList<>(), priceFrom, priceTo, sortBy, sortDirection, limit, offset);
                return toGSON(goods);
            }
        }
        return "{}";
    }

    //endregion

    //region Images
    @RequestMapping(value = "images", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getAllImages(HttpServletRequest request) {
        return toGSON(imageService.getAll());
    }

    @RequestMapping(value = "images/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getImageById(@PathVariable("id") String id, HttpServletRequest request) {
        Integer imageId = getIdFromString(id);
        if (imageId != null) {
            try {
                Image image = imageService.get(imageId);
                return toGSON(image);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        return null;
    }

    @RequestMapping(value = "goods/{pId}/images/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getProductImageById(@PathVariable("pId") String pId, @PathVariable("id") String id, HttpServletRequest request) {
        Integer imageId = getIdFromString(id);
        Integer productId = getIdFromString(pId);
        if (imageId != null && productId != null) {
            try {
                Product product = productService.get(productId);
                Image image = imageService.get(imageId);
                image.initializeImages();
//                if(!product.getImagesByProductId().contains(image))
//                {
//                    return "";
//                }
                return toGSON(image);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        return null;
    }
    //endregion

    //region Property class

    @RequestMapping(value = "propertyClasses", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getAllPropertyClasses(HttpServletRequest request) {
        List<PropertyClass> propertyClasses = propertyClassService.getAll();
        return toGSON(propertyClasses);
    }

    @RequestMapping(value = "propertyClasses", method = RequestMethod.POST,

            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String addPropertyClass(@RequestBody LinkedHashMap parameters, HttpServletRequest request) throws Exception {
        PropertyClass propertyClass = new PropertyClass("New property class", "");
        BeanUtils.populate(propertyClass, parameters);
        propertyClassService.add(propertyClass);
        return toGSON(propertyClass);
    }

    @RequestMapping(value = "propertyClasses/{id}", method = RequestMethod.GET,

            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getPropertyClassById(@PathVariable("id") String id, HttpServletRequest request) {
        Integer propertyClassId = getIdFromString(id);
        PropertyClass propertyClass = null;
        if (propertyClassId != null) {
            propertyClass = propertyClassService.get(propertyClassId);
        }
        return toGSON(propertyClass);
    }

    @RequestMapping(value = "propertyClasses/{id}", method = RequestMethod.PUT,

            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String updatePropertyClass(@RequestBody LinkedHashMap parameters, @PathVariable("id") String id, HttpServletRequest request) throws Exception {
        Integer propertyClassId = getIdFromString(id);
        PropertyClass propertyClassOriginal = null;
        if (null != propertyClassId) {
            propertyClassOriginal = propertyClassService.get(propertyClassId);
            BeanUtils.populate(propertyClassOriginal, parameters);
            propertyClassService.update(propertyClassOriginal);
        }
        return toGSON(propertyClassOriginal);
    }

    @RequestMapping(value = "propertyClasses/{id}/delete", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String deletePropertyClass(@PathVariable("id") String id, HttpServletRequest request) {
        Integer propertyClassId = getIdFromString(id);
        if (null != propertyClassId) {
            propertyClassService.delete(propertyClassId);
        }
        return toGSON("id", propertyClassId.toString());
    }
    //endregion

    //region Property
    @RequestMapping(value = "properties", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getAllProperties(HttpServletRequest request) {
        List<Property> properties = propertyService.getAll();
        return toGSON(properties);
    }

    @RequestMapping(value = "properties", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String addProperty(@RequestBody LinkedHashMap parameters, HttpServletRequest request) throws Exception {
        Property property = new Property();
        BeanUtils.populate(property, parameters);
        propertyService.add(property);
        return toGSON(property);
    }

    @RequestMapping(value = "properties/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getPropertyById(@PathVariable("id") String id, HttpServletRequest request) {
        Integer propertyId = getIdFromString(id);
        Property property = null;
        if (propertyId != null) {
            property = propertyService.get(propertyId);
        }
        return toGSON(property);
    }

    @RequestMapping(value = "properties/{id}", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String updatePropertyById(@RequestBody LinkedHashMap parameters, @PathVariable("id") String id, HttpServletRequest request) throws Exception {
        Integer propertyId = getIdFromString(id);
        Property property = null;
        if (propertyId != null) {
            property = propertyService.get(propertyId);
            BeanUtils.populate(property, parameters);
            if (parameters.containsKey("propertyClassId")) {
                Integer propertyClassId = getIdFromString(parameters.get("propertyClassId").toString());
                if (propertyClassId != null) {
                    PropertyClass propertyClass = propertyClassService.get(propertyClassId);
                    property.setPropertyClassByPropertyClassId(propertyClass);
                    property.setPropertyClassId(propertyClassId);
                }
            }
            propertyService.update(property);
        }
        return toGSON(property);
    }

    @RequestMapping(value = "properties/{id}/delete", method = RequestMethod.GET,

            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String deletePropertyById(@PathVariable("id") String id, HttpServletRequest request) throws Exception {
        Integer propertyId = getIdFromString(id);
        if (propertyId != null) {
            propertyService.delete(propertyId);
        }
        return toGSON("id", propertyId.toString());
    }


    //endregion

    //region Advertisement
    @RequestMapping(value = "adv", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getAllAdvertisements(HttpServletRequest request) {
        List<Advertisement> advertisements = advertisementService.getAll();
        return toGSON(advertisements);
    }

    @RequestMapping(value = "adv", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String addAdvertisement(@RequestBody LinkedHashMap parameters, HttpServletRequest request) throws Exception {
        Advertisement advertisement = new Advertisement();
        BeanUtils.populate(advertisement, parameters);
        advertisement.initializeImages();
        advertisementService.add(advertisement);
        return toGSON(advertisement);
    }

    @RequestMapping(value = "adv/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getAdvertisementById(@PathVariable("id") String id, HttpServletRequest request) {
        Integer advId = getIdFromString(id);
        Advertisement advertisement = null;
        if (advId != null) {
            advertisement = advertisementService.get(advId);
        }
        return toGSON(advertisement);
    }

    @RequestMapping(value = "adv/{id}/image", method = RequestMethod.POST)
    @ResponseBody
    public String changeAdvertisementImage(MultipartHttpServletRequest request,
                                           HttpServletRequest httpRequest,
                                           @PathVariable("id") String pid, ModelMap model) throws Exception {
        Iterator<String> iterator = request.getFileNames();
        MultipartFile multiFile = request.getFile(iterator.next());

        Integer advertisementId = getIdFromString(pid);
        Advertisement advertisement = null;
        if (advertisementId != null) {
            advertisement = advertisementService.get(advertisementId);
            advertisement.saveImagesToFS(multiFile.getBytes());
            advertisement.initializeImages();
            advertisementService.update(advertisement);
        }
        return toGSON(advertisement);
    }

    @RequestMapping(value = "adv/{id}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String updateAdvertisementById(@RequestBody LinkedHashMap parameters, @PathVariable("id") String id, HttpServletRequest request) throws Exception {
        Integer advId = getIdFromString(id);
        Advertisement advertisement = null;
        if (advId != null) {
            advertisement = advertisementService.get(advId);
            BeanUtils.populate(advertisement, parameters);
            advertisementService.update(advertisement);
        }
        return toGSON(advertisement);
    }

    @RequestMapping(value = "adv/{id}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String deleteAdvertisementById(@PathVariable("id") String id, HttpServletRequest request) throws Exception {
        Integer advId = getIdFromString(id);
        if (advId != null) {
            advertisementService.delete(advId);
        }
        return "success";
    }
    //endregion

    //region Customer
    @RequestMapping(value = "customers", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getCustomers(HttpServletRequest request) {
        List<Customer> customers = customerService.getAll();
        return toGSON(customers);
    }

    @RequestMapping(value = "customers/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getCustomerById(@PathVariable("id") String id, HttpServletRequest request) {
        Integer customerId = getIdFromString(id);
        if (customerId != null) {
            try {
                Customer customer = customerService.get(customerId);
                return toGSON(customer);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        return null;
    }

    @RequestMapping(value = "customers/{id}", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String changeCustomer(@RequestBody LinkedHashMap parameters, @PathVariable("id") String id, HttpServletRequest request) throws Exception {

        Integer objectId = getIdFromString(id);
        Customer customer = null;
        if (null != objectId) {
            customer = customerService.get(objectId);
            String name = customer.getCustomerName();
            String password = customer.getPassword();
            BeanUtils.populate(customer, parameters);
            if (customerService.getCustomer(customer.getCustomerName()).size() > 1) {
                //TODO error - this customer already exists
                customer.setCustomerName(name);
            }
            Address address = customer.getAddressByAddressId();
            if (address == null) {
                address = new Address();
                addressService.add(address);
            }
            BeanUtils.populate(address, parameters);
            if (parameters.containsKey("buildingNumber")) {
                Integer number = getIdFromString(parameters.get("buildingNumber").toString());
                if (number != null) {
                    address.setBuildingNumber(number);
                }
            }
            customer.setPassword(password);
            customer.setAddressByAddressId(address);
            customer.setAddressId(address.getAddressId());
            customerService.update(customer);
        }
        return toGSON(customer);
    }

    @RequestMapping(value = "customers/{id}/password", method = RequestMethod.POST)
    @ResponseBody
    public String changeCustomerPassword(
            @RequestParam(value = "oldPassword") String oldPassword,
            @RequestParam(value = "newPassword") String newPassword,
            @RequestParam(value = "confirmedPassword") String confirmedPassword,
            @PathVariable("id") String id, HttpServletRequest request) {
        Integer objectId = getIdFromString(id);
        Customer customer = null;
        boolean isChanged = false;
        if (null != objectId) {
            customer = customerService.get(objectId);
            if (oldPassword.equals(customer.getPassword())
                    && newPassword.equals(confirmedPassword)) {
                customer.setPassword(newPassword);
                isChanged = true;
            }
            customerService.update(customer);
        }
        return "{\"password\":\"" + isChanged + "\"}";
    }

    @RequestMapping(value = "customers/{id}/image", method = RequestMethod.POST)
    @ResponseBody
    public String changeCustomerImage(MultipartHttpServletRequest request,
                                      HttpServletRequest httpRequest,
                                      @PathVariable("id") String pid, ModelMap model) throws Exception {
        Iterator<String> iterator = request.getFileNames();
        MultipartFile multiFile = request.getFile(iterator.next());

        Integer customerId = getIdFromString(pid);
        Customer customer = null;
        if (customerId != null) {
            customer = customerService.get(customerId);
            customer.saveImagesToFS(multiFile.getBytes());
            customer.initializeImages();
            customerService.update(customer);
        }
        return toGSON(customer);
    }

    //endregion

    //region SubscribedUser

    @RequestMapping(value = "subscribed", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getAllSubscribed(HttpServletRequest request, HttpSession session) throws Exception {
        List<SubscribedUser> subscribedUsers = subscribedUserDAO.getAll();
        return toGSON(subscribedUsers);
    }


    @RequestMapping(value = "subscribed/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getSubscribedUserById(@PathVariable("id") Integer id, HttpServletRequest request) throws Exception {

        SubscribedUser subscribedUser = null;
        if (id != null) {
            subscribedUser = subscribedUserDAO.get(id);
        }
        return toGSON(subscribedUser);
    }

    @RequestMapping(value = "subscribed", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String addSubscribedUser(@RequestParam(value = "email", required = false) String email, HttpServletRequest request) {
        SubscribedUser subscribedUser = null;
        if (email != null && !email.equals("")) {
            List<SubscribedUser> users = subscribedUserDAO.getAll();
            Optional<SubscribedUser> user = users.stream().filter(subscribedUser1 ->
                    subscribedUser1.getEmail().equals(email)).findFirst();
            if (!user.isPresent()) {
                subscribedUser = new SubscribedUser(email);
                subscribedUserDAO.add(subscribedUser);
            } else {
                subscribedUser = user.get();
            }
            String toAddr = email;
            String fromAddr = "webshop.lviv2016@mail.ru";
            String subject = "Welcome to WebShop";
            String body = "\t\tWe are glad to see that you are interested in out service.\n" +
                    "\t\t\tWish you to find everything you want. \n\t\t\t\tGO SHOPPING WITH US";
            mailService.sendEmail(toAddr, fromAddr, subject, body);
        }
        return toGSON(subscribedUser);
    }
    //endregion

    //region Orders
    @RequestMapping(value = "orders", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getOrders(HttpServletRequest request) {
        return toGSON(orderService.getAll());
    }

    @RequestMapping(value = "orders", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String addOrder(@ActiveUser User user, @RequestBody LinkedHashMap parameters, HttpServletRequest request, HttpSession session) throws Exception {
        Customer customer = null;
        ProductOrder order = null;
        updateSession(user, session);

        //if user is logged in
        if (user != null) {
            customer = customerService.getCustomer(user.getUsername()).get(0);
        } else {
            //else using his/her id
            if (parameters.containsKey("customerId")) {
                Integer customerId = Integer.parseInt(parameters.get("customerId").toString());
                if (customerId != null) {
                    customer = customerService.get(customerId);
                }
            }
        }
        if (customer != null) {
            Address address = new Address();
            BeanUtils.populate(address, parameters);
            order = new ProductOrder(customer);
            if (!customer.getAddressByAddressId().equals(address)) {
                address = addressService.add(address);
                order.setAddressByDeliveryAddress(address);
            }

            order = orderService.add(order);
            double total = 0.0;
            for (Cart cart : customer.getCartsByCustomerId()) {
                ProductOrderProduct productOrderProduct = new ProductOrderProduct(order, cart.getProductByProductId(), cart.getProductAmount());
                productOrderProduct = productOrderDAO.add(productOrderProduct);
                total += cart.getProductByProductId().getPrice() * cart.getProductAmount();
                order.getProductOrderProductsByProductOrderId().add(productOrderProduct);
            }
            order.setTotal(total);
            orderService.update(order);
            customer.getProductOrdersByCustomerId().add(order);
            customer.getCartsByCustomerId().clear();
            customerService.update(customer);
        }
        return toGSON(order);
    }

    @RequestMapping(value = "orders/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getOrderById(@PathVariable("id") String id, HttpServletRequest request) {
        Integer orderId = getIdFromString(id);
        if (orderId != null) {
            try {
                ProductOrder productOrder = orderService.get(orderId);
                return toGSON(productOrder);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        return null;
    }

    @RequestMapping(value = "orders/{id}", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String changeOrder(@RequestBody LinkedHashMap parameters, @PathVariable("id") Integer orderId, HttpServletRequest request) {
        if (orderId != null) {
            try {
                ProductOrder productOrder = orderService.get(orderId);
                BeanUtils.populate(productOrder, parameters);
                orderService.update(productOrder);
                return toGSON(productOrder);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        return null;
    }

    @RequestMapping(value = "orders/{id}/delete", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String deleteOrdersById(@PathVariable("id") String id, HttpServletRequest request) throws Exception {
        Integer orderId = getIdFromString(id);
        if (orderId != null) {
            orderService.delete(orderId);
        }
        return "success";
    }

    //endregion

    //region Cart

    private List<Cart> getAllCartsFunc(HttpSession session) {
        List<Cart> carts = null;
        Customer activeUser = getActiveUser(session);
        if (activeUser != null) {
            carts = activeUser.getCartsByCustomerId();
        } else {
            carts = cartDAO.getAll();
        }
        return carts;
    }

    @RequestMapping(value = "cart", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getAllCarts(HttpServletRequest request, HttpSession session) throws Exception {
        List<Cart> carts = getAllCartsFunc(session);
        return toGSON(carts);
    }

    @RequestMapping(value = "cart/total", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getAllCartsTotalPrice(HttpServletRequest request, HttpSession session) throws Exception {
        Double total = 0.0;
        List<Cart> carts = getAllCartsFunc(session);
        for (Cart cart : carts) {
            total += cart.getProductAmount() * cart.getProductByProductId().getPrice();
        }
        return toGSON("total", total.toString());
    }

    @RequestMapping(value = "cart/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getCartItemById(@RequestParam(value = "customerId", required = true) Integer customerId, @PathVariable("id") Integer id, HttpServletRequest request) throws Exception {

        Cart cart = null;
        if (customerId != null) {
            Customer customer = customerService.get(customerId);
            if (customer != null) {
                cart = customer.getCartsByCustomerId().stream().filter(cart1 ->
                        cart1.getCartProductId() == id
                ).findFirst().get();
            }
        }
        return toGSON(cart);
    }

    @RequestMapping(value = "cart/{id}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String changeCartAmount(@PathVariable("id") Integer cartId, @RequestBody LinkedHashMap parameters, HttpSession session) throws Exception {
        Cart cart = null;
        Integer amount = getIdFromString(parameters.get("amount").toString());
        if (cartId != null) {
            cart = cartDAO.get(cartId);
            if (amount != null) {
                cart.setProductAmount(amount);
                cartDAO.update(cart);
            }
        }
        return toGSON(cart);
    }

    @RequestMapping(value = "cart/{id}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String deleteCart(@PathVariable("id") Integer cartId, HttpSession session) throws Exception {
        if (cartId != null) {
            Customer customer = getActiveUser(session);
            if (customer != null) {
                customer.getCartsByCustomerId().removeIf(cart1 ->
                        cart1.getCartProductId() == cartId
                );
                customerService.update(customer);
            }
        }
        return "{\"deletedId\":" + cartId + "}";
    }


    @RequestMapping(value = "cart", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String addToCart(@RequestBody LinkedHashMap parameters,
                            HttpSession session) throws Exception {
        Integer productId = getIdFromString(parameters.get("productId").toString());
        Integer amount = getIdFromString(parameters.get("amount").toString());
        Cart cart = null;
        Customer activeUser = getActiveUser(session);
        if (activeUser != null) {
            if (null != productId && amount != null) {
                Product product = productService.get(productId);


                //check if user contains cart
                Optional<Cart> first = activeUser.getCartsByCustomerId().stream().filter(cart1 ->
                        cart1.getProductId() == productId
                ).findFirst();
                if (first.isPresent())
                    cart = first.get();
                //check if user contains cart
                if (cart != null) {
                    cart.setProductAmount(amount);
                } else {
                    cart = new Cart(product, amount, activeUser);
                    activeUser.getCartsByCustomerId().add(cart);
                }
                customerService.update(activeUser);
            }
        } else throw new UserNotFoundException("User not found");
        return toGSON(cart);
    }

    //endregion

}
