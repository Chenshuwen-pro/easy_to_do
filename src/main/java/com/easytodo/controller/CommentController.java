package com.easytodo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easytodo.common.lang.Result;
import com.easytodo.entity.Comment;
import com.easytodo.entity.News;
import com.easytodo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
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
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("/list/{nid}")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage,
                       @PathVariable(name = "nid")Integer nid) {

        Page page = new Page(currentPage, 5);
        IPage pageData = commentService.page(page,
                new QueryWrapper<Comment>().eq("nid",nid).orderByDesc(
                        "createTime"));

        return Result.succ(pageData);
    }

    @GetMapping("/list/detail/{id}")
    public Result detail(@PathVariable(name = "id")Integer id) {
        Comment comment= commentService.getById(id);
        Assert.notNull(comment, "This comment have been deleted.");

        return Result.succ(comment);
    }


    @PostMapping("/edit")
    public Result edit(@RequestBody Comment comment) {

        Comment temp = commentService.getById(comment.getId());

        if(temp != null){
            temp.setCreateTime(comment.getCreateTime());
            temp.setNickname(comment.getNickname());
            temp.setNid(comment.getNid());
            temp.setComment(comment.getComment());
            commentService.updateById(temp);
        }
        else{
            comment.setId(commentService.count()+1);
            commentService.saveOrUpdate(comment);

        }
        return Result.succ(null);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable(name = "id") Integer id){
        boolean res = commentService.removeById(id);
        if(res){
            return Result.succ(0,"删除成功",null);
        }
        else{
            return Result.fail(1,"删除失败",null);
        }
    }
}
