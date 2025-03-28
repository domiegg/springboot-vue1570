package com.project.demo.controller;

import com.project.demo.entity.BonusItems;
import com.project.demo.service.BonusItemsService;
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
 * 加分项目：(BonusItems)表控制层
 *
 */
@RestController
@RequestMapping("/bonus_items")
public class BonusItemsController extends BaseController<BonusItems, BonusItemsService> {

    /**
     * 加分项目对象
     */
    @Autowired
    public BonusItemsController(BonusItemsService service) {
        setService(service);
    }


    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        String sql = "SELECT MAX(bonus_items_id) AS max FROM "+"`bonus_items`";
        Query select = service.runCountSql(sql);
        Integer max = (Integer) select.getSingleResult();
        sql = "UPDATE `behavioral_rating` INNER JOIN `bonus_items` ON behavioral_rating.student_id=bonus_items.student_id SET behavioral_rating.total_score= behavioral_rating.total_score + bonus_items.bonus_points WHERE bonus_items.bonus_items_id="+max;
        select = service.runCountSql(sql);
        select.executeUpdate();
        return success(1);
    }

}
