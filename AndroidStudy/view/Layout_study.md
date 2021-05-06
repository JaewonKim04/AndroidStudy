# Layout
## ConstraintLayout
* 위젯들 사이에 간단한 제약조건을 설정해서 화면을 구성하는 레이아웃
* 핸들러:UI편집기에서 다른 위젯과의 제약조건을 설정할때 사용할수있는 동그라미
* 컨스트레인트 편집기: 속성 영역에서 컨스트레인트 속성을 정할수있는 편집기
* 가이드라인:컨스트레인트 레이아웃에만 사용할 수 있는 보조도구
## LinearLayout
* 위젯을 가로 또는 세로 한 줄로 배치하기 위한 레이아웃
* orientation: vaertical-세로, horizontal-가로
* layout_weight: 해당 위젯이 레이아웃안에서 차지할 비율을 정함 (__layout_width또는layout_height을 0dp로 해야함__)
* gravity:뷰 안에있는 글씨같은것의 위치를 정함
* layout_gravity:레이아웃안에 있는 뷰의 위치를 정함
* space: 뷰사이에 일정한 간격을 두고싶을때 사용
## FrameLayout
* 위젯을 중첩해서 사용하기 위한 레이아웃
* 처리속도가 가장 빠름
* layout_gravity로 정렬가능