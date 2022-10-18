package com.tanjiali.front.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanjiali.blogpublicapi.constant.SiteConstant;
import com.tanjiali.front.mapper.page.site.SiteSettingMapper;
import com.tanjiali.front.pojo.page.site.SiteSetting;
import com.tanjiali.front.pojo.page.site.VO.IndexSiteVO;
import com.tanjiali.front.pojo.page.site.dto.BadgeDTO;
import com.tanjiali.front.pojo.page.site.dto.CopyrightDTO;
import com.tanjiali.front.pojo.page.site.dto.Favorite;
import com.tanjiali.front.pojo.page.site.dto.Introduction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @ClassName
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/18 13:08
 * @Version 1.0
 **/
@Service
public class SiteSetServiceImpl extends ServiceImpl<SiteSettingMapper, SiteSetting> implements SiteSetService {
    private static final Pattern PATTERN = Pattern.compile("\"(.*?)\"");


    @Override
    public IndexSiteVO getSite() {
        IndexSiteVO indexSiteVO = new IndexSiteVO();
        List<SiteSetting> list = list();
        Map<String,Object> siteMap = new HashMap<>(8);
        Introduction introduction = new Introduction();
        List<Favorite> favorites = new ArrayList<>();
        List<String> rollTexts = new ArrayList<>();
        List<BadgeDTO> badgeDTOS = new ArrayList<>();
        list.stream().map(o->{
            int type = o.getType();
            switch (type) {
                case 1:
                    if (SiteConstant.COPYRIGHT.getValue().equals(o.getNameEn())) {
                        CopyrightDTO copyrightDTO = JSONObject.parseObject(o.getValue(), CopyrightDTO.class);
                        siteMap.put(o.getNameEn(), copyrightDTO);
                    }else {
                        siteMap.put(o.getNameEn(), o.getValue());
                    }
                    break;
                case 2:
                    String nameEn = o.getNameEn();
                    final String value = SiteConstant.AVATAR;
                    switch (nameEn) {
                        case value:
                            introduction.setAvatar(o.getValue());
                            break;
                        case SiteConstant.NAME:
                            introduction.setName(o.getValue());
                            break;
                        case SiteConstant.GITHUB:
                            introduction.setGithub(o.getValue());
                            break;
                        case SiteConstant.TELEGRAM:
                            introduction.setTelegram(o.getValue());
                            break;
                        case SiteConstant.QQ:
                            introduction.setQq(o.getValue());
                            break;
                        case SiteConstant.BILIBILI:
                            introduction.setBilibili(o.getValue());
                            break;
                        case SiteConstant.NETEASE:
                            introduction.setNetease(o.getValue());
                            break;
                        case SiteConstant.EMAIL:
                            introduction.setEmail(o.getValue());
                            break;
                        case SiteConstant.FAVORITE:
                            Favorite favorite = JSONObject.parseObject(o.getValue(), Favorite.class);
                            favorites.add(favorite);
                            break;
                        case SiteConstant.ROLL_TEXT:
                            Matcher m = PATTERN.matcher(o.getValue());
                            while (m.find()) {
                                rollTexts.add(m.group(1));
                            }
                            break;
                        default:
                            break;
                    }
                    o.getNameEn();
                    break;
                case 3:
                    BadgeDTO badgeDTO =  JSONObject.parseObject(o.getValue(), BadgeDTO.class);
                    badgeDTOS.add(badgeDTO);
                    break;
                default:
                    break;
            }
            return o;
        }).collect(Collectors.toList());
        introduction.setFavorites(favorites);
        introduction.setRollText(rollTexts);
        indexSiteVO.setSiteInfo(siteMap);
        indexSiteVO.setBadges(badgeDTOS);
        indexSiteVO.setIntroduction(introduction);
        return indexSiteVO;
    }
}
