package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 行为评分：(BehavioralRating)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "BehavioralRating")
public class BehavioralRating implements Serializable {

    // BehavioralRating编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "behavioral_rating_id")
    private Integer behavioral_rating_id;

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
    // 总分数
    @Basic
    private Integer total_score;
    // 总扣分
    @Basic
    private Integer total_deduction_points;
    // 实际分数
    @Basic
    private String actual_score;














    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
