package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 校区管理：(CampusManagement)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "CampusManagement")
public class CampusManagement implements Serializable {

    // CampusManagement编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campus_management_id")
    private Integer campus_management_id;

    // 校区名称
    @Basic
    private String campus_name;














    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
