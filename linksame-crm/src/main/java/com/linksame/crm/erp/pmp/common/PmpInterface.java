package com.linksame.crm.erp.pmp.common;

public interface PmpInterface {
    interface contractPayment{
        interface trade{
            interface form{
                //收入
                String INCOME = "2";
                //支出
                String EXPENF = "1";
            }
            interface stats{
                //1完成 2 待交易 3 带审批
                String OK = "1";
                String TRADING = "2";
                String EXAMINE = "3";
            }
        }


    }
}
