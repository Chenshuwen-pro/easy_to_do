package com.easytodo.service.impl;

import com.easytodo.entity.News;
import com.easytodo.mapper.NewsMapper;
import com.easytodo.service.NewsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lheng
 * @since 2021-10-20
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {

}
