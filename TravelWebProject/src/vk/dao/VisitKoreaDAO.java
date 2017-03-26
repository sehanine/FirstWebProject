package vk.dao;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * 
 * @author mdSHin
 * vkDAO ���ǻ���

 */
public class VisitKoreaDAO {
	
	private String addr1 = "https://korean.visitkorea.or.kr/kor/bz15/where/festival/festival.jsp?areaCode=&year=2017&keyword=&type=&gotoPage=";
	private String addr2 = "&listType=rdesc&cid=&out_service=";
	private int pageNumber = 1;
	private DataDAO dao = new DataDAO();
	public void getAllData(){
		int fesNo = 1;
		try{
			/*
			<div class="doc">

			<p class="total bottom">�� �Խù� : 67�� , ������ : 1/7</p>
			 */
			dao.dropAndCreateImageTable();
			Document contents = Jsoup.connect(addr1 + pageNumber + addr2).get();
			Elements conNum = contents.select("div.doc p.total");
			Element conNum_ = conNum.get(0);
			int numOfContents = Integer.parseInt(conNum_.text().substring(8, 10));
			
			System.out.println("content number: " + numOfContents);
/* 
	<div class="cnt">
		<h3>��ħ������� ��������������</h3>
		<p class="info1">
			<span class="skip">��ȸ��</span> <span class="read">476278</span>
			<span class="skip">�ۼ���</span> <span class="time">12.05</span>
		</p> 
		<p class="txt">
			10���� ���� �߿� ���� ������ ��ä�ο� ����� ���� �̿��Ͽ� Ư���� ������ ������ ǥ���� �߰� ���� ��������̴�. �ڿ��� ���� ��ȭ�� �߱��ϸ� ���� ���ʷ� �õ��� ���ο� ���� ǳ���� ��������������������, �ΰ����� ���� �ӿ����� �ѱ� �ڿ��� ��(ڸ)��...
		</p>
		<p class="info2">
			<span class="skip">��������</span> <span class="city">��⵵ ����</span>
			<span class="date">����Ⱓ : 2016.12.02 ~ 2017.03.26</span>
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
					vo.setContentShort(removeTags(eContent.text()));
					vo.setMainLoc(eMainLoc.text());
					vo.setDate(eDate.text().replace("����Ⱓ : ", ""));
					
					//test only---------------------------------------
					System.out.println("����ȣ: " + vo.getFesNo());
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
						System.out.println( (j+1) + ": " + url); // test only *****
					}
					
					/*
					 * ���Ⱓ
						��ġ 
						������
						�� �� ó
						Ȩ������ 
					 */
					for(int j = 0; j < ul_list_first.size(); j++){
						String temp = ul_list_first.get(j).text();
						if(temp.contains("��ġ")){
							vo.setLocAddr(temp.replace("��ġ ", ""));
						} else if(temp.contains("������")){
							vo.setLoc(temp.replace("������ ", ""));
						} else if(temp.contains("�� �� ó")){
							vo.setTel(temp.replace("�� �� ó ", ""));
						} else if(temp.contains("Ȩ������")){
							vo.setHomepage(temp.replace("Ȩ������ ", ""));
						} else {
							vo.setLocAddr(null);
						}
						/*
						 * vo.setLocAddr(temp.replace("��ġ ", ""));
						 * vo.setLoc(temp.replace("������ ", ""));
						 */
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
		<div class="title"><strong><a class="ov" href="#group1">���� <span class="detail">�󼼺���</span></a></strong></div>

		<div id="group1" class="grap">
			<ul class="ptList">


*/
					/**
					 * ���� 
					 * ul_list_second
					 */
					Elements ul_list_second = doc_.select("div.cntBox.open div.grap ul.ptList li");
					//System.out.println("size: " + ul_list_second.size());
					ArrayList<String> list_summary = vo.getSummary();
					for(int j = 0; j < ul_list_second.size(); j++){
						String temp = ul_list_second.get(j).text();
						
						if(j == 0){
							list_summary.add(removeTags(temp));
						} else {
							
							if(!list_summary.get(j - 1).contains(temp)){
								list_summary.add(removeTags(temp));
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
					System.out.println("����****");
					for(String str: list_summary){
						System.out.println("����: " + str);
					}
					//test only ******
					
				//	list.add(vo);
/*
		<div class="cntBox">
		<div class="title"><strong><a href="#group4">�̿�ȳ� <span class="detail">�󼼺���</span></a></strong></div>

		<div id="group4" class="grap">
			<ul class="ptList">
*/	
					/**
					 * �̿�ȳ�
					 * ul_list_third 
					 */
					ArrayList<String> list_instruction = vo.getInstruction();
					Elements ul_list_third = doc_.select("div.cntBox div#group4 ul.ptList li");
					for(int j = 0; j < ul_list_third.size(); j++){
						String temp = ul_list_third.get(j).text();
						if(j == 0){
							list_instruction.add(removeTags(temp)); // *****
						} else {
							
							if(!list_instruction.get(j - 1).contains(temp)){
								list_instruction.add(removeTags(temp)); // *****
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
					System.out.println("�̿�ȳ�****");
					for(String str: list_instruction){
						System.out.println("�̿�ȳ�: " + str);
					}
					//test only ******
					dao.setMain(vo);
					dao.setImageList(vo);
					dao.setFirstList(vo);
					dao.setSecondList(vo);
					dao.setThirdList(vo);
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
	public static String removeTags(String s){
		String[] tags = {"\u00a0", "\u003E", "\u003C"};
		for(int i = 0; i < tags.length; i++){
			s = s.replaceAll(tags[i], "");
		}
		return s;
	}
}
