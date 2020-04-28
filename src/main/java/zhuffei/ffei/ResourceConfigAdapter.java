package zhuffei.ffei;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ResourceConfigAdapter implements WebMvcConfigurer {
 
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").
            addResourceLocations("file:"+System.getProperty("user.dir")+"\\src\\main\\resources\\static\\");
        registry.addResourceHandler("/tem/**").
            addResourceLocations("file:"+System.getProperty("user.dir")+"\\src\\main\\resources\\templates\\");
        //获取文件的真实路径 work_project代表项目工程名 需要更改
        String path1 = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\avator\\";
        String path2 = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\goodsImg\\";
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            registry.addResourceHandler("/avator/**").
                    addResourceLocations("file:"+path1);
            registry.addResourceHandler("/goodsImg/**").
                addResourceLocations("file:"+path2);
        }else{//linux和mac系统 可以根据逻辑再做处理
            registry.addResourceHandler("/avator/**").
                    addResourceLocations("file:/zhuffei/avator/");
            registry.addResourceHandler("/goodsImg/**").
                addResourceLocations("file:/zhuffei/goodsImg/");
        }
    }

}
