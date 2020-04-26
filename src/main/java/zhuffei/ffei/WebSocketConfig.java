package zhuffei.ffei;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * 开启WebSocket支持，如果采用springboot内置容器启动项目的，则需要配置一个Bean。如果是采用外部的容器，则 不需要配置。
 * @author zhengkai
 */

/**
 * @author zhuwei
 * @version 1.0
 * @date 2020/4/26 15:42
 */
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }


}
