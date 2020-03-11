package com.wang.springboot.controller.admin;

import com.wang.springboot.mapper.HeadMapper;
import com.wang.springboot.mapper.ProjectMapper;
import com.wang.springboot.pojo.Head;
import com.wang.springboot.pojo.Project;
import com.wang.springboot.pojo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author 王一宁
 * @date 2020/3/3 9:44
 */
@Controller
@RequestMapping("/admin")
public class ProjectAdminController {

    @Autowired
    HeadMapper headMapper;
    @Autowired
    ProjectMapper projectMapper;
    @GetMapping("/projects")
    public String list( Model model){
        List<Project> projectList = projectMapper.getProject();
        model.addAttribute("projectList",projectList);
        return "admin/projects";
    }

    //点击新增呢个进入到新增公告的页面
    @GetMapping("/projects/input")
    public String input(Model model) {
        model.addAttribute("type", new Type());
        return "admin/projects-input";
    }


    //新增公告
    @PostMapping("/projects")
    public String post(HttpServletRequest request) {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String img = request.getParameter("img");
        Project project = new Project();
        project.setTitle(title);
        project.setContent(content);
        project.setImg(img);
        projectMapper.save(project);
        return "redirect:/admin/projects";
    }


    //删除一个公告
    @GetMapping("/projects/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        projectMapper.delete(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/projects";
    }

}
