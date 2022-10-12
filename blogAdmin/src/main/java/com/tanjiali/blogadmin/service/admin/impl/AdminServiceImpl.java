package com.tanjiali.blogadmin.service.admin.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tanjiali.blogadmin.pojo.admin.CityVisitor;
import com.tanjiali.blogadmin.pojo.admin.dto.DashBoardDTO;
import com.tanjiali.blogadmin.pojo.admin.dto.RenderDTO;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanjiali.blogadmin.mapper.admin.AdminUserMapper;
import com.tanjiali.blogadmin.pojo.admin.Admin;
import com.tanjiali.blogadmin.pojo.admin.dto.ViDTO;
import com.tanjiali.blogadmin.pojo.admin.dto.VisitDTO;
import com.tanjiali.blogadmin.pojo.admin.vo.DashBoardVO;
import com.tanjiali.blogadmin.pojo.admin.vo.UserDTO;
import com.tanjiali.blogadmin.pojo.blog.Blog;
import com.tanjiali.blogadmin.pojo.blog.Category;
import com.tanjiali.blogadmin.pojo.blog.Tag;
import com.tanjiali.blogadmin.pojo.log.OperationLog;
import com.tanjiali.blogadmin.pojo.log.VisitLog;
import com.tanjiali.blogadmin.service.admin.AdminService;
import com.tanjiali.blogadmin.service.blog.BlogService;
import com.tanjiali.blogadmin.service.blog.CategoriesService;
import com.tanjiali.blogadmin.service.blog.CommentService;
import com.tanjiali.blogadmin.service.blog.TagService;
import com.tanjiali.blogadmin.service.log.OperationLogService;
import com.tanjiali.blogadmin.service.log.VisitLogService;
import com.tanjiali.blogadmin.service.statistics.VisitorService;
import com.tanjiali.blogpublicapi.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AdminServiceImpl extends ServiceImpl<AdminUserMapper, Admin> implements AdminService {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private VisitLogService visitLogService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private TagService tagService;
    @Autowired
    private VisitorService visitorService;

    @Override
    public UserDTO login(String name, String pwd) {
        UserDTO dto = new UserDTO();
        Admin admin = this.getOne(new QueryWrapper<Admin>().lambda()
                .eq(Admin::getUsername, name));
        if (admin == null) {
            dto.setToken("");
        }else {
            String token = jwtTokenUtil.generateUserNameStr(admin.getUsername(), admin.getPassword(), admin.getNickname());
            dto.setToken("admin:"+token);
        }
        dto.setUser(admin);
        return dto;
    }

    @Override
    public DashBoardVO dashboard() {
        DashBoardVO dto = new DashBoardVO();
        //统计PV量，查找访问日志
        log.info("开始从访问日志查询当日pv量-----------------------------------------------");
        QueryWrapper<VisitLog> visitLogQueryWrapper = new QueryWrapper<>();
        visitLogQueryWrapper.apply("date(create_time)=curdate()");
        int pv = visitLogService.count(visitLogQueryWrapper);
        log.info("结束查询当日pv量:("+pv+")-------------------------------------------------------");
        dto.setPv(pv);
        //统计UV量，查找Redis数据库信息---
        //todo 暂不做开发
        log.info("开始从Redis查询当日uv量-----------------------------------------------");
        log.info("结束查询当日uv量:("+20+")-------------------------------------------------------");
        dto.setUv(20);
        //统计博客文章数量
        log.info("开始从文章表查询文章数量-----------------------------------------------");
        int blogCount = blogService.count();
        log.info("结束查询文章量:("+blogCount+")-------------------------------------------------------");
        dto.setBlogCount(blogCount);
        //统计评论数量
        log.info("开始从评论表查询评论数量-----------------------------------------------");
        int commentCount = commentService.count();
        log.info("结束查询评论量:("+commentCount+")-------------------------------------------------------");
        dto.setCommentCount(commentCount);

        //分类统计渲染数据
        log.info("分类数据渲染开始-----------------------------------------------");
        RenderDTO category = new RenderDTO();
        //查询分类数据
        List<Category> categories = categoriesService.list();
        ArrayList<String> categoryLegendList = new ArrayList<>();
        categories.stream().map(o->{
            categoryLegendList.add(o.getName());
            return o;
        }).collect(Collectors.toList());
        category.setLegend(categoryLegendList);
        //查询分类下有多少文章
        List<DashBoardDTO> categoryBlogCountList = blogService.getCategoryBlogCountList();
        category.setSeries(categoryBlogCountList);
        log.info("分类数据渲染完成:"+category+"-----------------------------------------------");
        dto.setCategory(category);

        //标签统计渲染数据
        log.info("标签数据渲染开始-----------------------------------------------");
        RenderDTO tag = new RenderDTO();
        //查询标签数据
        List<Tag> tags = tagService.list();
        ArrayList<String> tagLegendList = new ArrayList<>();
        tags.stream().map(o->{
            tagLegendList.add(o.getName());
            return o;
        }).collect(Collectors.toList());
        tag.setLegend(tagLegendList);
        //查询标签下有多少文章
        List<DashBoardDTO> tagBlogCountList = tagService.getTagBlogCountList();
        tag.setSeries(tagBlogCountList);
        log.info("标签数据渲染完成:"+tag+"-----------------------------------------------");
        dto.setTag(tag);

        //渲染一周访问量数据
        log.info("周访问量数据渲染开始-----------------------------------------------");
        List<VisitDTO> visitDTO = visitorService.getVisitRecordList(7);
        //封装数据渲染
        List<String> date1 = new ArrayList<>();
        List<Integer> pv1 = new ArrayList<>();
        List<Integer> uv1 = new ArrayList<>();
        ViDTO viDTO = new ViDTO();
        visitDTO.stream().map(o->{
            date1.add(o.getDate());
            pv1.add(o.getPv());
            uv1.add(o.getUv());
            return o;
        }).collect(Collectors.toList());
        viDTO.setDate(date1);
        viDTO.setPv(pv1);
        viDTO.setUv(uv1);
        log.info("周访问量数据渲染完成:"+viDTO+"-----------------------------------------------");
        dto.setVisitRecord(viDTO);

        //查询访问者城市
        List<CityVisitor> cityVisitorList = visitorService.getCityVisitorList();
        dto.setCityVisitor(cityVisitorList);
        return dto;
    }
}
