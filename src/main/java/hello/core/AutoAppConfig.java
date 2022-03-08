package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // basePackages = "hello.core.member",
        basePackages = "hello.core",
        basePackageClasses = AutoAppConfig.class, // default base : hello.core
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) // Configuration 어노테이션이 붙어있으면 exclude
        // AppConfig를 exclude 함
)
public class AutoAppConfig {

}
