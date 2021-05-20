# RecyclerView
* 안드로이드에서 사용하는 리스트 뷰의 장,단점을 보완한 고급 위젯.
* __한정된 수의 뷰를 유지해서__ 매우 효율적으로 스크롤 할 수 있는 큰 데이터 세트를 표시하기 위한 컨테이너
* RecyclerView 클래스와 함께 사용하는 주요 클래스

    |클래스 이름|설명|
    |---|---|
    |Adapter|데이터 세트의 아이템을 나타내는 뷰를 생성|
    |ViewHolder|재활용 뷰에 대한 모든 서브 뷰를 저장|
    |LayoutManager|뷰에 있는 아이템을 배치하고 관리|
    |ItemDecoration|아이템을 꾸미는 서브 뷰를 제어|
    |ItemAnimation|아이템을 추가, 정렬, 제거할 때 애니메이션 효과를 줌|
## Adapter
* ViewHolder 클래스를 이용한 데이터 세트의 정의에 따라 UI 를 선택해 보여줌
* 구현해야하는 메서드
    1. __onCreateViewHolder(ViewGroup parent, int viewType)__: ViewHolder를 생성하고 뷰를 붙여주는 부분
    2. __onBindViewHolder(ListItemViewHolder holder, int position)__: 재활용하는 뷰를 호출하여 실행하는 메서드
    3. __getItemCount()__: 데이터의 개수를 반환