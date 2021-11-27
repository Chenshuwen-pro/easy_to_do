package com.easytodo.service.impl;

import com.easytodo.entity.Comment;
import com.easytodo.mapper.CommentMapper;
import com.easytodo.service.CommentService;
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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
