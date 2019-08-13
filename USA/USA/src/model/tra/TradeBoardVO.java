package model.tra;


import java.sql.Date;
import java.sql.Timestamp;

public class TradeBoardVO {
	public int tra_num;
	public String tra_subject;
	public int tra_readcount;
	public String tra_writer;
	public String tra_filename;
	public String tra_contents;
	public int tra_alive;
	public String tra_sysdate;
	public String tra_head;
	
	public String getTra_sysdate() {
		return tra_sysdate;
	}
	public void setTra_sysdate(String timestamp) {
		this.tra_sysdate = timestamp;
	}
	public int getTra_alive() {
		return tra_alive;
	}
	public void setTra_alive(int tra_alive) {
		this.tra_alive = tra_alive;
	}
	public int getTra_num() {
		return tra_num;
	}
	public void setTra_num(int tra_num) {
		this.tra_num = tra_num;
	}
	public String getTra_subject() {
		return tra_subject;
	}
	public void setTra_subject(String tra_subject) {
		this.tra_subject = tra_subject;
	}
	public int getTra_readcount() {
		return tra_readcount;
	}
	public void setTra_readcount(int tra_readcount) {
		this.tra_readcount = tra_readcount;
	}
	public String getTra_writer() {
		return tra_writer;
	}
	public void setTra_writer(String tra_writer) {
		this.tra_writer = tra_writer;
	}
	public String getTra_filename() {
		return tra_filename;
	}
	public void setTra_filename(String tra_filename) {
		this.tra_filename = tra_filename;
	}
	public String getTra_contents() {
		return tra_contents;
	}
	public void setTra_contents(String tra_contents) {
		this.tra_contents = tra_contents;
	}
	
	public String getTra_head() {
		return tra_head;
	}
	public void setTra_head(String tra_head) {
		this.tra_head = tra_head;
	}
}
