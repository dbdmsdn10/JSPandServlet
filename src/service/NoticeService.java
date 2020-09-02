package service;

import java.util.List;

import entity.Notice;

public class NoticeService {
	public List<Notice> getNoticeList() {

		return getNoticeList("title","",1);
	}

	public List<Notice> getNoticeList(int page) {

		return getNoticeList("title","",page);
	}

	public List<Notice> getNoticeList(String field, String query,int page) {
		String sql="select*from notice limit "+page+",5";
		return null;
	}
	
	public int getNoticeCount(){
		return 0;
	}
	public int getNoticeCount(String field, String query,int page) {
		return 0;
	}
	public Notice getNotice(int id) {
		String sql="select*from notice where id=?";
		
		return null;
	}
	
	public Notice getNextNotice(int id) {
		String sql1=" select * from (select @rownum:=@rownum+1 as num,n. *from (select *from notice order by regfate) n, (select @rownum:=0) r) b where num=6";//rownum 으로 검색

		String sql2="select*from (select @rownum:=@rownum+1,n. *from (select *from notice order by regfate) n, (select @rownum:=0)  R )c where id=4";// id로 검색
		return null;
	}
	public Notice getPrevNotice(int id) {
		return null;
	}
}
