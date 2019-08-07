package model.tra;

public class TradeBoardRepleVO {
	public int trarep_num; //번호PK
    public int trarep_tranum;   //-- 질문게시판 번호FK
    public String trarep_contents;
    public String trarep_writer ;//-- 아이디FK
    public int trarep_writerrep ;//-- 부모작성자 번호
    public int trarep_numref ;// -- 대댓글 유무
    public int trarep_numref_lv ;//-- 대댓글 순서
    
    
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
