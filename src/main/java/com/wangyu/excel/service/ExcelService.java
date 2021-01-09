package com.wangyu.excel.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Excel操作接口
 */
public interface ExcelService {
    /**
     * 动态上传excel文件
     * @param file
     * @return
     */
    String uploadExcel(MultipartFile file) throws Exception;
}
