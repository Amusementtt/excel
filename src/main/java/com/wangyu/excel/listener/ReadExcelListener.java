package com.wangyu.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.wangyu.excel.common.CommonUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: wangyu
 * @time: 2021/1/9 20:00
 */
public class ReadExcelListener extends AnalysisEventListener<Map<Integer,Object>>{

    //全量数据的集合
    private List<Map<Integer,Object>> excelData;
    //excel列与表字段的映射关系
    private List<Map<String,Object>> excelColMapping;

    //构造函数
    public ReadExcelListener(List<Map<String,Object>> excelColMapping){
        this.excelColMapping = excelColMapping;
        Map<String,Object> verifyMap = new HashMap<>();
        for (Map<String,Object> map:excelColMapping){
            boolean isNullFlag = CommonUtils.paramIsNull(map,"isNull")?false:true;
            boolean isSizeFlag = CommonUtils.paramIsNull(map,"isSize")?false:true;
            if (isNullFlag && !isSizeFlag){
                //只校验空
                verifyMap.put((String)map.get("ord"),"0");
            }else if (!isNullFlag && isSizeFlag){
                //只校验长度
                verifyMap.put((String)map.get("ord"),"1");
            }else if (isNullFlag && isSizeFlag){
                //都校验
                verifyMap.put((String)map.get("ord"),"2");
            }else{
                continue;
            }
        }
    }

    /**
     * 每读取一行数据，就会执行一次此方法
     * @param data
     * @param analysisContext
     */
    @Override
    public void invoke(Map<Integer, Object> data, AnalysisContext analysisContext) {
        excelData.add(data);
        /**
         * 1.对参数进行校验
         * 2.移除不需要入库的字段
         */
    }

    /**
     * 数据读取完成之后，执行此方法
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
