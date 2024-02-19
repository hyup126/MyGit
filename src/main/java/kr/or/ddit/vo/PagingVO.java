package kr.or.ddit.vo;

import java.util.List;

import lombok.Data;

@Data
public class PagingVO<T> {
	private int totalRecord;		// �? 게시�? ?��
	private int totalPage;			// �? ?��?���? ?��
	private int currentPage;		// ?��?�� ?��?���?
	private int screenSize = 5;	// ?��?���? ?�� 게시�? ?��
	private int blockSize = 5;		// ?��?���? 블록 ?��
	private int startRow;			// ?��?�� row
	private int endRow;				// ?�� row
	private int startPage;			// ?��?�� ?��?���?
	private int endPage;			// ?�� ?��?���?	
	private List<T> dataList;		// 결과�? ?��?�� ?��?��?�� 리스?��
	private String searchType;		// �??�� ???��(?���?,?��?��?��,?���?+?��?��?�� ?��?��)
	private String searchWord;		// �??�� ?��?��(?��?��?��)
	
	public PagingVO() {}
	public PagingVO(int screenSize, int blockSize) {
		super();
		this.screenSize = screenSize;
		this.blockSize = blockSize;
	}
	
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		// ceil?? ?��림에 ?��?��?��?��.
		// �? 게시�? ?���? 121개일?��, screenSize�? ?��?��게되�? 몫�? 12?���?�? ?��머�?�? 존재?��?��.
		// ?��?��, ?��림을 ?��게되�? ?��머�??�� ???�� �?분이 몫으�? ?��림이 ?���?�? 13?�� ?��?��.
		totalPage = (int)Math.ceil(totalRecord / (double)screenSize);
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;	// ?��?�� ?��?���?
		endRow = currentPage * screenSize;		// ?�� row = ?��?�� ?��?���? * ?�� ?��?���??�� 게시�? ?��
		startRow = endRow - (screenSize - 1);	// ?��?�� row = ?�� row - (?�� ?��?���??�� 게시�? ?�� - 1)
		// 마�?�? ?��?���? = (?��?�� ?��?���? + (?��?���? 블록 ?��?���? - 1)) / ?��?���? 블록 ?��?���? * ?��?���? 블록 ?��?���?
		// blockSize * blockSize?�� 1,2,3,4... ?��?���?마다 ?��?�� 계산?�� ?��?�� ?��?�� 계산?�� ?��?��?�� endPage�? 구한?��.
		endPage = (currentPage + (blockSize - 1)) / blockSize * blockSize;
		startPage = endPage - (blockSize - 1);
	}
	
	public String getPagingHTML() {
		StringBuffer html = new StringBuffer();
		html.append("<ul class='pagination pagination-sm m-0 float-right'>");
		
		if(startPage > 1) {
			html.append("<li class='page-item'><a href='' class='page-link' data-page='" + 
					(startPage - blockSize) + "'>Prev</a></li>");
		}
		
		// ?��?�� ?��?���?�??�� blockSize?�� ?��?��?��?�� ?��?���? ?��보�?? 출력?�� 반복�?
		for(int i = startPage; i <= (endPage < totalPage ? endPage : totalPage); i++) {
			if(i == currentPage) {
				html.append("<li class='page-item active'><span class='page-link'>" + i + "</span></li>");
			}else {
				html.append("<li class='page-item'><a href='' class='page-link' data-page='"+i+"'>"+i+"</a></li>");
			}
		}
		
		if(endPage < totalPage) {
			html.append("<li class='page-item'><a href='' class='page-link' data-page='"+(endPage+1)+"'>Next</a></li>");
		}
		html.append("</ul>");
		return html.toString();
	}
	
	
}





















