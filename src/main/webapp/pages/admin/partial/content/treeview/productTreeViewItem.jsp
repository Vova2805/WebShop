<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    $(document).ready();
</script>

<li id="productLi${product.productId}">
    <a class="cursor-hand" onclick="changeAdminContentInnerBlock('goods',${product.productId})">
        <i class="fa fa-image"></i>
        <span class="span-overflow product" id="goodsSpan${product.productId}">${product.title}</span>
                                                                                 <span class="pull-right-container success margin-top-5 fa-container plus"
                                                                                       data-toggle="tooltip"
                                                                                       data-placement="right"
                                                                                       onclick="changeAdminContentInnerBlock('product',${product.productId})"
                                                                                       title="Edit product">
                                                                                      <i class="fa fa-pencil-square-o pull-right"
                                                                                         aria-hidden="true"></i>
                                                                                      <i class="fa fa-pencil-square pull-right i-success"
                                                                                         aria-hidden="true"></i>
                                                                                </span>

                                                                                 <span class="pull-right-container danger margin-top-5 fa-container remove"
                                                                                       onclick="deleteTreeViewItem('${product.subcategoryGroupBySubcategoryGroupId.id}','${product.productId}','admin/goods/','productLi','groupRootUl')"
                                                                                       data-toggle2="tooltip"
                                                                                       data-placement="right"
                                                                                       title="Remove product">
                                                                                    <i class="fa fa-remove pull-right"
                                                                                       aria-hidden="true"></i>
                                                                                    <i class="fa fa-times-circle pull-right i-danger"
                                                                                       aria-hidden="true"></i>
                                                                                 </span>
    </a>
</li>