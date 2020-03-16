package com.jojoldu.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class) //스프링부트테스트와 JUnit 사이에 연결자
@WebMvcTest
public class HelloControllerTest {

    @Autowired //스프링이 관리하는 빈(Bean)을 주입
    private MockMvc mvc; //웹 API 테스트,스프링 MVC 테스트의 시작점. 이클래스를 통해 http get post등 테스트

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) //http get 요청
                .andExpect(status().isOk()) //우리가 알고있는 200 404 500 상태 ok 200
                .andExpect(content().string(hello)); //본문내용 검증
    }@Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name) //param API 테스트할때 사용될 요청 파라미터를 설정. 단 값은 STRING. 숫자/날짜 등의 데이터도 등록할때 문자열로 변경해야 가능
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) //jsonPath json 응답값을 필드별로 검증할 수 있는 메소드. $을 기준으로 필드명 명시.
                .andExpect(jsonPath("$.amount", is(amount)));
    }


}
