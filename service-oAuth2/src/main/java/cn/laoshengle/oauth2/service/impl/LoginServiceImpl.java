package cn.laoshengle.oauth2.service.impl;

import cn.laoshengle.oauth2.service.ILoginService;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author longjuntao
 * @description: 登录业务实现类
 * @date 2020/8/12 16:28
 */
@Service
public class LoginServiceImpl implements ILoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    /**
     * TODO 用于临时存放所有 Refresh Token，实际情况应该放在 Redis 中
     */
    private static Map<String, String> refreshTokenMaps = new HashMap<>();

    @Value("${security.oauth2.client.access-token-uri}")
    private String accessTokenUri;

    @Value("${security.oauth2.client.client-id}")
    private String clientId;

    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;


    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @Resource(name = "userDetailsServiceBean")
    private UserDetailsService userDetailsService;

    @Override
    public Map<String, String> getToken(String username, String password) {
        Map<String, String> result = new HashMap<>();

        // 验证密码是否正确
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails == null || !passwordEncoder.matches(password, userDetails.getPassword())) {
            //TODO 暂注释
            logger.info("[LoginServiceImpl].[getToken] ------> Get Token Fail");
//            throw new BusinessException(ResponseCode.USER_LOGIN_ERROR);
        }

        // 通过 HTTP 客户端请求登录接口
        Map<String, Object> authParam = getAuthParam();
        authParam.put("username", username);
        authParam.put("password", password);
        authParam.put("grant_type", "password");

        // 获取 access_token
        String strJson = HttpUtil.post(accessTokenUri, authParam);
        JSONObject jsonObject = JSONUtil.parseObj(strJson);
        String token = String.valueOf(jsonObject.get("access_token"));
        String refresh = String.valueOf(jsonObject.get("refresh_token"));
        if (StrUtil.isNotBlank(token) && StrUtil.isNotBlank(refresh)) {
            // 将 refresh_token 保存在服务端
            refreshTokenMaps.put(token, refresh);

            // 将 access_token 返回给客户端
            result.put("token", token);
            return result;
        }

        return null;
    }

    @Override
    public Map<String, String> refresh(String accessToken) {
        Map<String, String> result = new HashMap<>();

        // Access Token 不存在直接返回 null
        String refreshToken = refreshTokenMaps.get(accessToken);
        if (StrUtil.isBlank(refreshToken)) {
            //TODO 暂注释
            logger.info("[LoginServiceImpl].[refresh] ------> Refresh Token Fail");
//            throw new BusinessException(ResponseCode.USER_NOT_LOGGED_IN);
        }

        // 通过 HTTP 客户端请求登录接口
        Map<String, Object> authParam = getAuthParam();
        authParam.put("grant_type", "refresh_token");
        authParam.put("refresh_token", refreshToken);

        // 获取 access_token
        String strJson = HttpUtil.post(accessTokenUri, authParam);
        JSONObject jsonObject = JSONUtil.parseObj(strJson);
        String token = String.valueOf(jsonObject.get("access_token"));
        String refresh = String.valueOf(jsonObject.get("refresh_token"));
        if (StrUtil.isNotBlank(token) && StrUtil.isNotBlank(refresh)) {
            // 删除旧 Token
            refreshTokenMaps.remove(accessToken);

            // 将 refresh_token 保存在服务端
            refreshTokenMaps.put(token, refresh);

            // 将 access_token 返回给客户端
            result.put("token", token);
            return result;
        }

        return null;
    }

    private Map<String, Object> getAuthParam() {
        Map<String, Object> param = new HashMap<>();
        param.put("client_id", clientId);
        param.put("client_secret", clientSecret);
        return param;
    }
}
