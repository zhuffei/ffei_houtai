package zhuffei.ffei;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("zhuffei.ffei.mapper")
public class FfeiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FfeiApplication.class, args);
	}

}
