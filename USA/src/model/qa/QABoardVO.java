package model.qa;

import java.sql.Date;
import java.sql.Timestamp;

public class QABoardVO {
	public int qa_num;
	public String qa_subject;
	public int qa_readcount;
	public String qa_writer;
	public String qa_filename;
	public String qa_contents;
	public int qa_alive;
	public String qa_sysdate;
	public String qa_head;

	public String getqa_sysdate() {
		return qa_sysdate;
	}

	public void setqa_sysdate(String timestamp) {
		this.qa_sysdate = timestamp;
	}

	public int getqa_alive() {
		return qa_alive;
	}

	public void setqa_alive(int qa_alive) {
		this.qa_alive = qa_alive;
	}

	public int getqa_num() {
		return qa_num;
	}

	public void setqa_num(int qa_num) {
		this.qa_num = qa_num;
	}

	public String getqa_subject() {
		return qa_subject;
	}

	public void setqa_subject(String qa_subject) {
		this.qa_subject = qa_subject;
	}

	public int getqa_readcount() {
		return qa_readcount;
	}

	public void setqa_readcount(int qa_readcount) {
		this.qa_readcount = qa_readcount;
	}

	public String getqa_writer() {
		return qa_writer;
	}

	public void setqa_writer(String qa_writer) {
		this.qa_writer = qa_writer;
	}

	public String getqa_filename() {
		return qa_filename;
	}

	public void setqa_filename(String qa_filename) {
		this.qa_filename = qa_filename;
	}

	public String getqa_contents() {
		return qa_contents;
	}

	public void setqa_contents(String qa_contents) {
		this.qa_contents = qa_contents;
	}

	public String getqa_head() {
		return qa_head;
	}

	public void setqa_head(String qa_head) {
		this.qa_head = qa_head;
	}
}
