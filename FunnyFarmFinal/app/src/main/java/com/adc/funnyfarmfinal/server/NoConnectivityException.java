package com.adc.funnyfarmfinal.server;

import java.io.IOException;

public class NoConnectivityException extends IOException {
    @Override
    public String getMessage() {
        return "Không có kết nối Internet, mời bạn kiểm tra lại.";
    }
}
