package com.project.demo.controller;

import com.project.demo.entity.SemesterTotal;
import com.project.demo.service.SemesterTotalService;
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
 * 学期总计：(SemesterTotal)表控制层
 *
 */
@RestController
@RequestMapping("/semester_total")
public class SemesterTotalController extends BaseController<SemesterTotal, SemesterTotalService> {

    /**
     * 学期总计对象
     */
    @Autowired
    public SemesterTotalController(SemesterTotalService service) {
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
