package com.bw.edu.ctb.common.util;

import com.bw.edu.ctb.exception.CtbExceptionEnum;
import com.google.common.util.concurrent.RateLimiter;

import static com.bw.edu.ctb.exception.CtbExceptionEnum.promoteException;

/**
 * 限流控制器
 */
public class AccessControl {
    // 错题本上传限流，每秒发出3个令牌
    static RateLimiter CTB_UPLOAD_RT = RateLimiter.create(3.0);

    /**
     * 尝试获取令牌
     */
    public static void ctbUploadLimite() {
        if(!CTB_UPLOAD_RT.tryAcquire()){
            promoteException(CtbExceptionEnum.FLOW_CONTROL);
        }
    }
}
