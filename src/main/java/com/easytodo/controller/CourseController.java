package com.easytodo.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easytodo.common.lang.Result;
import com.easytodo.entity.Course;
import com.easytodo.entity.News;
import com.easytodo.entity.User;
import com.easytodo.service.CourseService;
import com.easytodo.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lheng
 * @since 2021-11-27
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @PostMapping("/add")
    public Result add(Integer userId, Course course){
        course.setStudentId(userId);
        course.setId(courseService.count()+1);
        boolean res = courseService.save(course);
        if(res){
            return Result.succ(0,"添加上课日程成功",null);
        }
        else{
            return Result.fail(1,"添加上课日程失败",null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable(name = "id") Integer id){
        boolean res = courseService.removeById(id);
        if(res){
            return Result.succ(0,"删除上课日程成功",null);
        }
        else{
            return Result.fail(1,"删除上课日程失败",null);
        }
    }

    @PutMapping("/update")
    public Result update(Course course){
        boolean res = courseService.updateById(course);
        if(res){
            return Result.succ(0,"更新上课日程成功",null);
        }
        else{
            return Result.fail(1,"更新上课日程失败",null);
        }
    }

    @GetMapping("/list/{id}")
    public Result getById(@PathVariable(name = "id") Integer id){
        Course course = courseService.getById(id);
        if(course != null){
            return Result.succ(0,"查询上课日程成功",course);
        }
        else{
            return Result.fail(1,"查询上课日程失败",null);
        }
    }

    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage) {

        Page page = new Page(currentPage, 5);
        IPage pageData = courseService.page(page,
                new QueryWrapper<Course>().orderByDesc(
                        "date"));
        return Result.succ(pageData);
    }
}
