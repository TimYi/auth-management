package com.shz.project.domain.system;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha1Hash;

/**
 * 用于密码的加密和解密
 * @author pc
 *
 */
public class EncryptService {
	
	public static final String HASH_ALGORITHM = "SHA-1";	
	public static final int HASH_INTERATIONS = 1024;
	public static final HashedCredentialsMatcher CREDENTIALS_MATCHER;
	
	static {
		CREDENTIALS_MATCHER=new HashedCredentialsMatcher(HASH_ALGORITHM);
		CREDENTIALS_MATCHER.setHashIterations(HASH_INTERATIONS);
	}

	public static String generateSalt() {
		String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
		return salt;
	}
	
	public static String encryptPassword(Object plainPassword, String salt) {
		String sha1=new Sha1Hash(plainPassword, salt, HASH_INTERATIONS).toHex();
		return sha1;
	}
	
	public static boolean rightPassword(String plainPassword, String salt, String password) {
		String calculatedPassword=encryptPassword(plainPassword, salt);
		return (calculatedPassword.equals(password));
	}
}
