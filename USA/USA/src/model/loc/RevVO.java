package model.loc;

import java.sql.Timestamp;

public class RevVO {

	private int rev_num;
	private Timestamp rev_date;
	private String rev_writer;
	private String rev_contents;
	private int rev_alive;
	private int rev_score;
	private String rev_locname;
	
	public int getRev_num() {
		return rev_num;
	}
	public void setRev_num(int rev_num) {
		this.rev_num = rev_num;
	}
	public Timestamp getRev_date() {
		return rev_date;
	}
	public void setRev_date(Timestamp rev_date) {
		this.rev_date = rev_date;
	}
	public String getRev_writer() {
		return rev_writer;
	}
	public void setRev_writer(String rev_writer) {
		this.rev_writer = rev_writer;
	}
	public String getRev_contents() {
		return rev_contents;
	}
	public void setRev_contents(String rev_contents) {
		this.rev_contents = rev_contents;
	}
	public int getRev_alive() {
		return rev_alive;
	}
	public void setRev_alive(int rev_alive) {
		this.rev_alive = rev_alive;
	}
	public int getRev_score() {
		return rev_score;
	}
	public void setRev_score(int rev_score) {
		this.rev_score = rev_score;
	}
	public String getRev_locname() {
		return rev_locname;
	}
	public void setRev_locname(String rev_locname) {
		this.rev_locname = rev_locname;
	}
	
}
