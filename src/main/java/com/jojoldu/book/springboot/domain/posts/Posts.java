package com.jojoldu.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter //lombok
@NoArgsConstructor //lombok 기본생성자 자동추가 public Posts(){}와 가튼 효과
@Entity //JPA 테이블과 링크될 클래스.기본값으로 클래스의 카멜케이스 이름을 언더스코어네이밍으로 테이블 이름을 매칭 ex) SalesManager.java -> sales_manager table
public class Posts extends BaseTimeEntity {

    @Id //해당 테이블의 PK 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK 생성규칙.추가해야 AUTO_INCREMENT 가 된다. 왠만하면 PK는 LONG 타입추천
    private Long id;

    @Column(length = 500, nullable = false) //테이블의 칼럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼. 사용하는 이유는 기본값 외에 추가로 변경이 필요한 옵선에 있으면
    //문자열의 경우 VARCHAR(255)가 기본값인데 사이즈를 500으로 늘리고 싶거나 타입을 TEXT 로 변경하고 싶거나 등의 경우의 사용
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder //생성자 상단에 선언 시 생성자에 포함된 필드만 빌더의 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
    //Entity 클래스에서는 절대 Setter 메소드를 만들지 않는다.
    //Setter 가 없는 상황에서 어떻게 값을 채워 DB에 삽입하는가? 기본적인 구조는 생성자를 통해 최종값을 채운 후 DB 에 삽입 하는것이며 값 변경이 필요한 경우 해당 이벤트에 맞는
    //public 메소드를 호출하여 변경하는 것을 전제 . 생성자 대신에 @Builder 를 통해 제공되는 빌더 클래스를 사용한다. 생성자나 빌더나 생성 시점에 값을 채워주는 역할은 똑같다.
    //다만 생성자의 경우 지금 채워야 할 필드가 무엇인지 명확히 지정할 수 없다. 파라미터 값의 위치를 변경해도 코드를 실행하기 전까지는 문제를 찾을 수 없기 때문이다. 하지만
    // Builder 를 사용하게 되면 어느 필드에 어떤값을 채워야할지 명확하게 인지 가능.

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
