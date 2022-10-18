package com.tanjiali.blogadmin.controller.page.about;


import com.tanjiali.blogadmin.pojo.page.about.VO.AboutVO;
import com.tanjiali.blogadmin.service.page.about.AboutService;
import com.tanjiali.blogpublicapi.annotation.LoginCheck;
import com.tanjiali.blogpublicapi.annotation.OperaLog;
import com.tanjiali.blogpublicapi.api.PublicResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName AboutController
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/9/29 16:01
 * @Version 1.0
 **/
@RestController
@Api(value = "关于页面管理")
@RequestMapping("back/page")
@CrossOrigin
public class AboutController {
    @Autowired
    private AboutService aboutService;

    @ApiOperation("加载关于信息")
    @GetMapping("/about")
    public PublicResult<AboutVO> getAbout(){
        AboutVO aboutVO = aboutService.getAbout();
        if (aboutVO != null) {
            return PublicResult.success(aboutVO,"加载成功");
        }
        return PublicResult.failed(null);
    }

    @ApiOperation("修改关于信息")
    @OperaLog("修改关于信息")
    @PutMapping("/about")
    @LoginCheck("用户需要登录验证")
    public PublicResult<Boolean> updateAbout(@RequestBody AboutVO aboutVO){
        Boolean updateAbout = aboutService.updateAbout(aboutVO);
        if (updateAbout) {
            return PublicResult.success("修改成功");
        }
        return PublicResult.failed("修改失败");
    }
}
