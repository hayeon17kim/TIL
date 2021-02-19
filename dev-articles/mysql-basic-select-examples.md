### group by

특정 row를 하나의 row로 grouping 하여 어떤 통계적인 데이터로 표현할 수 있다.

**회차별로 몇 건의 데이터가 있는 지 확인하기**

```sql
select exam_seq, count(*)
from class.exam_result
group by exam_seq;
```

`class.exam_result` 테이블의 데이터 중 `exam_seq`별로 그룹핑하여 각 `exam_seq`변 건수를 출력하라.

**회차별 수학 성적의 평균값 구해보기**

```js
select exam_seq, avg(math)
from class.exam_result
group by exam_seq;
```

`class.exam_result` 테이블의 데이터 중 `exam_seq`별로 그룹핑하여 각 `exam_seq`별 math(수학 성적)의 평균을 출력하라.

