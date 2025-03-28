package com.project.demo.controller;

import com.project.demo.entity.DeductionItems;
import com.project.demo.service.DeductionItemsService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 扣分项目：(DeductionItems)表控制层
 *
 */
@RestController
@RequestMapping("/deduction_items")
public class DeductionItemsController extends BaseController<DeductionItems, DeductionItemsService> {

    /**
     * 扣分项目对象
     */
    @Autowired
    public DeductionItemsController(DeductionItemsService service) {
        setService(service);
    }


    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}
