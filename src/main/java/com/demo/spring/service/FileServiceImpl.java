package com.demo.spring.service;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import org.springframework.stereotype.Service;

import com.demo.spring.service.FileService;


@Service
public class FileServiceImpl implements FileService {
	
	public List<String> getDirFilenames(String dirPath)
	{
		List<String> fileList = new ArrayList<String>();
		File directory = new File(dirPath);
		File[] files = directory.listFiles();
		if(files.length == 0)
		{
			return null;
		} else {
			for(File file : files)
			{
				fileList.add(file.getName());
			}
		}
		return fileList;
	}

}
	
	


