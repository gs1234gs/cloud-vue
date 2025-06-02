import type { IModuleConfig } from "@/components/base-module-form/config-module/type";

const moduleConfig:IModuleConfig = {
 pageName: "menu",
  header: {
    newTitle: "新建菜单",
    editTitle: "编辑菜单",
  },
  formItem: [
    {
      type: "input",
      label: "菜单类型",
      prop: "menuType",
      width: "150px",
      slot: "menuType",
      placeholder: "请输入菜单名称",
    },
    {
      type: "input",
      label: "菜单名称",
      prop: "menuName",
      width: "150px",
      slot: "menuName",
      placeholder: "请输入菜单名称",
    },
    {
      type: "select",
      label: "父级菜单",
      prop: "parentId",
      width: "100px",
      placeholder: "请选择上级菜单",
      options: [], // 初始化为空，动态加载
      props: {
        // 级联选择器配置
        value: "menuId",    // 节点值的字段名
        label: "menuName",  // 节点标签的字段名
        children: "children", // 子节点的字段名
        checkStrictly: true // 允许选择任意一级节点
      }
    },
    {
      type: "input",
      label: "权限标识",
      prop: "perms",
      width: "150px",
      slot: "permsName",
      placeholder: "请输入菜单名称",
    },
    {
      type: "input",
      label: "路由地址名称",
      prop: "path",
      width: "150px",
      slot: "path",
      placeholder: "请输入菜单名称",
    },
  ]
};

export default moduleConfig;
