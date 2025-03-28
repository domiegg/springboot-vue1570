package com.project.demo.controller;

import com.project.demo.entity.BehavioralRating;
import com.project.demo.service.BehavioralRatingService;
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
 * 行为评分：(BehavioralRating)表控制层
 *
 */
@RestController
@RequestMapping("/behavioral_rating")
public class BehavioralRatingController extends BaseController<BehavioralRating, BehavioralRatingService> {

    /**
     * 行为评分对象
     */
    @Autowired
    public BehavioralRatingController(BehavioralRatingService service) {
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
