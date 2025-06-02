package com.tianchang.userServiceImplUtils.depts;

import com.tianchang.pojo.Dept;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DeptTreeHelper {

    // 构建树结构
    public List<Dept> buildDeptTree(List<Dept> list) {
        Map<Integer, Dept> nodeMap = new LinkedHashMap<>(list.size());
        List<Dept> roots = new ArrayList<>();

        // 第一次遍历：创建所有节点
        for (Dept dept : list) {
            dept.setChildren(new ArrayList<>());
            nodeMap.put(dept.getDeptId(), dept);
        }

        // 第二次遍历：建立父子关系
        for (Dept dept : list) {
            if (dept.getParentId() == 0) {
                roots.add(dept);
            } else {
                Dept parent = nodeMap.get(dept.getParentId());
                if (parent != null) {
                    parent.getChildren().add(dept);
                    checkCyclic(nodeMap, dept); // 循环引用检测
                }
            }
        }
        return roots;
    }

    // 递归排序
    public void sortTree(List<Dept> nodes) {
        if (nodes == null || nodes.isEmpty()) {
            return;
        }
        nodes.sort(Comparator.comparingInt(Dept::getOrderNum));
        for (Dept node : nodes) {
            sortTree(node.getChildren());
        }
    }

    // 循环引用检测
    private void checkCyclic(Map<Integer, Dept> nodeMap, Dept current) {
        Set<Integer> path = new HashSet<>();
        while (current != null) {
            if (!path.add(current.getDeptId())) {
                throw new IllegalStateException("检测到部门循环引用: " + current.getDeptId());
            }
            current = nodeMap.get(current.getParentId());
        }
    }
}
