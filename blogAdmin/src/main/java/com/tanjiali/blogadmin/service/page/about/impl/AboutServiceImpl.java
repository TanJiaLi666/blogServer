package com.tanjiali.blogadmin.service.page.about.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanjiali.blogadmin.mapper.page.about.AboutMapper;
import com.tanjiali.blogadmin.pojo.page.about.About;
import com.tanjiali.blogadmin.pojo.page.about.VO.AboutVO;
import com.tanjiali.blogadmin.service.page.about.AboutService;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/9/29 17:58
 * @Version 1.0
 **/
@Service
public class AboutServiceImpl extends ServiceImpl<AboutMapper, About> implements AboutService {

    @Override
    public AboutVO getAbout() {
        AboutVO vo = new AboutVO();
        List<About> abouts = this.list();
        for (About about:
                abouts) {
            if ("title".contains(about.getNameEn())) {
                vo.setTitle(about.getValue());
            }
            if ("musicId".contains(about.getNameEn())) {
                vo.setMusicId(about.getValue());
            }
            if ("content".contains(about.getNameEn())) {
                vo.setContent(about.getValue());
            }
            if ("commentEnabled".contains(about.getNameEn())) {
                vo.setCommentEnabled(about.getValue());
            }
        }
        return vo;
    }

    @Override
    @Transient
    public Boolean updateAbout(AboutVO aboutVO) {

        if (!StrUtil.isBlank(aboutVO.getTitle())) {
            UpdateWrapper<About> wrapper = new UpdateWrapper<>();
            LambdaUpdateWrapper<About> lambda = wrapper.lambda();
            lambda.set(About::getValue,aboutVO.getTitle())
                    .eq(About::getNameEn,"title");
            update(lambda);
        }
        if (!StrUtil.isBlank(aboutVO.getContent())) {
            UpdateWrapper<About> wrapper = new UpdateWrapper<>();
            LambdaUpdateWrapper<About> lambda = wrapper.lambda();
            lambda.set(About::getValue,aboutVO.getContent())
                    .eq(About::getNameEn,"content");
            update(lambda);
        }
        if (!StrUtil.isBlank(aboutVO.getMusicId())) {
            UpdateWrapper<About> wrapper = new UpdateWrapper<>();
            LambdaUpdateWrapper<About> lambda = wrapper.lambda();
            lambda.set(About::getValue,aboutVO.getMusicId())
                    .eq(About::getNameEn,"musicId");
            update(lambda);
        }
        if (!StrUtil.isBlank(aboutVO.getCommentEnabled())) {
            UpdateWrapper<About> wrapper = new UpdateWrapper<>();
            LambdaUpdateWrapper<About> lambda = wrapper.lambda();
            lambda.set(About::getValue,aboutVO.getCommentEnabled())
                    .eq(About::getNameEn,"commentEnabled");
            update(lambda);
        }
        return true;
    }
}
