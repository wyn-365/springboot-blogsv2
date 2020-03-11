package com.wang.springboot.controller;


import com.wang.springboot.pojo.Type;
import com.wang.springboot.service.BlogService;
import com.wang.springboot.service.TagService;
import com.wang.springboot.service.TypeService;
import com.wang.springboot.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by 王一宁 on 2017/10/23.
 */
@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private TagService tagService;

    @GetMapping("/types/{id}")
    public String types(@PageableDefault(size = 10, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        @PathVariable Long id, Model model) {
        //拿到所有的分类
        List<Type> types = typeService.listTypeTop(10000);
        if (id == -1) {
           id = types.get(0).getId();
        }
        BlogQuery blogQuery = new BlogQuery();//查询对象
        blogQuery.setTypeId(id);//根据id查询
        model.addAttribute("types", types);
        model.addAttribute("tags",tagService.listTagTop(100));//标签
        model.addAttribute("recommendBlogs",blogService.listRecommendBlogTop(8));//推荐
        model.addAttribute("page", blogService.listBlog(pageable, blogQuery));
        model.addAttribute("activeTypeId", id);//传回去 用来解析变色的分类显示
        model.addAttribute("clickBlogs",blogService.listClickBlogTop(8));//点击
        return "list";
    }

    //分类的分页查询所有博客
    @GetMapping("/typelist")
    public String typelist(@PageableDefault(size = 10,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                           Model model){
        //拿到分页的数据
        model.addAttribute("page",blogService.listBlog(pageable));
        model.addAttribute("types",typeService.listTypeTop(1000));//分类
        model.addAttribute("tags",tagService.listTagTop(100));//标签
        model.addAttribute("recommendBlogs",blogService.listRecommendBlogTop(8));//推荐
        model.addAttribute("clickBlogs",blogService.listClickBlogTop(8));//点击
        return "list";
    }


}
