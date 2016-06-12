package com.zcoder.admin.core.message;

/**
 * 用于返回分页表格数据
 * @author lin
 *
 * @param <T>
 */
public class ResponsePageData <T> {
	
	private T data;
	
	private String draw;
	
	private int recordsFiltered;

	private int recordsTotal;
	

	public ResponsePageData(int recordsTotal, int recordsFiltered, T data) {
		super();
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsFiltered;
		this.data = data;
	}

	public ResponsePageData(int recordsTotal, T data) {
		super();
		this.recordsTotal = recordsTotal;
		this.data = data;
		this.recordsFiltered = recordsTotal;
	}

	public ResponsePageData(String draw, int recordsTotal, int recordsFiltered, T data) {
		super();
		this.draw = draw;
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsFiltered;
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public String getDraw() {
		return draw;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setDraw(String draw) {
		this.draw = draw;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	
	@Override
	public String toString() {
		return "ResponsePageData [draw=" + draw + ", recordsTotal=" + recordsTotal + ", recordsFiltered="
				+ recordsFiltered + ", data=" + data + "]";
	}
	
	

}
