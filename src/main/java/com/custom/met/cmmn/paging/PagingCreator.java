package com.custom.met.cmmn.paging;


import com.custom.met.cmmn.model.CustomMap;
import com.custom.met.cmmn.utils.MapUtils;

import lombok.Getter;

/***
 * <pre>
 * 클래스명: PagingCreator
 * 설명: 페이지바 생성
 * </pre>
 */
@Getter
public class PagingCreator {
	
	/***
	 * <pre>
	 * 변수명: width
	 * 설명: 페이지바 너비
	 * </pre>
	 */
	private int width = 10;
	/***
	 * <pre>
	 * 변수명: count
	 * 설명: 레코드 총 개수
	 * </pre>
	 */
	private long count;
	/***
	 * <pre>
	 * 변수명: rowAmountPerPage
	 * 설명: 페이지당 레코드수
	 * </pre>
	 */
	private int rowAmountPerPage;
	/***
	 * <pre>
	 * 변수명: pageNum
	 * 설명: 페이지바 현재페이지
	 * </pre>
	 */
	private int pageNum;
	/***
	 * <pre>
	 * 변수명: startPagingNum
	 * 설명: 페이지바 시작페이지
	 * </pre>
	 */
	private int startPagingNum;
	/***
	 * <pre>
	 * 변수명: endPagingNum
	 * 설명: 페이지바 마지막페이지
	 * </pre>
	 */
	private int endPagingNum;
	/***
	 * <pre>
	 * 변수명: prev
	 * 설명: 페이지바 이전버튼 유무
	 * </pre>
	 */
	private boolean prev;
	/***
	 * <pre>
	 * 변수명: next
	 * 설명: 페이지바 다음버튼 유무
	 * </pre>
	 */
	private boolean next;
	/***
	 * <pre>
	 * 변수명: lastPageNum
	 * 설명: 가장 마지막 페이지
	 * </pre>
	 */
	private int lastPageNum;
	
	
	public PagingCreator(CustomMap customMap) {
		
		long theCount = customMap.getLong("count");
		int thePageNum = customMap.getInt("pageNum", 1) < 1 ? 1 : customMap.getInt("pageNum", 1);
		int theRowAmountPerPage = customMap.getInt("rowAmountPerPage", 10) < 10 ? 10 : customMap.getInt("rowAmountPerPage", 10);
		
		count = theCount;
		pageNum = thePageNum;
		rowAmountPerPage = theRowAmountPerPage;
		
		endPagingNum = ((int) Math.ceil(pageNum / (width * 1.0)) ) * width;
		startPagingNum = endPagingNum - width + 1;
		
		
		lastPageNum = (int) Math.ceil(count / (rowAmountPerPage * 1.0));
		prev = (startPagingNum > 1);
		next =  (lastPageNum > endPagingNum);
		
		if (!next) {
			endPagingNum = lastPageNum;
		}
	}


	@Override
	public String toString() {
		return toCustomMap().toString();
	}
	
	public CustomMap toCustomMap() {
		return new CustomMap(MapUtils.objectToMap(this));
	}
	
	
}
