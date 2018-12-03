package com.sun.webexample.mvc.locks;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sun.webexample.entity.vo.ResultVO;
import com.sun.webexample.service.LockService;
import com.sun.webexample.service.ReentrantReadWriteLockService;
import com.sun.webexample.util.ResultUtil;

@RestController
public class LocksController {
	@Autowired LockService lockService;
	@Autowired ReentrantReadWriteLockService reentrantReadWriteLockService;
	@RequestMapping("/main")
	@ResponseBody
	public ResultVO<List<String>> lockin() {
		List<String> list = lockService.lockIn();
		ResultVO<List<String>> success = ResultUtil.success(list);
		return success;
	}
	
	@RequestMapping("/main2")
	@ResponseBody
	public ResultVO<List<String>> lockin2() {
		List<String> list = lockService.lockIn2();
		ResultVO<List<String>> success = ResultUtil.success(list);
		return success;
	}
	
	@RequestMapping("/main3")
	@ResponseBody
	public ResultVO<List<String>> lockin3() {
		List<String> list = lockService.lockqueue();
		ResultVO<List<String>> success = ResultUtil.success(list);
		return success;
	}
	@RequestMapping("/main4")
	@ResponseBody
	public ResultVO<List<String>> lockin4() {
		List<String> list = lockService.lockqueue2();
		ResultVO<List<String>> success = ResultUtil.success(list);
		return success;
	}
	
	@RequestMapping("/read")
	@ResponseBody
	public ResultVO<List<String>> read() {
		List<String> list = reentrantReadWriteLockService.read();
		ResultVO<List<String>> success = ResultUtil.success(list);
		return success;
	}
	
	@RequestMapping("/write")
	@ResponseBody
	public ResultVO<List<String>> write() {
		List<String> list = reentrantReadWriteLockService.write();
		ResultVO<List<String>> success = ResultUtil.success(list);
		return success;
	}
}
