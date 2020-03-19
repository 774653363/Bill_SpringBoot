package ek.zhou.springboot.entities;

import java.io.Serializable;

public class BillProvider extends  Bill implements Serializable {
    private String providerName;

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }
}
