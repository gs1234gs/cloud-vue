package com.tianchang.pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "sys_post")
public class Post {
    //岗位id
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    //岗位代号
    private String postCode;
    //岗位名称
    private String postName;
    //显示顺序
    private Integer postSort;
    //状态（0正常 1停用）
    private Short status;
    //创建者
    private String createBy;
    //创建时间
    private LocalDateTime createTime;
    //更新者
    private String updateBy;
    //更新时间
    private LocalDateTime updateTime;
    //备注
    private String remark;
}
