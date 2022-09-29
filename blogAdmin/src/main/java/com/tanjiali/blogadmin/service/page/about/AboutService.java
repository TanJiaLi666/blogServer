package com.tanjiali.blogadmin.service.page.about;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tanjiali.blogadmin.pojo.page.about.About;
import com.tanjiali.blogadmin.pojo.page.about.VO.AboutVO;

/**
 * @ClassName AboutService
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/9/29 17:57
 * @Version 1.0
 **/
public interface AboutService extends IService<About> {
    /**
     * 加载关于页信息
     * @return
     */
    AboutVO getAbout();

    /**
     * 修改信息
     * @param aboutVO
     * @return
     */
    Boolean updateAbout(AboutVO aboutVO);
}
