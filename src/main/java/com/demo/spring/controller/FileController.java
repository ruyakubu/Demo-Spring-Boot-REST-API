package com.demo.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.service.FileService;

@RestController
@RequestMapping("/api")
public class FileController {
	
	@Autowired
	private FileService fileService;

	@RequestMapping(value = "/file/directory", method = RequestMethod.POST)
	public @ResponseBody List<String> lookUpFiles(@RequestParam String directory) {
		return fileService.getDirFilenames(directory);
	}

}
