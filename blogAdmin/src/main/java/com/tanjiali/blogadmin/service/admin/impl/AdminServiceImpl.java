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
        //??????PV????????????????????????
        log.info("?????????????????????????????????pv???-----------------------------------------------");
        QueryWrapper<VisitLog> visitLogQueryWrapper = new QueryWrapper<>();
        visitLogQueryWrapper.apply("date(create_time)=curdate()");
        int pv = visitLogService.count(visitLogQueryWrapper);
        log.info("??????????????????pv???:("+pv+")-------------------------------------------------------");
        dto.setPv(pv);
        //??????UV????????????Redis???????????????---
        //todo ???????????????
        log.info("?????????Redis????????????uv???-----------------------------------------------");
        log.info("??????????????????uv???:("+20+")-------------------------------------------------------");
        dto.setUv(20);
        //????????????????????????
        log.info("????????????????????????????????????-----------------------------------------------");
        int blogCount = blogService.count();
        log.info("?????????????????????:("+blogCount+")-------------------------------------------------------");
        dto.setBlogCount(blogCount);
        //??????????????????
        log.info("????????????????????????????????????-----------------------------------------------");
        int commentCount = commentService.count();
        log.info("?????????????????????:("+commentCount+")-------------------------------------------------------");
        dto.setCommentCount(commentCount);

        //????????????????????????
        log.info("????????????????????????-----------------------------------------------");
        RenderDTO category = new RenderDTO();
        //??????????????????
        List<Category> categories = categoriesService.list();
        ArrayList<String> categoryLegendList = new ArrayList<>();
        categories.stream().map(o->{
            categoryLegendList.add(o.getName());
            return o;
        }).collect(Collectors.toList());
        category.setLegend(categoryLegendList);
        //??????????????????????????????
        List<DashBoardDTO> categoryBlogCountList = blogService.getCategoryBlogCountList();
        category.setSeries(categoryBlogCountList);
        log.info("????????????????????????:"+category+"-----------------------------------------------");
        dto.setCategory(category);

        //????????????????????????
        log.info("????????????????????????-----------------------------------------------");
        RenderDTO tag = new RenderDTO();
        //??????????????????
        List<Tag> tags = tagService.list();
        ArrayList<String> tagLegendList = new ArrayList<>();
        tags.stream().map(o->{
            tagLegendList.add(o.getName());
            return o;
        }).collect(Collectors.toList());
        tag.setLegend(tagLegendList);
        //??????????????????????????????
        List<DashBoardDTO> tagBlogCountList = tagService.getTagBlogCountList();
        tag.setSeries(tagBlogCountList);
        log.info("????????????????????????:"+tag+"-----------------------------------------------");
        dto.setTag(tag);

        //???????????????????????????
        log.info("??????????????????????????????-----------------------------------------------");
        List<VisitDTO> visitDTO = visitorService.getVisitRecordList(7);
        //??????????????????
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
        log.info("??????????????????????????????:"+viDTO+"-----------------------------------------------");
        dto.setVisitRecord(viDTO);

        //?????????????????????
        List<CityVisitor> cityVisitorList = visitorService.getCityVisitorList();
        dto.setCityVisitor(cityVisitorList);
        return dto;
    }
}
