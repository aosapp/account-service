package com.advantage.accountsoap.dto.account;
//Modify this class with resources/accountservice.xsd

import com.advantage.accountsoap.config.WebServiceConfig;
import com.advantage.accountsoap.dto.IAdminRequest;
import com.advantage.accountsoap.dto.IUserRequest;
import io.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.*;

/**
 * @author Binyamin Regev on on 09/05/2016.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "accountId",
        "base64Token",
        "data"
})
@XmlRootElement(name = "AccountPermanentDeleteRequest", namespace = WebServiceConfig.NAMESPACE_URI)
public class AccountPermanentDeleteRequest implements IAdminRequest {
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private long accountId;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private String base64Token;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private String data;

    public long getAccountId() {
        return accountId;
    }

    public String getData() {
        return data;
    }
    @ApiModelProperty(hidden=true)
    public void setData(String data) {
        this.data = data;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getBase64Token() {
        return base64Token;
    }
    @ApiModelProperty(hidden=true)
    public void setBase64Token(String base64Token) {
        this.base64Token = base64Token;
    }

    @Override
    public String toString() {
        return "AccountPermanentDeleteRequest{" +
                "accountId=" + accountId +
                ", base64Token='" + base64Token + '\'' +
                '}';
    }
}
