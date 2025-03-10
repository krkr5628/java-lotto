1. project_name : java-lotto

2. 공통 피드백 및 요구사항
   - 기능 목록 작성
   - 하드 코딩 금지(static final)
   - 구현 순서(상수, 클래스 변수, 인스턴스 변수 생성자, 메서드)
   - 한 함수에 한 가지 기능만 담당
   - 테스트를 작성 및 이유에 대해 본인의 경험을 토대로 정리
     + 큰 단위 테스트
     + 작은 단위 테스트
   - 들여쓰기 2개 이하로 한다.
   - 함수 라인은 15라인 이하
   - if 사용시 else 빼고 바로 return 사용하라
   - case when 불가
   - java enum 사용
   - 도메인 로직에 단위 테스트를 구현해야 한다.(UI 로직 제외)
     + 핵심 로직을 구현하는 코드와 UI를 담당하는 로직을 분리해 구현한다.
   - camp.nextstep.edu.missionutils의 Randoms 및 Console를 사용하라
     + 램덤 추출 : pickUniqueNumbersInRange()
     + 사용자 입력 : readLine()

3. 기능 요구 사항(자동)
   - 로또 번호 1 ~ 45 / 중복 X / 6개 숫자 및 보너스 1개 뽑기 
   - 구입 금액만큼 로또를 발행 / 1장 천원
   - 숫자 6개 및 보너스 1개 입력 받기
   - 당첨 내역 및 수익률 출력 / 이후 종료
   - 1등: 6개 번호 일치 / 2,000,000,000원
   - 2등: 5개 번호 + 보너스 번호 일치 / 30,000,000원
   - 3등: 5개 번호 일치 / 1,500,000원
   - 4등: 4개 번호 일치 / 50,000원
   - 5등: 3개 번호 일치 / 5,000원

4. 입력 조건
   - 천원단위로 받으며 백원이하 단위 절삭
   - 일반 당첨 번호는 한줄에 쉼표(,)를 통해서 한번에 받는다.
   - 보너스 번호는 다음줄에 받는다.

5. 출력 조건
   - 발행한 로또 수량을 출력한다. ('8개를 구매했습니다.')
   - 다음 각줄에 로또 번호를 오름차순으로 정렬하여 출력한다.
   - 당첨 내역을 출력한다.(5개 일치 보너스 볼 일치 (당첨긍액) - 개수)
   - 수익률을 소숫점 둘째 자리에서 올림해서 출력한다. (총 수익률은 62.5% 입니다.)
   - 예외 상황 시 에러 문구를 출력한다.
     (ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.)
      public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
      }

     private void validate(List<Integer> numbers) {
       if (numbers.size() != 6) {
         throw new IllegalArgumentException();**
       }
     }

6. Lotto 클래스
   - 제공된 Lotto 클래스만 사용
   - LOTTO클래스에 매개 변수 없는 생성자를 추가할 수 없다.
   - numbers의 접근 제어자인 private를 변경할 수 없다.
   - lotto클래스에 필드(인스턴스 변수)를 추가할 수 없다.
     + 인스턴스 변수 그냥 형태
     + 클래스 변수 static
   - lotto클래스의 패키지 변경은 가능하다.


7. 상세
   1) 기능 목록
       a) Application.java
           - 주 기능 출력
           - 구입 가격 숫자로만 이루어질시 다음 단계로 진입
       b) price.java
           - price() : 구입 금액 받기
           - check_price() : 구입 금액이 숫자로만 이루어지는지 확인
       c) quantity.java
           - quantity() : 총 발매한 로또 개수 파악
       d) Pick.java
           - Pick() : 발매 수 많큼 램덤 번호 반복 출력
           - pick_number : 램덤 수 6자리 뽑기
           - repeated() : 뽑힌 램덤 수에서 중복된 수 있는지 확인
       e) victory.java
           - victory() : 당첨번호 및 보너스번호 받기
       f) statistic.java
           - statistic() : 당첨번호 및 보너스번호 정수 변경 등
           - victory_count() : 발매한 로또 줄별 당첨 등수 확인
           - medal_result() : 등수별 당첨 수 및 총 수익률 출력
       g) Lotto.java(로또 번호 검수)
           - validate // 6자리 인가 확인
           - repeated // 반복값 확인
           - Over // 0미만 45초과 확인
        h) LottoTest.java(입력 번호 검수)
           - enum을 통해 입력값 관리
           - createLottoByOverSize // 뽑는 자리수 확인
           - createLottoByDuplicatedNumber // 중복 수 확인
           - createLottoByOverNumber // 0 ~ 45인지 확인
        i) ApplicationTest.java(메인 검사)
           - 기능_테스트 // 이상적인 값을 통한 정상 작동 확인
           - 예외_테스트 // 숫자가 아닌 문자 확인
           - 예외_테스트 // 1000원 미만의 구매 불능 확인
   
   2) 대략적인 구성(자동 로또 *줄과 시스템 담당자가 입력한 정답)
      - "구매금액을 입력해 주세요"
      - 구매 금액 입력
       + 잘못된 값을 입력할 시 에러 발생시 [ERROR]로 시작하는 에러 메시지 출력
      - 구매하는 로또 줄 수 계산 (금액 / 1,000)
      - "*개를 구매했습니다."
      - *개 만큼 숫자 6개 세트 받기
      - 각 세트 각 줄에 출력
      - "당첨 번호를 입력해 주세요."
      - 다음줄에 일반 번호 5개 입력(,를 기준으로 한줄에 입력)
        + 잘못된 값을 입력할 시 IllegalArgumentException 발생
        + 에러 발생시 [ERROR]로 시작하는 에러 메시지 출력
      - "보너스 번호를 입력해 주세요".
      - 다음줄에 보너스 번호 1개 입력.
        + 잘못된 값을 입력할 시 IllegalArgumentException 발생
        + 에러 발생시 [ERROR]로 시작하는 에러 메시지 출력
      - "당첨 통계"
      - "---"
      - 당첨 통계 각 줄에 출력
      - 수익률 출력(총 수익률은 62.5%입니다.)
      - 게임 종료
      
    3) 구현 불필요
        - 램덤 생성번호는 정수 0 ~ 45와 6개로 명령어에서 제어됨