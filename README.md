# DI Container 만들어보기

※ 제작 전반에 관해 고민해본 내용들은 https://www.slideshare.net/JAEYOONLEE23/my-di-container 참고해주세요 

### 의존성(의존관계) 주입이 필요한 이유
- SRP, DIP, OCP를 지킨 좋은 설계를 가능하게 해주기 때문에 변화에 유연하게 대처할 수 있다.

### MyContainer 제공 기능
1.  AppConfig 파일에 등록된 메서드 기반으로 빈 등록 (싱글턴 패턴 사용하지 않고 하나의 인스턴스만 생성되도록 보장)
2. 등록된 빈 가져오기 (예외처리 : NoSuchBeanDefinitionException, NoUniqueBeanDefinitionException)
- 메서드 파라미터 : 빈 이름 or 빈 이름 & 클래스/인터페이스 타입 or 클래스/인터페이스 타입 
3. 등록된 빈 이름, 개수 조회 

### MyApp Flow
![image](https://user-images.githubusercontent.com/64415489/114221166-ea095380-99a7-11eb-9534-1920d7a1931d.png)
