package ek.zhou.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@MapperScan("ek.zhou.springboot.mapper")
@SpringBootApplication
public class SpringBoot10BillApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot10BillApplication.class, args);
    }

}
