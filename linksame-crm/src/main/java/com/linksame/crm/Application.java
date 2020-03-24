package com.linksame.crm;

import com.jfinal.server.undertow.UndertowConfig;
import com.jfinal.server.undertow.UndertowServer;
import com.linksame.crm.common.config.JfinalConfig;
import com.linksame.crm.common.constant.BaseConstant;

public class Application {
    public static void main(String[] args) {
        UndertowConfig config=new UndertowConfig(JfinalConfig.class,"config/undertow.txt");
        config.setResourcePath("src/main/webapp,"+ BaseConstant.UPLOAD_PATH);
        config.setServerName(BaseConstant.NAME);
        UndertowServer.create(config).start();
    }
}
