desc notice;

select *from notice

create table comment (id int, content text,regdate text,writer_id text,notice_id int)

desc comment

insert into comment(id,content,regdate,writer_id,notice_id) value (2,'내용2','2020/5/7','나2',7)

insert into comment(id,content,regdate,writer_id,notice_id) value (3,'내용3','2020/5/9','나3',8)

select*from comment

select n.*, count(c.id) as ccnt from notice n
left outer join comment c on n.id=c.notice_id
group by n.id,n.title,n.content,n.writer_id,n.regfate,n.hit
order by n.regfate desc

create view notice_view as
select n.*, count(c.id) as ccnt from notice n
left outer join comment c on n.id=c.notice_id
group by n.id,n.title,n.content,n.writer_id,n.regfate,n.hit
order by n.regfate desc,n.id desc

select*from notice_view

drop view notice_view 

select * from (select * from (select @rownum:=@rownum+1 as num,n. *from (select *from notice_view order by regfate,id desc) n, (select @rownum:=0) r) b)c where num between 1 and 5

select * from (select @rownum:=@rownum+1 AS num,n. *from (select *from notice order by regfate) n,(select @rownum:=0) r)  b where id=?