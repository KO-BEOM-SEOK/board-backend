package com.example.board.common;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
public class JasyptConfig {
	
	private String key = System.getenv("jasyptKey");
	
	@Bean("jasyptStringEncryptor")
	public StringEncryptor stringEncryptor() {
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		config.setPassword(key);			// 암호화 할 때 사용하는 키
		config.setAlgorithm("PBEWithMD5AndDES");				// 암호화 알고리즘
		config.setPoolSize("1");								// 암호화 인스턴스 pool
		config.setKeyObtentionIterations("1000");				// 반복할 해싱 횟수
		config.setStringOutputType("base64");					// 인코딩 방식
		config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
		config.setIvGeneratorClassName("org.jasypt.iv.NoIvGenerator");
		config.setProviderName("SunJCE");

		encryptor.setConfig(config);
		return encryptor;
		
	}
}