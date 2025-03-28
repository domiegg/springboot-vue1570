package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 加分项目：(BonusItems)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "BonusItems")
public class BonusItems implements Serializable {

    // BonusItems编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bonus_items_id")
    private Integer bonus_items_id;

    // 评分日期
    @Basic
    private Timestamp rating_date;
    // 校区名称
    @Basic
    private String campus_name;
    // 年级名称
    @Basic
    private String grade_name;
    // 班级名称
    @Basic
    private String class_name;
    // 学生
    @Basic
    private Integer student;
    // 学号
    @Basic
    private String student_id;
    // 评分类型
    @Basic
    private String rating_type;
    // 加分分数
    @Basic
    private Integer bonus_points;
    // 备注信息
    @Basic
    private String remarks;
    // 评分教师
    @Basic
    private Integer rating_teacher;














    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
