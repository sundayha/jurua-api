package com.jurua.api.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 张博【zhangb@lianliantech.cn】
 *
 * 配置Swagger2
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket juruaApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jurua.api"))
                .build()
                // 手动设置全局的参数，手动设置header中的token
                .globalOperationParameters(setHeaderToken())
                .apiInfo(apiInfo());
    }

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2017/10/23 上午10:38
     * @apiNote 添加swagger全局文档的参数，
     */
    private List<Parameter> setHeaderToken() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("Authorization").description("token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        return pars;
    }

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2017/10/23 上午10:37
     * @apiNote api 文档基础信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("南美亚马逊短鲷精灵")
                .description("群应用")
                .version("v0.1")
                .build();
    }
}
