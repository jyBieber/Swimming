package com.it.swim.web.home;

import com.it.swim.util.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

/*
 * @description: 图片加载相关类-登录界面
 */
@Controller
@RequestMapping("/imageUpload/item")
public class ImageController {


    /*
     * 会员主界面路由
     */
    @RequestMapping(value = "/{type}/{id}/{file:.+}", method = RequestMethod.GET)
    private void vipIndex(@PathVariable("type") String type, @PathVariable("id") String id, @PathVariable("file") String file, HttpServletResponse response) throws Exception {
        FileUtils.downLoad("/" + type + "/" + id + "/" + file, response, true);
    }


}
