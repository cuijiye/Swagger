package com.example.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * 配置Swagger的Docket的Bean实例
     * @return
     */
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .enable(getEnable()) //是否启用Swagger
                .groupName("组织机构")
                .select()
                //RequestHandlerSelectors, 配置要扫描接口的方式
                //basePackage, 指定要扫描的包
                .apis(RequestHandlerSelectors.basePackage("com.example.swagger.controller"))
                //ant 指定要扫描的路径
//                .paths(PathSelectors.ant("/hell1/**"))
                .build();
    }

    /**
     * 设置分组1
     * @return
     */
    @Bean
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(getEnable())
                .groupName("人员维护")
                .select()
                //RequestHandlerSelectors, 配置要扫描接口的方式
                //basePackage, 指定要扫描的包
                .apis(RequestHandlerSelectors.basePackage("com.example.swagger.controller1"))
                .build();
    }

    /**
     * 设置分组2
     * @return
     */
    @Bean
    public Docket docket2(){
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(getEnable())
                .groupName("通讯录");
    }

    /**
     * 配置Swagger的信息 apiInfo
     * @return
     */
    private ApiInfo apiInfo(){
        return new ApiInfo(
                "集成Swagger2",
                "方便前后端接口调用",
                "1.0",
                "https://swagger.io/",
                new Contact("沫殇", "https://swagger.io/", "cuijiye1993@163.com"),//作者信息
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }

    public boolean getEnable(){
        Properties props = null;
        InputStream is = null;
        Boolean enable = false;
        try {
            // 1.通过当前类加载器的getResourceAsStream方法获取
            // is =  Thread.currentThread().getContextClassLoader().getResourceAsStream("test.properties");

            // 2、从文件获取
            //is = new FileInputStream(new File(this.getClass().getResource("/test.properties").getPath()));

            // 3、也是通过类加载器来获取，和第一种一样
            // is = ClassLoader.getSystemResourceAsStream("test.properties");

            // 4.在servlet中，还可以通过context来获取InputStream
            // is = context.getResourceAsStream("filePath");

            // 5.通过URL来获取
            URL url = Thread.currentThread().getContextClassLoader().getResource("application.properties");
            is = url.openStream();

            if (is == null) {
                throw new FileNotFoundException("" + "file is not found");
            }
            props = new Properties();
            props.load(is);

            Iterator<String> it=props.stringPropertyNames().iterator();
            while(it.hasNext()){
                String key=it.next();
                System.out.println(key+":"+props.getProperty(key));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
        //获取key对应的value值
        String str = props.getProperty("swaggerEnable");
        if ("true".equals(str)){
            enable = true;
        }
        return enable;
    }
}
