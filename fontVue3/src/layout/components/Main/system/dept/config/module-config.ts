import type { IModuleConfig } from "@/components/base-module-form/config-module/type";

const moduleConfig:IModuleConfig = {
 pageName: "dept",
  header: {
    newTitle: "新建部门",
    editTitle: "编辑部门",
  },
  formItem: [
    {
      type: "input",
      label: "部门名称",
      prop: "deptName",
      width: "150px",
      slot: "deptName",
      placeholder: "请输入部门名称",
    },
    {
      type: "input",
      label: "部门负责人",
      prop: "leader",
      width: "100px",
      placeholder: "请输入部门负责人姓名",
    },
    {
      type: "select",
      label: "上级部门",
      prop: "parentId",
      width: "100px",
      placeholder: "请选择上级部门",
      options: [], // 初始化为空，动态加载
      props: {
        // 级联选择器配置
        value: "deptId",    // 节点值的字段名
        label: "deptName",  // 节点标签的字段名
        children: "children", // 子节点的字段名
        checkStrictly: true // 允许选择任意一级节点
      }
    },
    {
      type: "input",
      label: "联系方式",
      prop: "phoneNumber",
      width: "150px",
      placeholder: "请输入部门负责人手机号",
    },
    {
      type: "select",
      label: "部门状态",
      prop: "status",
      width: "150px",
      slot: "status",
      placeholder: "请输入部门名称",
    },

  ] ,
  childrenTree:{
    rowKey: 'deptId',
    treeProps: {
    children: 'children',
    hasChildren: 'hasChildren'
  }
},

};

export default moduleConfig;
