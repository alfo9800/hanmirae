package org.edu.vo;

import java.util.Date;

/**
 * 첨부파일에서 사용되는 데이터 입출력 클래스
 * @author 한미래
 *
 */
public class AttachVO {
	private String save_file_name;
	private String real_file_name;
	private Integer bno;
	private Date reg_date;

	
	@Override
	public String toString() {
		return "디버그 AttachVO [save_file_name=" + save_file_name + ", real_file_name=" + real_file_name + ", bno=" + bno
				+ ", reg_date=" + reg_date + "]";
	}
	//GET, SET
	public String getSave_file_name() {
		return save_file_name;
	}
	public void setSave_file_name(String save_file_name) {
		this.save_file_name = save_file_name;
	}
	public String getReal_file_name() {
		return real_file_name;
	}
	public void setReal_file_name(String real_file_name) {
		this.real_file_name = real_file_name;
	}
	public Integer getBno() {
		return bno;
	}
	public void setBno(Integer bno) {
		this.bno = bno;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	
	
}
