package cn.whb.java.controller;


import cn.whb.java.ehcache.ContentCountCache;
import cn.whb.java.util.UserAgentUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/newView")
public class MainController {
    private final static Logger logger = LoggerFactory.getLogger(MainController.class);
    @Autowired
    private ContentCountCache contentCountCache;
    @Value("#{myproperties['pc']}")
    private String PC ;
    @Value("#{myproperties['app']}")
    String APP ;
    @Value("#{myproperties['appShare']}")
    String APPSHARE ;
    @Value("#{myproperties['jinri']}")
    String JINRI ;
    @Value("#{myproperties['uc']}")
    String UC ;
    @Value("#{myproperties['zaker']}")
    String ZAKER ;
    @Value("#{myproperties['yidian']}")
    String YIDIAN ;


    @RequestMapping("/contentView")
    @ResponseBody
    public String contentView(Integer contentId, HttpServletRequest request,
                            HttpServletResponse response, String page)  {
        logger.info("contentView...");
        if (contentId == null) {
            return "false";
        }
        String thirdSource= UserAgentUtils.getThirdSource(page);
        if ("APP分享".equals(thirdSource)) {
            if("0".equals(APPSHARE)){
                return "shutdown";
            }

        } else if ("PC网站".equals(thirdSource)) {
            if("0".equals(PC)){
                return "shutdown";
            }
        } else if ("今日头条".equals(thirdSource)) {
            if("0".equals(JINRI)){
                return "shutdown";
            }
        } else if ("UC头条".equals(thirdSource)) {
            if("0".equals(UC)){
                return "shutdown";
            }
        } else if ("一点资讯".equals(thirdSource)) {
            if("0".equals(YIDIAN)){
                return "shutdown";
            }
        } else if ("ZAKER新闻".equals(thirdSource)) {
            if("0".equals(ZAKER)){
                return "shutdown";
            }
        } else if ("APP".equals(thirdSource)) {
            if("0".equals(APP)){
                return "shutdown";
            }
        }



        int[] counts = contentCountCache.viewAndGet(contentId,thirdSource);
        logger.info("统计OK");
        //栏目访问量计数
        return "true";
    }


    @RequestMapping("/json")
    @ResponseBody
    public String json(){
        //System.out.println("test");
        return "2";
    }



}
