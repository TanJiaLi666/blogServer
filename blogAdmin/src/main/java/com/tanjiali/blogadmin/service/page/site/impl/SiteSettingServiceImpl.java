package com.tanjiali.blogadmin.service.page.site.impl;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanjiali.blogadmin.mapper.page.site.SiteSettingMapper;
import com.tanjiali.blogadmin.pojo.page.site.SiteSetting;
import com.tanjiali.blogadmin.pojo.page.site.VO.SiteSettingInVO;
import com.tanjiali.blogadmin.pojo.page.site.VO.SiteSettingVO;
import com.tanjiali.blogadmin.service.page.site.SiteSettingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/9/30 15:58
 * @Version 1.0
 **/
@Service
@Slf4j
public class SiteSettingServiceImpl extends ServiceImpl<SiteSettingMapper, SiteSetting> implements SiteSettingService {
    @Override
    public SiteSettingVO getSiteSettingData() {
        List<SiteSetting> list = this.list();
        SiteSettingVO vo = new SiteSettingVO();
        ArrayList<SiteSetting> list1 = new ArrayList<>();
        ArrayList<SiteSetting> list2 = new ArrayList<>();
        ArrayList<SiteSetting> list3 = new ArrayList<>();
        list.stream().map(o->{
            switch (o.getType()) {
                case 1:
                    list1.add(o);
                    break;
                case 2:
                    list2.add(o);
                    break;
                case 3:
                    list3.add(o);
                    break;
                default:
                    return o;
            }
            return o;
        }).collect(Collectors.toList());
        vo.setType1(list1);
        vo.setType2(list2);
        vo.setType3(list3);
        return vo;
    }

    @Override
    @Transactional
    public Boolean updateSiteSettings(SiteSettingInVO inVO) {
        log.info("删除id："+inVO.getDeleteIds());
        this.removeByIds(inVO.getDeleteIds());
        log.info("修改站点信息："+inVO.getSettings());
        this.saveOrUpdateBatch(inVO.getSettings());
        return true;
    }
}
