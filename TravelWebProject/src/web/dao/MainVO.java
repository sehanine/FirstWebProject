package web.dao;

public class MainVO {
	// maintitle, maincontent, mainloc, fesdate
	private int fesno;
	private String maintitle;
	private String maincontent;
	private String mainloc;
	private String fesdate;
	
	
	public int getFesno() {
		return fesno;
	}
	public void setFesno(int fesno) {
		this.fesno = fesno;
	}
	public String getMaintitle() {
		return maintitle;
	}
	public void setMaintitle(String maintitle) {
		this.maintitle = maintitle;
	}
	public String getMaincontent() {
		return maincontent;
	}
	public void setMaincontent(String maincontent) {
		this.maincontent = maincontent;
	}
	public String getMainloc() {
		return mainloc;
	}
	public void setMainloc(String mainloc) {
		this.mainloc = mainloc;
	}
	public String getFesdate() {
		return fesdate;
	}
	public void setFesdate(String fesdate) {
		this.fesdate = fesdate;
	}
	
}
