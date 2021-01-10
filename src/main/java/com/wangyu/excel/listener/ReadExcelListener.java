package com.wangyu.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.wangyu.excel.common.CommonUtils;

import java.util.*;

/**
 * @description:
 * @author: wangyu
 * @time: 2021/1/9 20:00
 */
public class ReadExcelListener extends AnalysisEventListener<Map<Integer,Object>>{

    //全量数据的集合
    private List<Map<Integer,Object>> excelData = new ArrayList<>();
    //excel列与表字段的映射关系
    private List<Map<String,Object>> excelColMapping = new ArrayList<>();
    //要忽略的行数
    private List<Integer> ignoreRows = new ArrayList<>();
    //最后要入库的列数和字段名
    private Map<Integer,Object> colNameMaps = new HashMap<>();
    //需要校验的字段map
    private Map<String,Object> verifyMap = new HashMap<>();
    //数据读取到了第几行
    private int readRowNum = 1;
    //校验标志位
    private boolean verifyFlag = true;
    //返回的结果集
    private Map<String,Object> resultMap = new HashMap();

    //构造函数
    public ReadExcelListener(List<Map<String,Object>> excelColMapping,Map<String,Object> resultMap){
        this.excelColMapping = excelColMapping;
        this.resultMap = resultMap;
        int rowNum = 1;
        for (Map<String,Object> map:excelColMapping){
            if ("0".equals(map.get("status"))){
                //如果是0,表示此字段是不需要入库的
                ignoreRows.add(Integer.valueOf((String)map.get("ord")) - 1);
            }else{
                colNameMaps.put(rowNum++,map.get("excelId"));
                boolean isNullFlag = CommonUtils.paramIsNull(map,"isNull")?false:true;
                boolean isSizeFlag = CommonUtils.paramIsNull(map,"isSize")?false:true;
                String ord = (String) map.get("ord");
                if (isNullFlag && !isSizeFlag){
                    //只校验空
                    verifyMap.put(ord,"0");
                }else if (!isNullFlag && isSizeFlag){
                    //只校验长度
                    verifyMap.put(ord,"1");
                    verifyMap.put("length",map.get("isSize"));
                }else if (isNullFlag && isSizeFlag){
                    //都校验
                    verifyMap.put(ord,"2");
                }else{
                    continue;
                }
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
        //数据计数器
        int rowNum = 1;
        Map<Integer,Object> dataAfterRemove = new HashMap<>();
        //将data重新组装进另一个Map<Integer,Object>
        /**
         * 1.对参数进行校验
         */
        //verifyMap中存放的数据为需要校验的字段编号+以何种校验方式校验
        for (String key:verifyMap.keySet()){
            String verifyFlag = (String)verifyMap.get(key);
            Integer verifyIndex = Integer.valueOf(key) - 1;
            String cellValue = (String)data.get(verifyIndex);
            if ("0".equals(verifyFlag)){
                if (CommonUtils.isEmpty(cellValue)){
                    resultMap.put("exception","第" + rowNum + "行数据不能为空");
                }
            }else if ("1".equals(verifyFlag)){
                String length = (String) verifyMap.get("length");
                if (cellValue.length() > Integer.valueOf(length)){

                }
            }else if ("2".equals(verifyFlag)){
                String length = (String) verifyMap.get("length");
                if (CommonUtils.isEmpty(cellValue)){

                }
                if (cellValue.length() < Integer.valueOf(length)){

                }
            }
        }
        //校验完成后，移除不需要的字段，并循环将其组装到新的data
        for (int i = 0;i < data.size();i ++){
            //如果忽略字段中包含需要忽略的，直接忽略该字段,continue
            if (ignoreRows.contains(i)){
                continue;
            }
            //否则，加入到dataAfterRomove
            dataAfterRemove.put(rowNum++,data.get(i));
        }
        excelData.add(dataAfterRemove);
    }

    /**
     * 数据读取完成之后，执行此方法
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("hello");
    }
}
