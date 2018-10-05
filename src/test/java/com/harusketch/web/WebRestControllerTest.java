//package com.harusketch.web;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//public class WebRestControllerTest {
//	
//	@Autowired
//	private TestRestTemplate restTemplate;
//	
//	@Test
//	public void Profile확인() {
//		//when
//		String profile = this.restTemplate.getForObject("/profile", String.class);
//		
//		//then
//		assertThat(profile).isEqualTo("local");
//		//test/resource/application.yml 파일에 spring.profile.active가 local로 되어있으므로 "local".
//		
//	}
//
//}
