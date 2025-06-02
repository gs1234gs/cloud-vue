package com.tianchang.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dept {
    //部门id
    private Integer deptId;
    //部门父级id
    private Integer parentId;
    //祖籍列表
    private String ancestors;
    //部门名称
    private String deptName;
    //显示顺序
    private Integer orderNum;
    //负责人
    private String leader;
    //联系电话
    private String phoneNumber;
    //邮箱
    private String email;
    //部门状态（0正常 1停用）
    private Short status;
    //删除标志（0代表存在 2代表删除）
    private Short delFlag;
    //创建者
    private String createBy;
    //创建时间
    private LocalDateTime createTime;
    //更新者
    private String updateBy;
    //更新时间
    private LocalDateTime updateTime;
    //树形结构等级
    private Integer level;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Dept> children = new ArrayList<>();

//    // 安全获取子节点方法
//    public List<Dept> getSafeChildren() {
//        return children == null ? new ArrayList<>() : children;
//    }

}
