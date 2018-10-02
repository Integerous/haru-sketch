package com.harusketch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication
public class Application {

	//외부에 있는 운영환경 yml 파일을 프로젝트가 호출할 수 있도록 코드 추가 및 변경
	public static final String APPLICATION_LOCATIONS = "spring.config.location="
			+ "classpath:application.yml,"
			+ "/app/config/haru-sketch/real-application.yml";
	
//	public static void main(String[] args) {
//		SpringApplication.run(Application.class, args);
//		System.out.println("====== SERVER STARTED ======");
//	}
	
	public static void main(String[] args)	{
		new SpringApplicationBuilder(Application.class)
				.properties(APPLICATION_LOCATIONS)
				.run(args);
	}
}
