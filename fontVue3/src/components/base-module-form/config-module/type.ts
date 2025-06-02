import type { VNode, Component } from "vue";

export interface IFormItem {
  type?: string;
  label: string;
  prop?: any;
  width?: string; // 可选属性
  slot?: string | {
    name: string;
    render?: () => VNode | Component;
  };
  placeholder?: string; // 可选属性
  options?: Array<{ label: string; value: number | string }>; // 可选属性
  props?: {
    value?: string;
    label?:string;
    children?: string;
    checkStrictly?: boolean;
  };
  initValue?: any;
  menuListId?: any;
}
export interface childrenTree {
  rowKey: string;
  treeProps: {
    children: string;
    hasChildren: string;
  };
}
export interface OtherInfo {
  [key: string]: any;
}

export interface IModuleConfig {
  pageName: string;
  header: {
    newTitle: string;
    editTitle: string;
  };
  formItem: IFormItem[];
  childrenTree?: childrenTree;
  otherInfo?: OtherInfo;
}

export interface IModuleIProps {
  moduleConfig: IModuleConfig;
}