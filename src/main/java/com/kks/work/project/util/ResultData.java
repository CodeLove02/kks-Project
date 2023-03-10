package com.kks.work.project.util;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 데이터의 정보를 JSON으로 뽑아주는 클래스
 * */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResultData<DT> {
	private String resultCode;
	private String msg;
	private DT data1;
	private String data1Name;			
	private Map<String, Object> body;
	
	// 생성자 메서드
	public ResultData(String resultCode, String msg, Object... args) {
		this.resultCode = resultCode;
		this.msg = msg;
		this.body = Utility.mapOf(args);
	}
	
	/**
	 * @param resultCode 성공/실패 코드
	 * @param msg 성공/실패에 따른 보여줄 메시지
	 * @param data1Name [선택] data1 의 이름
	 * @param data1 [선택] 여러 값
	 * 
	 * @return 매개변수에 넣은 값을 토대로 나온 JSON
	 * 
	 * */
	public static <DT> ResultData<DT> from(String resultCode, String msg, String data1Name, DT data1) {
		ResultData<DT> rd = new ResultData<>();
		rd.resultCode = resultCode;
		rd.msg = msg;
		rd.data1Name = data1Name;
		rd.data1 = data1;
		
		return rd;
	}
	
	public static <DT> ResultData<DT> from(String resultCode, String msg) {
		return from(resultCode, msg, null, null);
	}

	/**
	 * ResultData 성공
	 * */
	public boolean isSuccess() {
		return this.resultCode.startsWith("S-");
	}
	
	/**
	 * ResultData 실패
	 * */
	public boolean isFail() {
		return isSuccess() == false;
	}
}
