package vk.dao;

public class StringTest {
	public static String removeTags(String s){
		String[] tags = {"\u00a0", "\u2264", "\u003E"};
		for(int i = 0; i < tags.length; i++){
			s = s.replace(tags[i], "");
		}
		return s;
	}
	public static void main(String[] args){
		
		String input1 = "줄 거 리 ‘의’와 ‘예’의 가치를 중시하는 태권도. 그리고 최강의 태권도 전사, 킥스 5인방은‘진정한 강함이란 무엇인가?’화두 아래 수련을 계속 하고 있다. 그러던 어느날 태권도를 말살시키고 자신이 최강자가 되기 위해 나타난 악의 수장, 오.디.나우캇.태권도(Taekwondo)의 가치를 전복시키기 위해 이름까지 바꾼 오.디. 나우캇(O.D.Nowkeat)은 <배틀K>라는 난투쇼를 만들어 세계 유명 격투가들의 생체데이터를 확보하고 있다. 잔혹하고, 자극적인 배틀K에 열광하는 사람들로 인해 태권도는 설 자리를 잃어가고, 실망한 킥스는 뿔뿔히 흩어진다. 과연 태권도는 이렇게 세상에서 잊혀질 것인가? 태권도의 명운을 걸고 벌이는 킥스와 나우캇 일당의 한판승부가 지금 시작된다!";
		String input2 =  "출 연 송동철, 김규태, 정수빈, 원수진, 원연상, 하기성, 주만기, 최인호, 이요한, 박용환, 박충식, 이승준, 한진우, 김은지, 조초희, 황선홍, 진원병, 서우준, 이상규, 헌병훈, 홍승현";
		
		String input3 = "&lt;오리지널드로잉쇼&gt;는 2007년 &nbsp;김진규 예술감독에 의해서 세계최초로 회화와 미술이라는 소재를 무대 위의 주인공으로 과감하게 끌여들여 예술감각과 특수효과를 덧입혀 ‘드로잉 넌버벌 퍼포먼스’라는 새로운 세계의 문을 활짝 열었다. 눈 앞에서 펼쳐지는 환상의 라이브 드로잉과 상상력을 자극하는 퍼포먼스로 만들어지는 작품들은 새롭고 다양한 감성으로 즐거움과 감동을 전한다.";
		System.out.println(input3);
		System.out.println("after: " + removeTags(input3));
		
	}
}
