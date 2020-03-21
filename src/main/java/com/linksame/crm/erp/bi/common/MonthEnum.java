package com.linksame.crm.erp.bi.common;

/**
 * @author hmb
 */

public enum MonthEnum{
    /** 一月 */
    JANUARY("january","一月","01"),
    /** 二月 */
    FEBRUARY("february","二月","02"),
    /** 三月 */
    MARCH("march","三月","03"),
    /** 四月 */
    APRIL("april","四月","04"),
    /** 五月 */
    MAY("may","五月","05"),
    /** 六月 */
    JUNE("june","六月","06"),
    /** 七月 */
    JULY("july","七月","07"),
    /** 八月 */
    AUGUST("august","八月","08"),
    /** 九月 */
    SEPTEMBER("september","九月","09"),
    /** 十月 */
    OCTOBER("october","十月","10"),
    /** 十一月 */
    NOVEMBER("november","十一月","11"),
    /** 十二月 */
    DECEMBER("december","十二月","12");

    MonthEnum(String name, String remark,String value){
        this.name = name;
        this.remark = remark;
        this.value = value;
    }

    public String getName(){
        return name;
    }

    public String getRemark(){
        return remark;
    }

    public String getValue(){
        return value;
    }

    private String name;
    private String remark;
    private String value;


}
