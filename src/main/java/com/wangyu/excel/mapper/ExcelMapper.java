package com.wangyu.excel.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ExcelMapper {
    /**
     * 获取到excel列字段与数据库字段名的映射信息
     * @return
     */
    List<Map<String,Object>> getExcelColMapping();
}
