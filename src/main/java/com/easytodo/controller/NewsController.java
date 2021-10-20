package com.easytodo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easytodo.common.lang.Result;
import com.easytodo.entity.News;
import com.easytodo.service.NewsService;
import org.springframework.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lheng
 * @since 2021-10-20
 */
@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    NewsService newsService;

    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage) {

        Page page = new Page(currentPage, 5);
        IPage pageData = newsService.page(page,
                new QueryWrapper<News>().orderByDesc(
                "publish_time"));

        return Result.succ(pageData);
    }

    @GetMapping("/list/{id}")
    public Result detail(@PathVariable(name = "id") Long id) {
        News news = newsService.getById(id);
        Assert.notNull(news, "This news have been deleted.");

        return Result.succ(news);
    }

}
