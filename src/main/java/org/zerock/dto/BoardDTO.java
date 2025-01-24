package org.zerock.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BoardDTO {
    private Long bno;       // 게시글 번호1
    private String name;    // 작성자 이름2
    private String title;   // 게시글 제목3
    private String content; // 게시글 내용4
    private String email;   // 작성자 이메일5

    private String status;

  
    private String memo;
    
    private String rteNm; // RTE_NM 필드 추가
   
    
    private Date regdate; // Date 타입으로 설정

    // Getter 및 Setter
    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            this.regdate = sdf.parse(regdateStr); // String을 Date로 변환
        } catch (Exception e) {
            e.printStackTrace();  // 예외 처리
        }
    }

	public Long getBno() {
		return bno;
	}

	public void setBno(Long bno) {
		this.bno = bno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getRteNm() {
		return rteNm;
	}

	public void setRteNm(String rteNm) {
		this.rteNm = rteNm;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "BoardDTO [bno=" + bno + ", name=" + name + ", title=" + title + ", content=" + content + ", email="
				+ email + ", status=" + status + ", memo=" + memo + ", rteNm=" + rteNm + ", regdate=" + regdate + "]";
	}

	public String getWriter() {
		// TODO Auto-generated method stub
		return null;
	}


	
	
}