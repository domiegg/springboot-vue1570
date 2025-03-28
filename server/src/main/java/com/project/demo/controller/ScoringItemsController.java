package com.project.demo.controller;

import com.project.demo.entity.ScoringItems;
import com.project.demo.service.ScoringItemsService;
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
 * 评分项目：(ScoringItems)表控制层
 *
 */
@RestController
@RequestMapping("/scoring_items")
public class ScoringItemsController extends BaseController<ScoringItems, ScoringItemsService> {

    /**
     * 评分项目对象
     */
    @Autowired
    public ScoringItemsController(ScoringItemsService service) {
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
