package com.bw.edu.ctb.cache;

import com.bw.edu.ctb.domain.UnitDO;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 本地单元缓存
 */
@Configuration
public class LcUnit {
    /** the key indicates class code */
    public static final Map<Integer, UnitDO> UNIT_MAP = new HashMap<>();
}
