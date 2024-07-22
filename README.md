<hr>
<h3>메타정보관리시스템</h3>
<hr>
<h3>목적</h3>
<ul>
    <li>효율적인 프로젝트를 위한 메타정보(도메인) 조회 및 관리</li>
    <li>DB 오브젝트 생성 및 관리</li>
</ul>
<br>
<h3>기능상세</h3>
<ul>
    <li>테이블 생성</li>
    <li>컬럼명 수정</li>
    <li>컬럼에 대한 도메인 조회</li>
    <li>컬럼 영어 변수명 조회(스네이크, 카멜)</li>
</ul>
<br>
<h3>사용기술 및 컨셉</h3>
<ul>
    <li>스프링부트 2.7, 스프링 시큐리티, JSP, postgresql</li>
    <li>
        <div>SQL인터셉터와 Handler 인터셉터를 활용한 로깅</div>
        <div>- 바인딩 파라미터, 변수바인딩한 SQL 로깅</div>
        <div>- URL을 핸들러 식별자로 사용하기 위해 영문코드와 숫자코드로 결합한 짧은 URL방식 사용</div>
    </li>
    <li>엑셀업로드를 활용한 대량 데이터수정</li>
    <li>공통모델로 활용하기 위한 커스텀맵 정의</li>
    <li>정상응답과 실패응답 구분하기 위한 커스텀컨트롤러 정의</li>
    <li>예외코드와 예외메시지 변수바인딩하는 커스텀 예외 정의</li>
    <li>도메인별로 폴더구조 분리</li>
</ul>
<br>
<h3>화면단 구분</h3>
<ul>
    <li>버전1: jsp (레포지터리 >>> custom_meta)</li>
    <li>버전2: react (레포지터리 >>> custom_meta_react)</li>
</ul>
