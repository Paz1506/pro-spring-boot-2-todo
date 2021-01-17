package com.apress.spring.springbootsimple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@SpringBootApplication
public class SpringBootSimpleApplication
    // p.80
        implements ApplicationRunner, CommandLineRunner {

    public static void main(String[] args) {
        //	default
        SpringApplication.run(SpringBootSimpleApplication.class, args);

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
//        SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder();
//        springApplicationBuilder.bannerMode(Banner.Mode.OFF)
//                                .sources(SpringBootSimpleApplication.class);
////								.logStartupInfo(false)
////								.child(SomeClassImplementsEnvironment.class)
////								.profiles("dev")
////								.run(args);
//
//        // p. 77
//        Logger logger = LoggerFactory.getLogger(SpringBootSimpleApplication.class);
//        springApplicationBuilder.listeners(new ApplicationListener<ApplicationEvent>() {
//            @Override
//            public void onApplicationEvent(ApplicationEvent applicationEvent) {
//                logger.info("#### > " + applicationEvent.getClass().getCanonicalName());
//            }
//        }).web(WebApplicationType.NONE) // If we have not web app
//								.run(args);


    }

    // p.78 Command arguments
    Logger log = LoggerFactory.getLogger(SpringBootSimpleApplication.class);

    @Autowired
    String info;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("## > ApplicationRunner impl ...: " + info);

    }

    @Override
    public void run(String... args) throws Exception {
        log.info("## > CommandLineRunner impl ...: " + info);
    }

    @Bean
    CommandLineRunner myMethod() {
        return args -> {
            log.info("## > CommandLineRunner impl 2 ...: ");
        } ;
    }

    @Component
    class MyComponent {

        @Autowired
        public MyComponent(ApplicationArguments arguments) {
            if (arguments.containsOption("enable")) {
                log.info("!!! You are enabled !!!");
            }

            List<String> nonOptionArgs = arguments.getNonOptionArgs();
            log.info("## > extra args ...");
            if (!nonOptionArgs.isEmpty()) {
                nonOptionArgs.forEach(arg -> log.info("## > {}", arg));
            }
        }
    }

    // ApplicationRunner & CommandLineRunner
    @Bean
    public String info() {
        return "Just a simple String bean";
    }
}
