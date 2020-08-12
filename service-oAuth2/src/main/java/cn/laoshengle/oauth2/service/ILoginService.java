package cn.laoshengle.oauth2.service;

import java.util.Map;

/**
 * @author longjuntao
 * @description: 登录业务接口
 * @date 2020/8/12 16:27
 */
public interface ILoginService {

    /**
     * 登录成功后仅返回 Token
     * @param username {@code String} 账号
     * @param password {@code String} 密码
     * @return {@code Map<String, String>} key: token
     */
    Map<String, String> getToken(String username, String password);

    /**
     * 刷新 Token
     * @param accessToken {@code String} 使用旧 Token 换新 Token
     * @return {@code Map<String, String>} 新 Token，key: token
     */
    Map<String, String> refresh(String accessToken);
}
