package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 年级管理：(GradeManagement)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "GradeManagement")
public class GradeManagement implements Serializable {

    // GradeManagement编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_management_id")
    private Integer grade_management_id;

    // 年级名称
    @Basic
    private String grade_name;














    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
