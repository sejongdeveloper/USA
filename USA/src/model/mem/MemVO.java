package model.mem;

import java.sql.Timestamp;

public class MemVO {
	private String mem_id; // 아이디
	private String mem_pwd; // 비밀번호
	private String mem_ph; // 전화번호
	private String  mem_name; // 이름   
	private String mem_addr; // 주소
	private String  mem_filename; // 파일이름
	private int mem_point; // 포인트
	private int mem_alive; // 탈퇴유무
	private Timestamp mem_cdate; // 가입날짜
	private Timestamp mem_ddate; // 탈퇴날짜

	public MemVO() {} // 기본생성자
	
	public MemVO(String mem_id, String mem_pwd, String mem_ph, String mem_name, String mem_addr, String mem_filename) {
		super();
		this.mem_id = mem_id;
		this.mem_pwd = mem_pwd;
		this.mem_ph = mem_ph;
		this.mem_name = mem_name;
		this.mem_addr = mem_addr;
		this.mem_filename = mem_filename;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_pwd() {
		return mem_pwd;
	}
	public void setMem_pwd(String mem_pwd) {
		this.mem_pwd = mem_pwd;
	}
	public String getMem_ph() {
		return mem_ph;
	}
	public void setMem_ph(String mem_ph) {
		this.mem_ph = mem_ph;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_addr() {
		return mem_addr;
	}
	public void setMem_addr(String mem_addr) {
		this.mem_addr = mem_addr;
	}
	public String getMem_filename() {
		return mem_filename;
	}
	public void setMem_filename(String mem_filename) {
		this.mem_filename = mem_filename;
	}
	public int getMem_point() {
		return mem_point;
	}
	public void setMem_point(int mem_point) {
		this.mem_point = mem_point;
	}
	public int getMem_alive() {
		return mem_alive;
	}
	public void setMem_alive(int mem_alive) {
		this.mem_alive = mem_alive;
	}
	public Timestamp getMem_cdate() {
		return mem_cdate;
	}
	public void setMem_cdate(Timestamp mem_cdate) {
		this.mem_cdate = mem_cdate;
	}
	public Timestamp getMem_ddate() {
		return mem_ddate;
	}
	public void setMem_ddate(Timestamp mem_ddate) {
		this.mem_ddate = mem_ddate;
	}
	
}
