package com.tianchang.userServiceImplUtils.user;

import com.tianchang.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class UserHelper {
    public void log(Object obj){
        log.info("查询用户列表成功，结果：{}", obj);
    }

    public Result isDelete(Integer userId){
        if(userId == null || userId == 0 ||userId<=0){
            return Result
                    .builder()
                    .code(400)
                    .msg("删除失败,用户不存在")
                    .build();
        }
      else if(userId == 1){
            return Result
                    .builder()
                    .code(400)
                    .msg("删除失败，该用户为管理员，不能删除")
                    .build();
        }
        else  if(userId == 2){
            return Result
                    .builder()
                    .code(400)
                    .msg("删除失败，该用户为普通用户，不能删除")
                    .build();
        }
        else {
            return Result
                    .builder()
                    .code(400)
                    .msg("删除失败，该用户为测试用户，不能删除")
                    .build();
        }

    }
}
