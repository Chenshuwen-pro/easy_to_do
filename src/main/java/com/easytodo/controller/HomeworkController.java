package com.easytodo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easytodo.common.lang.Result;
import com.easytodo.entity.Course;
import com.easytodo.entity.Homework;
import com.easytodo.service.CourseService;
import com.easytodo.service.HomeworkService;
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
@RequestMapping("/homework")
public class HomeworkController {
    @Autowired
    HomeworkService homeworkService;

    @PostMapping("/add")
    public Result add(Integer userId, Homework homework){
        homework.setStudentId(userId);
        homework.setId(homeworkService.count()+1);
        boolean res = homeworkService.save(homework);
        if(res){
            return Result.succ(0,"添加家庭作业成功",null);
        }
        else{
            return Result.fail(1,"添加家庭作业失败",null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable(name = "id") Integer id){
        boolean res = homeworkService.removeById(id);
        if(res){
            return Result.succ(0,"删除家庭作业成功",null);
        }
        else{
            return Result.fail(1,"删除家庭作业失败",null);
        }
    }

    @PutMapping("/update")
    public Result update(Homework homework){
        boolean res = homeworkService.updateById(homework);
        if(res){
            return Result.succ(0,"更新家庭作业成功",null);
        }
        else{
            return Result.fail(1,"更新家庭作业失败",null);
        }
    }

    @GetMapping("/list/{id}")
    public Result getById(@PathVariable(name = "id") Integer id){
        Homework homework = homeworkService.getById(id);
        if(homework != null){
            return Result.succ(0,"查询家庭作业成功",homework);
        }
        else{
            return Result.fail(1,"查询家庭作业失败",null);
        }
    }

    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage) {

        Page page = new Page(currentPage, 5);
        IPage pageData = homeworkService.page(page,
                new QueryWrapper<Homework>().orderByDesc(
                        "date"));
        return Result.succ(pageData);
    }
}
