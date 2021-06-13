package com.bw.edu.ctb.common.util;

import com.google.common.util.concurrent.RateLimiter;

/**
 * 限流控制器
 */
public class AccessControl {
    // 错题本上传限流，每秒发出3个令牌
    static RateLimiter CTB_UPLOAD_RT = RateLimiter.create(3.0);

    /**
     * 尝试获取令牌
     */
    public static boolean ctbUploadLimite() {
        return CTB_UPLOAD_RT.tryAcquire();
    }
}
