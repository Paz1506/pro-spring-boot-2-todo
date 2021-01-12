package com.apress.spring.springbootsimple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

@SpringBootApplication
public class SpringBootSimpleApplication {

    public static void main(String[] args) {
        //	default
//        SpringApplication.run(SpringBootSimpleApplication.class, args);

        // p. 70
//		SpringApplication app = new SpringApplication(SpringBootSimpleApplication.class);
//        app.run();

        // p. 71
//		SpringApplication app = new SpringApplication(SpringBootSimpleApplication.class);
//		app.setBanner(new Banner() {
//			@Override
//			public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
//				out.print("\n\n\tThis is my own banner!\n\n".toUpperCase());
//			}
//		});
//		app.run();

        // p. 75 Disable the banner
//		SpringApplication app = new SpringApplication(SpringBootSimpleApplication.class);
//		app.setBannerMode(Banner.Mode.OFF);
//		app.run();

        // p. 76
        SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder();
        springApplicationBuilder.bannerMode(Banner.Mode.OFF)
                                .sources(SpringBootSimpleApplication.class);
//								.logStartupInfo(false)
//								.child(SomeClassImplementsEnvironment.class)
//								.profiles("dev")
//								.run(args);

        // p. 77
        Logger logger = LoggerFactory.getLogger(SpringBootSimpleApplication.class);
        springApplicationBuilder.listeners(new ApplicationListener<ApplicationEvent>() {
            @Override
            public void onApplicationEvent(ApplicationEvent applicationEvent) {
                logger.info("#### > " + applicationEvent.getClass().getCanonicalName());
            }
        }).web(WebApplicationType.NONE) // If we have not web app
								.run(args);
    }

}
