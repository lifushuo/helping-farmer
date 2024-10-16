package com.sky;


import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import springfox.documentation.annotations.Cacheable;

@SpringBootApplication
@EnableTransactionManagement //开启注解方式的事务管理
@Slf4j
@EnableCaching//开启缓存注解功能
@EnableScheduling
@MapperScan("com.sky.dao")
public class SkyApplication {
    public static void main(String[] args) {
        SpringApplication.run(SkyApplication.class, args);
        log.info("server started");
    }

//    @Bean
//    MybatisPlusInterceptor mybatisPlusInterceptor() {
//        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
//
//        // 分页插件
//        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
//
//        // 乐观锁
//        mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
//
//        // 防止全表 被删除或者修改
//        mybatisPlusInterceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
//
//        return mybatisPlusInterceptor;
//    }

}
