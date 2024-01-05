package com.example.springinit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity //DB가 인식 가능한 객체 선언
@AllArgsConstructor //롬복을 사용해서 생성자 자동 생성
@NoArgsConstructor //인수 없는 디폴트 생성자 자동 생성
@ToString //롬복을 사용해서 toString 함수 대체
@Getter // 롬복을 통해 게터 생성
public class Article {

    @Id //대표값 설정, like a 주민등록번호
    @GeneratedValue // 1,2,3... 자동으로 생성하도록
    private Long id;
    @Column //열 설정
    private String title;
    @Column
    private String content;

}
