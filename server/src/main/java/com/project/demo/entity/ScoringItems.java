package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 评分项目：(ScoringItems)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ScoringItems")
public class ScoringItems implements Serializable {

    // ScoringItems编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scoring_items_id")
    private Integer scoring_items_id;

    // 评分类型
    @Basic
    private String rating_type;














    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
