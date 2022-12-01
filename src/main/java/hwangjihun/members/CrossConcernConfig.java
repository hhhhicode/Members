package hwangjihun.members;

import hwangjihun.members.crossconcern.ExceptionInterceptor;
import hwangjihun.members.crossconcern.LogInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CrossConcernConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(logInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/js/**",
                        "/*.ico", "/error");

        registry.addInterceptor(exceptionInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/js/**",
                        "/*.ico", "/error");
    }

    @Bean
    public LogInterceptor logInterceptor() {
        return new LogInterceptor();
    }

    @Bean
    public ExceptionInterceptor exceptionInterceptor() {
        return new ExceptionInterceptor();
    }
}
