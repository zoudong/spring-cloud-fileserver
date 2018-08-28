package com.zoudong.fileserver.utils.encode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Base64;

/**
 * @author zd
 * @description class
 * @date 2018/5/8 13:39
 */
@Component
public class KeyUtils {
    @Autowired
    private Environment env;

    public  String  enCode(String sourcePassword)throws Exception{
        String publicKey=env.getProperty("publicKey");
        String privateKey=env.getProperty("privateKey");
        byte[] data = sourcePassword.getBytes();
        byte[] encodedData = RSAUtils.encryptByPublicKey(data, publicKey);
        return Base64.getEncoder().encodeToString(encodedData);
    }
    public  String deCode(String encodedPasswordData)throws Exception{
        String publicKey=env.getProperty("publicKey");
        String privateKey=env.getProperty("privateKey");
        byte[] decodedData = RSAUtils.decryptByPrivateKey(Base64.getDecoder().decode(encodedPasswordData), privateKey);
        return new String(decodedData);
    }
}
