package com.easytodo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easytodo.common.lang.Result;
import com.easytodo.entity.Activity;
import com.easytodo.entity.News;
import com.easytodo.entity.User;
import com.easytodo.service.NewsService;
import com.easytodo.util.MD5Util;
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

        Page page = new Page(currentPage, 1000);
        IPage pageData = newsService.page(page,
                new QueryWrapper<News>().orderByDesc(
                "publish_time").isNotNull("title"));

        return Result.succ(pageData);
    }

    @GetMapping("/list/{id}")
    public Result detail(@PathVariable(name = "id") Long id) {
        News news = newsService.getById(id);
        Assert.notNull(news, "This news have been deleted.");

        return Result.succ(news);
    }


    @PostMapping("/edit")
    public Result edit(News news) {

        News temp = newsService.getById(news.getId());

        if(temp != null){
            temp.setTitle(news.getTitle());
            temp.setContent(news.getContent());
            temp.setAuthor(news.getAuthor());
            temp.setPublishTime(news.getPublishTime());
            newsService.updateById(temp);
        }
        else{
            news.setId(newsService.count()+1);
            newsService.save(news);

        }
        return Result.succ(null);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable(name = "id") Integer id){
        boolean res = newsService.removeById(id);
        if(res){
            return Result.succ(0,"删除成功",null);
        }
        else{
            return Result.fail(1,"删除失败",null);
        }
    }

}
