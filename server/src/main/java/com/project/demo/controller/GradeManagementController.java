package com.project.demo.controller;

import com.project.demo.entity.GradeManagement;
import com.project.demo.service.GradeManagementService;
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
 * 年级管理：(GradeManagement)表控制层
 *
 */
@RestController
@RequestMapping("/grade_management")
public class GradeManagementController extends BaseController<GradeManagement, GradeManagementService> {

    /**
     * 年级管理对象
     */
    @Autowired
    public GradeManagementController(GradeManagementService service) {
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
