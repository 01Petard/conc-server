package com.hzx.conc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzx.conc.entity.Clazz;
import com.hzx.conc.entity.Conc;
import com.hzx.conc.mapper.ClazzMapper;
import com.hzx.conc.mapper.ConcMapper;
import com.hzx.conc.service.ClazzService;
import com.hzx.conc.service.ConcService;
import org.springframework.stereotype.Service;

/**
 * (Class)表服务实现类
 *
 * @author hzx
 * @since 2025-02-22 14:37:41
 */
@Service
public class ConcServiceImpl extends ServiceImpl<ConcMapper, Conc> implements ConcService {

}

