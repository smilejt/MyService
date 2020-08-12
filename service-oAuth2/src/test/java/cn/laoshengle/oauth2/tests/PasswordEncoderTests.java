package cn.laoshengle.oauth2.tests;

import cn.laoshengle.oauth2.AuthApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author longjuntao
 * @description:
 * @date 2020/8/8 14:03
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthApplication.class)
public class PasswordEncoderTests {

    private static final Logger logger = LoggerFactory.getLogger(PasswordEncoderTests.class);

    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void testPasswordEncoder() {
        logger.info("dashboard = {}", passwordEncoder.encode("dashboard"));
        logger.info("portal = {}", passwordEncoder.encode("portal"));
    }
}
