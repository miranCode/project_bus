package org.zerock.dto;

public class PageDTO {
    private int page;         // 현재 페이지 번호
    private int pageSize;     // 한 페이지에 보여줄 게시글 수
    private int totalCount;   // 총 게시글 수
    private int totalPages;   // 총 페이지 수
    private int startPage;    // 페이지 블록의 시작 페이지
    private int endPage;      // 페이지 블록의 끝 페이지

    


    // 생성자
    public PageDTO(int page, int pageSize, int totalCount) {
        this.page = page;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        
        // 전체 페이지 수 계산
        this.totalPages = (int) Math.ceil((double) totalCount / pageSize);
        
        // 페이지 블록 계산 (예: 1~10, 11~20, ...)
        int blockSize = 10; // 페이지 블록 크기
        int blockStart = (int) (Math.floor((double) page / blockSize) * blockSize) + 1;
        int blockEnd = Math.min(blockStart + blockSize - 1, totalPages);
        
        this.startPage = blockStart;
        this.endPage = blockEnd;
    }

    // Getters and Setters
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }
}
