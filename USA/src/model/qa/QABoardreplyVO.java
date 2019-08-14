package model.qa;

import java.sql.Time;

public class QABoardreplyVO {
	public int qarep_num; // 번호PK
	public int qarep_qanum; // -- 질문게시판 번호FK
	public String qarep_contents;
	public String qarep_writer;// -- 아이디FK
	public int qarep_numref;// -- 대댓글 유무
	public int qarep_writerrep;// -- 부모작성자 번호
	public int qarep_numref_lv;// -- 대댓글 순서
	public int qarep_alive;
	public String qarep_writerrepwriter;
	public String qarep_date;

	public String getqarep_writerrepwriter() {
		return qarep_writerrepwriter;
	}

	public void setqarep_writerrepwriter(String qarep_writerrepwriter) {
		this.qarep_writerrepwriter = qarep_writerrepwriter;
	}

	public int getqarep_alive() {
		return qarep_alive;
	}

	public void setqarep_alive(int qarep_alive) {
		this.qarep_alive = qarep_alive;
	}

	public String getqarep_date() {
		return qarep_date;
	}

	public void setqarep_date(String string) {
		this.qarep_date = string;
	}

	public int getqarep_num() {
		return qarep_num;
	}

	public void setqarep_num(int qarep_num) {
		this.qarep_num = qarep_num;
	}

	public int getqarep_qanum() {
		return qarep_qanum;
	}

	public void setqarep_qanum(int qarep_qanum) {
		this.qarep_qanum = qarep_qanum;
	}

	public String getqarep_contents() {
		return qarep_contents;
	}

	public void setqarep_contents(String qarep_contents) {
		this.qarep_contents = qarep_contents;
	}

	public String getqarep_writer() {
		return qarep_writer;
	}

	public void setqarep_writer(String qarep_writer) {
		this.qarep_writer = qarep_writer;
	}

	public int getqarep_writerrep() {
		return qarep_writerrep;
	}

	public void setqarep_writerrep(int qarep_writerrep) {
		this.qarep_writerrep = qarep_writerrep;
	}

	public int getqarep_numref() {
		return qarep_numref;
	}

	public void setqarep_numref(int qarep_numref) {
		this.qarep_numref = qarep_numref;
	}

	public int getqarep_numref_lv() {
		return qarep_numref_lv;
	}

	public void setqarep_numref_lv(int qarep_numref_lv) {
		this.qarep_numref_lv = qarep_numref_lv;
	}

}
