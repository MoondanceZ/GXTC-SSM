package com.rk.controller;

import com.rk.common.enums.ProductStatus;
import com.rk.common.exception.DataNotFoundException;
import com.rk.dto.response.LayPage;
import com.rk.dto.response.ReturnResult;
import com.rk.dto.request.ProductPageRequest;
import com.rk.entity.Product;
import com.rk.service.interfaces.ProductService;
import com.rk.service.interfaces.ProductTypeService;
import com.rk.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.List;


/**
 * Created by Qin_Yikai on 2018-09-29.
 */
@Controller
@RequestMapping("/product")
public class ProductController extends BaseController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductTypeService productTypeService;

    @RequestMapping(value = "/pageList", method = RequestMethod.GET)
    @ResponseBody
    public LayPage<List<Product>> PageList(ProductPageRequest pageRequest) {
        return productService.getPageList(pageRequest);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editProduct(@RequestParam(value = "id", required = false) Long id, Model model) throws DataNotFoundException {
        if (id != null) {
            Product product = productService.getByPrimaryKey(id);
            if (product == null)
                throw new DataNotFoundException("产品不存在");
            model.addAttribute("product", product);
        } else {
            model.addAttribute("product", new Product());
        }

        model.addAttribute("enableTypes", productTypeService.getEnableTypes());
        //model.addAttribute("productStatus", ProductStatus.ToList());
        return "admin/product/edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody()
    public ReturnResult Save(HttpServletRequest request, @Valid Product product,
                             @RequestParam(value = "imgFile1", required = false) MultipartFile imgFile1,
                             @RequestParam(value = "imgFile2", required = false) MultipartFile imgFile2,
                             @RequestParam(value = "imgFile3", required = false) MultipartFile imgFile3)
            throws IOException {

        //采用这种校验方式是因为前段为 multipart/form-data 方式提交时, 添加 @Valid 会报400错误, 暂时无法解决
        //ReturnResult validateResult = ValidatorHelper.validate(product);
        //if (validateResult != null) return validateResult;

        if (!imgFile1.isEmpty()) {
            String imgFile1Name = FileUtils.saveImage(request, imgFile1);
            if (imgFile1Name != null) {
                String[] nameStrings = imgFile1Name.split("/");
                product.setImage1(nameStrings[nameStrings.length - 1]);
            }
        }

        if (!imgFile2.isEmpty()) {
            String imgFile2Name = FileUtils.saveImage(request, imgFile2);
            if (imgFile2Name != null) {
                String[] nameStrings = imgFile2Name.split("/");
                product.setImage2(nameStrings[nameStrings.length - 1]);
            }
        }

        if (!imgFile3.isEmpty()) {
            String imgFile3Name = FileUtils.saveImage(request, imgFile3);
            if (imgFile3Name != null) {
                String[] nameStrings = imgFile3Name.split("/");
                product.setImage3(nameStrings[nameStrings.length - 1]);
            }
        }

        if (product.getId() != null)
            product.setModifyDate(new Date());
        else
            product.setCreateDate(new Date());

        if(product.getUnifiedPrice() == null)
            product.setUnifiedPrice(false);

        return productService.updateOrAdd(product);
    }

    @RequestMapping(value = "/delete")
    @ResponseBody()
    public ReturnResult Delete(@RequestParam("ids[]") Long[] ids) {
        return productService.delete(ids);
    }
}
