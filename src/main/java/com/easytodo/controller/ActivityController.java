package com.easytodo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easytodo.common.lang.Result;
import com.easytodo.entity.Activity;
import com.easytodo.entity.Course;
import com.easytodo.service.ActivityService;
import com.easytodo.service.CourseService;
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
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    ActivityService activityService;

    @PostMapping("/add")
    public Result add(Integer userId, Activity activity){
        activity.setStudentId(userId);
        activity.setId(activityService.count()+1);
        boolean res = activityService.save(activity);
        if(res){
            return Result.succ(0,"添加活动日程成功",null);
        }
        else{
            return Result.fail(1,"添加活动日程失败",null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable(name = "id") Integer id){
        boolean res = activityService.removeById(id);
        if(res){
            return Result.succ(0,"删除活动日程成功",null);
        }
        else{
            return Result.fail(1,"删除活动日程失败",null);
        }
    }

    @PutMapping("/update")
    public Result update(Activity activity){
        boolean res = activityService.updateById(activity);
        if(res){
            return Result.succ(0,"更新活动日程成功",null);
        }
        else{
            return Result.fail(1,"更新活动日程失败",null);
        }
    }

    @GetMapping("/list/{id}")
    public Result getById(@PathVariable(name = "id") Integer id){
        Activity activity = activityService.getById(id);
        if(activity != null){
            return Result.succ(0,"查询活动日程成功",activity);
        }
        else{
            return Result.fail(1,"查询活动日程失败",null);
        }
    }

    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage) {

        Page page = new Page(currentPage, 5);
        IPage pageData = activityService.page(page,
                new QueryWrapper<Activity>().orderByDesc(
                        "date"));
        return Result.succ(pageData);
    }

}
