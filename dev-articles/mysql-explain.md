https://cheese10yun.github.io/mysql-explian/

### MySQL 옵티마이저 구조

비용 기반으로 어떤 실행계획으로 쿼리를 실행햇을 때 비용이 얼마나 발생하는지를 계산해 비용이 가장 적은 것을 택한다. 

### EXPLAIN

**MySQL 서버가 어떠한 쿼리를 실행할 것인가**, 즉 **실행 계획이 무엇인지 알고 싶을 때 사용**하는 기본적인 명령어이다. 

### EXPLAIN 결과

```ㅓㄴ
explain
select m.*, o.*, t.* from member m
inner join orders o on m.id=o.member_id
inner join transaction t on o.transaction_id = t.id
where m.id in (1, 2, 33)
```

| id   | select_type | table | partitions | type     | possible_keys | key        | key_len | ref                                  | rows | filtered | Extra         |
| :--- | :---------- | :---- | :--------- | :------- | :------------ | :--------- | :------ | :----------------------------------- | :--- | :------- | :------------ |
| ‘1’  | ‘SIMPLE’    | ‘m’   | NULL       | ‘range’  | ‘PRIMARY’     | ‘PRIMARY’  | ‘8’     | NULL                                 | ‘3’  | ‘100.00’ | ‘Using where’ |
| ‘1’  | ‘SIMPLE’    | ‘o’   | NULL       | ‘ref’    | ‘FKpktxw…’    | ‘FKpktxw…’ | ‘8’     | ‘[sample.m.id](http://sample.m.id/)’ | ‘90’ | ‘100.00’ | NULL          |
| ‘1’  | ‘SIMPLE’    | ‘t’   | NULL       | ‘eq_ref’ | ‘PRIMARY’     | ‘PRIMARY’  | ‘8’     | ‘sample.o.transaction_id’            | ‘1’  | ‘100.00’ | NULL          |

- `table`: 어떤 테이블에 대한 접근을 표시하고 있는지
- `id`: **SELECT에 붙은 번호**. MySQL은 **조인을 하나의 단위로 실행**하기 때문에 i**d는 그 쿼리에 실행 단위를 식별**하는 것이다. 따라서 조인만 수행하는 쿼리에서는 id는 항상 1이 된다.
- `select_type`: 복잡한 조인을 해도 항상 SIMPLE이 된다. **서브쿼리나 UNION이 있으면 id와 `select_type`이 변한다.**
- `partitions`: 파티셔닝이 되어 있는 경우에 사용되는 필드이다. 

