# 분리수거 척척박사

### 프로젝트 동기
최근 코로나 19로 인해 비 대면 소비가 늘어났다. 식당에 가는 대신 배달음식을 시켜 먹고, 택배로 장을 보고, 카페 음료는 테이크아웃 하면서 포장재와 일회용품 사용 비율이 급증했다. 쓰레기 대란이 시작된 것이다.  

우리는 이 상황이 다소 의아할 수 있다. 매일같이 재활용을 위해 분리수거를 실천하기 때문이다. 우리나라는 아파트나 회사뿐만 아니라 공공 쓰레기통까지 어디든 분리수거 할 수 있는 환경이 꾸려져 있고, OECD 국가 중 재활용을 잘하는 국가 2위로 알려져 있기도 하다.  

하지만 문제는 실상은 그렇지 않다는 것이다. 우리나라 재활용 수치는 2019년 기준 약 86%라고 알려져 있는데, 이는 실질적인 재활용률이 아니다.  

올바른 방법으로 배출하지 않은 쓰레기가 많기 때문이다. 먹고 난 라면 용기를 어떻게 처리하여, 어디에 배출해야 하는지 헷갈리는 사람이 많은 것은 사실이다. 국내에서 버려지는 쓰레기 종량제 봉투 속을 살펴보면 약 70%는 재활용품으로 활용할 수 있는 자원이다. 분리배출을 올바르게 하지 않아 재활용될 수 있는 자원조차 소각되거나 매립되어 자원 부족과 환경오염으로 이어지고 있다. 재활용 가능한 자원을 종량제 봉투에 버리는 일만 막아도 연간 약 3천억 원 상당의 종량제 봉투 구매 비용을 절약할 수 있을 정도이다.  

따라서 “분리수거 척척박사”는 누구에게나 쉽게 분리수거 방식을 알려줌으로써 사람들의 올바른 재활용품 분류를 통해 환경보호에 일조하고자 한다.


### 주요 기능
1.	사용자가 휴대폰을 통해 이미지를 모델에 전달하면 모델은 분류 결과를 알려준다.
2.	사용자는 분리수거 카테고리를 통해 분리수거 하기 까다로운 물체의 분리수거 방식을 알 수 있다. 

### 핵심 시나리오
[방법 1] – 10가지의 재활용 쓰레기들을 직접 준비
1.	앱 실행 후 "사진 선택" 버튼을 클릭한다.
2.	재활용품 이미지를 업로드할 업로드 방식을 선택한다. 
(새로운 사진 촬영 or 앨범에서 불러오기)
3.	쓰레기 사진을 촬영하거나 앨범에서 쓰레기 사진을 업로드한다.
4.	업로드한 이미지의 분류 결과를 확인한다.
5.	"상세한 "상세한 분리수거 Tip 버튼을 클릭해 해당 항목에 대한 분리수거 방법을 확인한다.

[방법 2]
1.	앱 실행 후 “분리수거 Tip” 버튼을 클릭한다.
2.	정보를 원하는 분리수거 카테고리를 선택한다.
3.	해당 항목에 대한 분리수거 방법을 확인한다.

### 활용 도구
학습 시스템 : Google Colab / TensorFlow Lite 2.5.0 version / Android Studio   
인식 시스템 : Android phone (minSdkVersion 28 / targetSdkVersion 30)

## 향후 활용 방안
[자동 무인 쓰레기통 분류기]  
기존 프로젝트를 확장해 학습시킨 모델과 쓰레기통, 카메라를 분류설비와 결합하는 방식으로 활용한다면 자동 무인 쓰레기통 분류기를 만들 수 있다. 이러한 쓰레기 분류기를 미국과 같은 분리수거가 잘 이루어지지 않는 지역에서 활용할 경우, 자원을 절약하고 환경오염을 줄이는 에코 사회로 한발짝 더 다가 갈수 있다.

[교육 도구로 활용]  
유치원이나 초등학교 저학년 상대로 환경 교육 도구로 사용할 수 있다. 유치원생이나 초등학교 저학년을 대상으로 하는 교구로서 가장 중요한 것은 얼마나 아이들의 흥미를 끌 수 있는 지이다.
분리수거 척척박사 어플리케이션은 아이들이 휴대폰을 이용해 촬영하며 직접 분류결과를 확인할 수 있기 때문에 교구로서 흥미를 느낄 것이다.
또한, 시간이 지날수록 환경 문제가 대두되고 있기 때문에 분리수거 교육에 대한 중요성은 점점 올라가고 있다. 분리수거 척척박사 어플리케이션은 분리수거 방식을 알려주기 때문에 아이들은 분리수거 방법을 쉽게 배울 수 있다. 
