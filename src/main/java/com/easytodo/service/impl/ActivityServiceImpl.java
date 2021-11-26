package com.easytodo.service.impl;

import com.easytodo.entity.Activity;
import com.easytodo.mapper.ActivityMapper;
import com.easytodo.service.ActivityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lheng
 * @since 2021-11-27
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {

}
