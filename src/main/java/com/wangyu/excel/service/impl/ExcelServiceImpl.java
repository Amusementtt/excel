package com.wangyu.excel.service.impl;

import com.alibaba.excel.EasyExcel;
import com.wangyu.excel.listener.ReadExcelListener;
import com.wangyu.excel.mapper.ExcelMapper;
import com.wangyu.excel.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: wangyu
 * @time: 2021/1/9 17:38
 */
@Service
public class ExcelServiceImpl implements ExcelService{

    @Autowired
    ExcelMapper excelMapper;

    /**
     * 动态上传excel文件
     * @param file
     * @return
     */
    @Override
    public String uploadExcel(MultipartFile file) throws Exception{
        //获取到表映射信息
        List<Map<String,Object>> excelColMapping = excelMapper.getExcelColMapping();
        //接收EasyExcel读取完成后返回的信息
        Map<String,Object> resultMap = new HashMap<>();
        EasyExcel.read(file.getInputStream(),new ReadExcelListener(excelColMapping,resultMap)).sheet().doRead();
        return null;
    }
}
