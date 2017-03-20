package vk.dao;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class VisitKoreaDAO {
	
	private String addr1 = "https://korean.visitkorea.or.kr/kor/bz15/where/festival/festival.jsp?areaCode=&year=2017&keyword=&type=&gotoPage=";
	private String addr2 = "&listType=rdesc&cid=&out_service=";
	private int pageNumber = 1;

	public void getAllData(){
		int fesNo = 1;
		try{
			/*
			<div class="doc">

			<p class="total bottom">총 게시물 : 67건 , 페이지 : 1/7</p>
			 */
			Document contents = Jsoup.connect(addr1 + pageNumber + addr2).get();
			Elements conNum = contents.select("div.doc p.total");
			Element conNum_ = conNum.get(0);
			int numOfContents = Integer.parseInt(conNum_.text().substring(8, 10));
			
			System.out.println("content number: " + numOfContents);
/*
	<div class="cnt">
		<h3>아침고요수목원 오색별빛정원전</h3>
		<p class="info1">
			<span class="skip">조회수</span> <span class="read">476278</span>
			<span class="skip">작성일</span> <span class="time">12.05</span>
		</p>
		<p class="txt">
			10만여 평의 야외 정원 곳곳을 다채로운 조명과 빛을 이용하여 특별한 주제를 가지고 표현한 야간 조명 점등행사이다. 자연과 빛의 조화를 추구하며 국내 최초로 시도된 새로운 빛의 풍경인 ‘오색별빛정원전’은, 인공적인 조명 속에서도 한국 자연의 미(美)를...
		</p>
		<p class="info2">
			<span class="skip">축제지역</span> <span class="city">경기도 가평군</span>
			<span class="date">진행기간 : 2016.12.02 ~ 2017.03.26</span>
		</p>

*/			
			while(numOfContents > 0){
				Document doc = Jsoup.connect(addr1 + pageNumber + addr2).get();
				Elements title = doc.select("div.cnt h3");
				Elements content = doc.select("div.cnt p.txt");
				Elements mainLoc = doc.select("div.cnt p.info2 span.city");
				Elements date = doc.select("div.cnt p.info2 span.date");
				Elements conAddr = doc.select("div.newsWrap div.news_list div.item a");
/*
				<div class="item"><a href="festival.jsp?cid=663045&type=&gotoPage=">
*/
				for(int i = 0; i < title.size(); i++){
					numOfContents--;
					Element eTitle = title.get(i);
					Element eContent = content.get(i);
					Element eMainLoc = mainLoc.get(i);
					Element eDate = date.get(i);
					Element eConAddr = conAddr.get(i);
					VisitKoreaVO vo = new VisitKoreaVO();
					vo.setFesNo(fesNo++);
					vo.setUrl("https://korean.visitkorea.or.kr/kor/bz15/where/festival/" + eConAddr.attr("href"));
					vo.setTitle(eTitle.text());
					vo.setContentShort(eContent.text().replaceAll("\u00a0", " "));
					vo.setMainLoc(eMainLoc.text());
					vo.setDate(eDate.text().replace("진행기간 : ", ""));
					
					//test only---------------------------------------
					System.out.println(vo.getTitle());
					System.out.println(vo.getContentShort());
					System.out.println("mainLoc: " + vo.getMainLoc());
					System.out.println(vo.getDate());
					System.out.println(vo.getUrl());
					//test only---------------------------------------
					
					Document doc_ = Jsoup.connect(vo.getUrl()).get();
					Elements eImage = doc_.select("div.obj img");
					Elements ul_list_first = doc_.select("figcaption ul li");
					
					for(int j = 0; j < eImage.size(); j++){
						Element eImage_ = eImage.get(j);
						String url = eImage_.attr("src");
						vo.getImage_list().add(url);
						System.out.println("image_url: " + url); // test only *****
					}
					
					for(int j = 0; j < ul_list_first.size(); j++){
						String temp = ul_list_first.get(j).text();
						if(temp.contains("위치")){
							vo.setLocAddr(temp.replace("위치 ", ""));
						} else if(temp.contains("행사장소")){
							vo.setLoc(temp.replace("행사장소 ", ""));
						} else if(temp.contains("연 락 처")){
							vo.setTel(temp.replace("연 락 처 ", ""));
						} else if(temp.contains("홈페이지")){
							vo.setHomepage(temp.replace("홈페이지 ", ""));
						} else {
							vo.setLocAddr(null);
						}
					}
					//test output only *****
					System.out.println(vo.getLocAddr());
					System.out.println(vo.getLoc());
					System.out.println(vo.getTel());
					System.out.println(vo.getHomepage());
					//test output only *****

/*
				<div id="group1" class="grap">
					<ul class="ptList">
						<div class="cntBox open">
		<div class="title"><strong><a class="ov" href="#group1">개요 <span class="detail">상세보기</span></a></strong></div>

		<div id="group1" class="grap">
			<ul class="ptList">


*/
					/**
					 * 개요 
					 * ul_list_second
					 */
					Elements ul_list_second = doc_.select("div.cntBox.open div.grap ul.ptList li");
					//System.out.println("size: " + ul_list_second.size());
					ArrayList<String> list_summary = vo.getSummary();
					for(int j = 0; j < ul_list_second.size(); j++){
						String temp = ul_list_second.get(j).text();
						
						if(j == 0){
							list_summary.add(temp.replaceAll("\u00a0", " "));
						} else {
							
							if(!list_summary.get(j - 1).contains(temp)){
								list_summary.add(temp.replaceAll("\u00a0", " "));
							} else 
								list_summary.add("DUPLICATE");
						}
					}
					
					for(int j = 0; j < list_summary.size(); j++){	
						if(list_summary.get(j).contains("DUPLICATE")){
							System.out.println("ul_list_second duplicate removed");
							list_summary.remove(j);
						}
					}
					//test only ******
					System.out.println("개요****");
					for(String str: list_summary){
						System.out.println("개요: " + str);
					}
					//test only ******
					
				//	list.add(vo);
/*
		<div class="cntBox">
		<div class="title"><strong><a href="#group4">이용안내 <span class="detail">상세보기</span></a></strong></div>

		<div id="group4" class="grap">
			<ul class="ptList">
*/	
					/**
					 * 이용안내
					 * ul_list_third 
					 */
					ArrayList<String> list_instruction = vo.getInstruction();
					Elements ul_list_third = doc_.select("div.cntBox div#group4 ul.ptList li");
					for(int j = 0; j < ul_list_third.size(); j++){
						String temp = ul_list_third.get(j).text();
						if(j == 0){
							list_instruction.add(temp.replaceAll("\u00a0", " "));
						} else {
							
							if(!list_instruction.get(j - 1).contains(temp)){
								list_instruction.add(temp.replaceAll("\u00a0", " "));
							} else 
								list_instruction.add("DUPLICATE");
						}
					}
				
					for(int j = 0; j < list_instruction.size(); j++){	
						if(list_instruction.get(j).contains("DUPLICATE")){
							System.out.println("ul_list_third duplicate removed");
							list_instruction.remove(j);
						}
					}
					
					//test only ******
					System.out.println("이용안내****");
					for(String str: list_instruction){
						System.out.println("이용안내: " + str);
					}
					//test only ******
					
				//	break;// test only ********
				}
				pageNumber++;
				//break; // test only *********
			}
			
		}catch(Exception ex){
			System.out.println("VisitKoreaDAO.java getAllData");
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		new VisitKoreaDAO().getAllData();
	}
}
