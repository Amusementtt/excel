package com.wangyu.excel.common;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 公共工具类
 */
public final class CommonUtils {

    /**
     * 全国省份id（）
     */
    public static final String PROV_ID_ALL = "111";

    /**
     * 全部地市id
     */
    public static final String CITY_ID_ALL = "-1";

    /**
     * 默认显示七天的数
     */
    public static final String DEFAULT_TIME = "7";

    /**
     * 北十省份id
     */
    public static final String PROV_ID_NORTH_10 = "112";

    /**
     * 南二十一省份id
     */
    public static final String PROV_ID_SOUTH_21 = "113";

    /**
     * 北京省份id
     */
    public static final String PROV_ID_BEIJING = "011";

    /**
     * 天津省份id
     */
    public static final String PROV_ID_TIANJIN = "013";

    /**
     * 上海省份id
     */
    public static final String PROV_ID_SHANGHAI = "031";

    /**
     * 重庆省份id
     */
    public static final String PROV_ID_CHONGQING = "083";

    /**
     * 小数点
     */
    public static final String POINT = ".";


    /**
     * 工具类，不允许new对象
     */
    private CommonUtils() {
    }

    /**
     * double转BigDecimal
     *
     * @param value double
     * @return BigDecimal
     */
    public static BigDecimal doubleToBigDecimal(double value) {
        return new BigDecimal(String.valueOf(value));
    }

    /**
     * 判断字符为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]+");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 判定object对象为空
     *
     * @param object 对象
     * @return 判断结果
     */
    public static boolean isBlank(final Object object) {
        return null == object || "".equals(object);
    }

    /**
     * 判定object对象不为空
     *
     * @param object 对象
     * @return 判断结果
     */
    public static boolean isNotBlank(final Object object) {
        return null != object && !"".equals(object);
    }

    /**
     * 判定map中key对应的value为空，则赋给缺省值
     *
     * @param map   map对象
     * @param key   map对象的key
     * @param value map对象的value
     */
    public static void defaultValueOfMap(final Map map, final Object key, final Object value) {
        if (isBlank(map.get(key))) {
            map.put(key, value);
        }
    }

    /**
     * 判定key是否为空，空则赋给缺省值value，否则返回自身字符串
     *
     * @param key   map对象的key
     * @param value map对象的value
     */
    public static String defaultValueOfObject(final Object key, final String value) {
        return key == null ? value : key.toString();
    }

    public static String defaultValueOfObjects(final Object key, final String value) {
        return isEmpty(key) ? value : key.toString();
    }

    /**
     * 判断省分id是否为直辖市
     *
     * @param provId 省分id
     * @return 结果
     */
    public static boolean isMunicipality(String provId) {
        return provId.equals(PROV_ID_BEIJING) || provId.equals(PROV_ID_TIANJIN) || provId.equals(PROV_ID_SHANGHAI) || provId.equals(PROV_ID_CHONGQING);
    }

    /**
     * 去掉后面无用的零
     *
     * @param s
     * @return
     */
    public static String removeZero(String s) {
        if (s.indexOf(POINT) > 0) {
            //正则表达
            s = s.replaceAll("0+?$", "");//去掉后面无用的零
            s = s.replaceAll("[.]$", "");//如小数点后面全是零则去掉小数点
        }
        return s;
    }

    /**
     * 判断用户id是否是预发布用户
     *
     * @param preUserIds
     * @param userId
     * @return
     */
    public static boolean contains(String preUserIds, Object userId) {
        if (isBlank(preUserIds) || isBlank(userId)) {
            return false;
        }
        String[] preUserIdArray = preUserIds.split(",");
        for (String preUserId : preUserIdArray) {
            if (preUserId.equals(userId)) {
                return true;
            }
        }
        return false;
    }


//public static void main(String[] args) {
    //   System.out.println(isNumeric("6.884391184763904"));
//    List<String> list = new ArrayList<String>();
//    list.add("1");
//    String s = list.get(0);
//    list.set(0,"324");
//    s = "123";
//    System.out.printf(""+list);
////		List<Map<String, Object>> busiDataList = new ArrayList<>();
////		Map<String, Object> v1 = new HashMap<>();
////		v1.put("dValues", "-114445777.501");
////		v1.put("ID", "99");
////		busiDataList.add(v1);
////		Map<String, Object> v2 = new HashMap<>();
////		v2.put("dValues", "464122.85");
////		v2.put("ID", "02");
////		busiDataList.add(v2);
////		Map<String, Object> v3 = new HashMap<>();
////		v3.put("dValues", "38481183.85");
////		v3.put("ID", "01");
////		busiDataList.add(v3);
////		Map<String, Object> v4 = new HashMap<>();
////		v4.put("dValues", "5360383.662");
////		v4.put("ID", "04");
////		busiDataList.add(v4);
////		Map<String, Object> v5 = new HashMap<>();
////		v5.put("dValues", "8167523.025");
////		v5.put("ID", "03");
////		busiDataList.add(v5);
////		System.out.println("args = [" + convertMinusKpiData(null, busiDataList) + "]");
//	}

    /**
     * 对象是否为空
     *
     * @param o String,List,Map,Object[],int[],long[]
     * @return
     */
    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        }
        if (o instanceof String) {
            if (o.toString().equals("")) {
                return true;
            }
        } else if (o instanceof List) {
            if (((List) o).size() == 0) {
                return true;
            }
        } else if (o instanceof Map) {
            if (((Map) o).size() == 0) {
                return true;
            }
        } else if (o instanceof Set) {
            if (((Set) o).size() == 0) {
                return true;
            }
        } else if (o instanceof Object[]) {
            if (((Object[]) o).length == 0) {
                return true;
            }
        } else if (o instanceof int[]) {
            if (((int[]) o).length == 0) {
                return true;
            }
        } else if (o instanceof long[]) {
            if (((long[]) o).length == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断参数是否为空
     * @param params 参数
     * @param type 字段
     * @return true为空，false为非空
     */
    public static boolean paramIsNull(Map<String, Object> params, String type) {
        return Objects.isNull(params) || Objects.isNull(params.get(type)) || Objects.equals("", params.get(type));
    }
}
