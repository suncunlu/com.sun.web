package com.sun.webexample.mvc.gc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sun.webexample.entity.vo.ResultVO;
import com.sun.webexample.service.GcServie;
import com.sun.webexample.util.ResultUtil;

@RestController
public class GcController {
	@Autowired GcServie gcService;
	/**
	 * 1、Serial/Serial Old
	 * @param handle
	 * @return
	 */
	@RequestMapping("/t1")
	@ResponseBody
	public ResultVO<List<String>> serialAndSerialOld() {
		List<String> list = gcService.serialAndSerialOld();
		ResultVO<List<String>> success = ResultUtil.success(list);
		return success;
	}
	
	@RequestMapping("/config")
	@ResponseBody
	public ResultVO<List<String>> configs() throws InterruptedException {
		List<String> list = gcService.config();
		ResultVO<List<String>> success = ResultUtil.success(list);
		return success;
	}
}
