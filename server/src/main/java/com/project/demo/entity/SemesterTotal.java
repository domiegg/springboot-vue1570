package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 学期总计：(SemesterTotal)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "SemesterTotal")
public class SemesterTotal implements Serializable {

    // SemesterTotal编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "semester_total_id")
    private Integer semester_total_id;

    // 学生
    @Basic
    private Integer student;
    // 学号
    @Basic
    private String student_id;
    // 校区名称
    @Basic
    private String campus_name;
    // 年级名称
    @Basic
    private String grade_name;
    // 班级名称
    @Basic
    private String class_name;
    // 评分学期
    @Basic
    private String graded_semester;
    // 满分
    @Basic
    private String full_score;
    // 加分
    @Basic
    private String bonus_points;
    // 扣分
    @Basic
    private String deduction_points;
    // 合计
    @Basic
    private String total;
    // 总分
    @Basic
    private String total_score;














    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
