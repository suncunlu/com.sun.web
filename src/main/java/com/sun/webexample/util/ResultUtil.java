package com.sun.webexample.util;

import com.sun.webexample.entity.vo.ResultVO;
import com.sun.webexample.enums.ResultEnum;

/**
 * 返回结果工具类
 * 
 * @author jh
 *
 */
public class ResultUtil {
	private ResultUtil() {
		throw new IllegalStateException("Utility class");
	}

	public static <T> ResultVO<T> success(T object) {
		ResultVO<T> resultVO = new ResultVO<T>();
		resultVO.setErrcode(ResultEnum.SUCCESS.getCode());
		resultVO.setErrmsg(ResultEnum.SUCCESS.getMsg());
		resultVO.setData(object);
		return resultVO;
	}

	public static <T> ResultVO<T> success() {
		return success(null);
	}

	public static <T> ResultVO<T> error(Integer errcode, String errmsg) {
		ResultVO<T> resultVO = new ResultVO<T>();
		resultVO.setErrcode(errcode);
		resultVO.setErrmsg(errmsg);
		return resultVO;

	}

	public static <T> ResultVO<T> error(ResultEnum resultEnum) {
		ResultVO<T> resultVO = new ResultVO<T>();
		resultVO.setErrcode(resultEnum.getCode());
		resultVO.setErrmsg(resultEnum.getMsg());
		return resultVO;

	}
}
