package vk.dao;

import java.util.ArrayList;

public class VisitKoreaVO {
	
	public VisitKoreaVO(){
		image_list = new ArrayList<>();
		summary = new ArrayList<>();
		instruction = new ArrayList<>();
	}
	//메인 화면
	/**
	 * fesNo
	 * 행사 번호
	 */
	private int fesNo;
	/**
	 * title
	 * 행사 이름
	 */
	private String title;
	/**
	 * contentShort
	 * 짧은 행사소개 메인 페이지용
	 */
	private String contentShort;
	/**
	 * contentLong
	 * 개별 페이지 행사소개 full content
	 */
	private String contentLong;
	/**
	 * mainLoc
	 * 행사 지역 i.e. 경기도 가평군 
	 */
	private String mainLoc;
	/**
	 * locDetail
	 * 행사장 full address
	 */
	private String locAddr;
	/**
	 * url
	 * 행사정보 디테일 url
	 */
	private String url;
	//개별 화면
	/**
	 * notice
	 * 공지사항
	 */
	private String notice;
	/**
	 * date
	 * 진행기간/ 행사기간
	 */
	private String date; // 진행 기간 행사기간
	
	/**
	 * addr 
	 * 개최지의 주소
	 */
	private String addr; // 개최지
	/**
	 * loc
	 * 행사장소 url 포함된 사이트 있음
	 */
	private String loc; // location
	/**
	 * tel 
	 * 연락처
	 */
	private String tel;
	/**
	 * homepage 
	 * 홈페이지
	 */
	private String homepage;
	
	/**
	 * image_list
	 * 관련 행사의 사진
	 * arraylist (String) 타입
	 */
	private ArrayList<String> image_list;

	/**
	 * summary
	 * 개요
		행사소개
		행사내용
		프로그램 
		부대행사 
		정보제공자
	 */
	private ArrayList<String> summary;
	/**
	 * instruction
	 * 이용안내
		공연시간 -점등시간
			-운영시간
		관람소요시간 
		관람가능연령 
		이용요금 
		어린이
		할인정보
	 */
	private ArrayList<String> instruction;
	
	public ArrayList<String> getInstruction() {
		return instruction;
	}
	public void setInstruction(ArrayList<String> usefultip) {
		this.instruction = usefultip;
	}
	public ArrayList<String> getSummary() {
		return summary;
	}
	public void setSummary(ArrayList<String> summary) {
		this.summary = summary;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public ArrayList<String> getImage_list() {
		return image_list;
	}
	public void setImage_list(ArrayList<String> image_list) {
		this.image_list = image_list;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContentShort() {
		return contentShort;
	}
	public void setContentShort(String content) {
		this.contentShort = content;
	}
	public String getMainLoc() {
		return mainLoc;
	}
	public void setMainLoc(String mainLoc) {
		this.mainLoc = mainLoc;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContentLong() {
		return contentLong;
	}
	public void setContentLong(String contentLong) {
		this.contentLong = contentLong;
	}
	public String getLocAddr() {
		return locAddr;
	}
	public void setLocAddr(String locAddr) {
		this.locAddr = locAddr;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	
	public int getFesNo() {
		return fesNo;
	}
	public void setFesNo(int fesNo) {
		this.fesNo = fesNo;
	}

	
	
}
