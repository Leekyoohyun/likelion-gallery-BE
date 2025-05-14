package likelion8.backend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Gallery {

    /*
    * @Id: id라는 컬럼을 기본키(primary key)로 설정
    * @GenerateValue: 인스턴스 생성 때, 기본키를 자동 생성
    * @GenerationType.IDENTITY: 기본키 생성을 데이터베이스에 위임
    * (다른 정책으로 SEQUENCE, TABLE 등) JPA 기본키 생성 전략 찾아보기
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//인스턴스 생성 때, 기본키를 자동으로 생성
    private Long id; //Integer보다 큰 범위 저장

    private String image; //이미지파일 경로

    private String title;

    private String description;

    private LocalDateTime lastUpdate; //최근 수정 시간
}
