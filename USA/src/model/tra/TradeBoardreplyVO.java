package model.tra;



import java.sql.Time;

public class TradeBoardreplyVO {
	public int trarep_num; //번호PK
    public int trarep_tranum;   //-- 질문게시판 번호FK
    public String trarep_contents;
    public String trarep_writer ;//-- 아이디FK
    public int trarep_numref ;// -- 대댓글 유무
    public int trarep_writerrep ;//-- 부모작성자 번호
    public int trarep_numref_lv ;//-- 대댓글 순서
    public int trarep_alive;
    public String trarep_writerrepwriter;
    public String trarep_date;
    
    public String getTrarep_writerrepwriter() {
		return trarep_writerrepwriter;
	}
	public void setTrarep_writerrepwriter(String trarep_writerrepwriter) {
		this.trarep_writerrepwriter = trarep_writerrepwriter;
	}
	public int getTrarep_alive() {
		return trarep_alive;
	}
	public void setTrarep_alive(int trarep_alive) {
		this.trarep_alive = trarep_alive;
	}
    
    
	public String getTrarep_date() {
		return trarep_date;
	}
	public void setTrarep_date(String string) {
		this.trarep_date = string;
	}
	public int getTrarep_num() {
		return trarep_num;
	}
	public void setTrarep_num(int trarep_num) {
		this.trarep_num = trarep_num;
	}
	public int getTrarep_tranum() {
		return trarep_tranum;
	}
	public void setTrarep_tranum(int trarep_tranum) {
		this.trarep_tranum = trarep_tranum;
	}
	public String getTrarep_contents() {
		return trarep_contents;
	}
	public void setTrarep_contents(String trarep_contents) {
		this.trarep_contents = trarep_contents;
	}
	public String getTrarep_writer() {
		return trarep_writer;
	}
	public void setTrarep_writer(String trarep_writer) {
		this.trarep_writer = trarep_writer;
	}
	public int getTrarep_writerrep() {
		return trarep_writerrep;
	}
	public void setTrarep_writerrep(int trarep_writerrep) {
		this.trarep_writerrep = trarep_writerrep;
	}
	public int getTrarep_numref() {
		return trarep_numref;
	}
	public void setTrarep_numref(int trarep_numref) {
		this.trarep_numref = trarep_numref;
	}
	public int getTrarep_numref_lv() {
		return trarep_numref_lv;
	}
	public void setTrarep_numref_lv(int trarep_numref_lv) {
		this.trarep_numref_lv = trarep_numref_lv;
	}

}
