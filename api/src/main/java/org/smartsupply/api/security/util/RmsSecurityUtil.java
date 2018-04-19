package org.smartsupply.api.security.util;

import org.smartsupply.api.security.RmsUserDetails;
import org.smartsupply.model.admin.User;
import org.smartsupply.model.exception.RmsSessionExpiredException;
import org.smartsupply.model.exception.RmsUnexpectedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import static org.smartsupply.model.util.MyStringUtil.isBlankOrEmpty;
import static org.smartsupply.model.util.MyStringUtil.isNotBlankOrEmpty;

/**
 * Understands generation of a SHA-1 hash of a user's password
 */
public final class RmsSecurityUtil {

    /*
     * private static final String RNG_ALGORITHM = "SHA1PRNG";
	 */

    /**
     * message digest algorithm (must be sufficiently long to provide the key
     * and initialization vector)
     */
    private static final String DIGEST_ALGORITHM = "SHA-256";

    /**
     * key algorithm (must be compatible with CIPHER_ALGORITHM)
     */
    private static final String KEY_ALGORITHM = "AES";

    /**
     * key algorith (must be compatible with CIPHER_ALGORITHM
     */
    @SuppressWarnings("unused")
    private static final String KEY_ALGORITHM_2 = "DES";

    /**
     * cipher algorithm (must be compatible with KEY_ALGORITHM)
     */
    // private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String CIPHER_ALGORITHM = "AES/CTR/NoPadding";

    /**
     * cipher algorithm (must be compatible with KEY_ALGORITHM)
     */
    @SuppressWarnings("unused")
    private static final String CIPHER_ALGORITHM_2 = "DES/CBC/PKCS5Padding";

    private static Logger log = LoggerFactory.getLogger(RmsSecurityUtil.class);

    private RmsSecurityUtil() {

    }

    /**
     * This method will hash <code>strToEncode</code> using the preferred
     * algorithm. Currently, Mohr's preferred algorithm is hard coded to be
     * SHA-1.
     *
     * @param strToEncode string to encode
     * @return the SHA-1 encryption of a given string
     */
    public static String encodeString(String strToEncode) {
        try {
            String algorithm = "SHA1";
            MessageDigest md = MessageDigest.getInstance(algorithm);
            /*
             * pick a character encoding that doesn't rely on the platform
			 * default
			 */
            byte[] input = strToEncode.getBytes();
            return hexString(md.digest(input));
        } catch (NoSuchAlgorithmException ex) {
            throw new RmsUnexpectedException(ex);
        }
    }

    /**
     * encodes a string using the platform default character encoding
     * <blockquote>This method is here only for backward compatibility because
     * it calls the buggy hexString2</blockquote>
     */
    public static String encodeString2(String strToEncode) {
        try {
            String algorithm = "SHA1";
            MessageDigest md = MessageDigest.getInstance(algorithm);

			/*
             * we use platform default character encoding
			 */
            byte[] input = strToEncode.getBytes();
            return hexString2(md.digest(input));
        } catch (NoSuchAlgorithmException ex) {
            throw new RmsUnexpectedException(ex);
        }
    }

    /**
     * Convenience method to convert a byte array to a string <br/>
     * <strong>This method is here only for backward compatibility because its
     * buggy.</strong>
     *
     * @param b Byte array to convert to HexString
     * @return Hexidecimal based string
     */
    private static String hexString2(byte[] b) {
        if (b == null || b.length < 1) {
            return "";
        }
        StringBuffer s = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            s.append(Integer.toHexString(b[i] & 0xFF));
        }
        return new String(s);
    }

    /**
     * Convenience method to convert a byte array to a string. This solves a bug
     * in the above method.
     *
     * @param b
     * @return
     */
    private static String hexString(byte[] b) {
        StringBuffer buf = new StringBuffer();
        char[] hexChars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        int len = b.length;
        int high = 0;
        int low = 0;
        for (int i = 0; i < len; i++) {
            high = ((b[i] & 0xf0) >> 4);
            low = (b[i] & 0x0f);
            buf.append(hexChars[high]);
            buf.append(hexChars[low]);
        }

        return buf.toString();
    }

    /**
     * Sets the <code>Spring security context</code> with the current
     * <code>User</code> authentication details.
     *
     * @param userDetails - User to place in security context.
     */
    public static void setSecurityContext(RmsUserDetails userDetails) {
        User user = userDetails.getSystemUser();

        // Proceed to put the User in the spring security Context.
        SecurityContext sc = new SecurityContextImpl();
        Authentication auth = new UsernamePasswordAuthenticationToken(user,
                user.getPassword(), userDetails.getAuthorities());
        sc.setAuthentication(auth);
        SecurityContextHolder.setContext(sc);

        log.info("Successfully logged in User: << " + user.getUsername()
                + " >> ");
        log.info("<< " + "Setting User:" + user.getUsername() + " in Context"
                + ">> ");
    }

    public static User getLoggedInUser() throws RmsSessionExpiredException {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context != null) {
            Authentication auth = context.getAuthentication();
            if (auth != null) {
                User user;
                if (auth.getPrincipal() instanceof RmsUserDetails) {
                    RmsUserDetails userDetails = (RmsUserDetails) auth.getPrincipal();
                    user = userDetails.getSystemUser();
                } else if (auth.getPrincipal() instanceof User) {
                    user = (User) auth.getPrincipal();
                } else {
                    log.debug("Auth not an instance of RmsUserDetails - i.e. no logged in user. Auth=" + auth);
                    throw new RmsSessionExpiredException("Could not find logged in user");
                }
                return user;
            }
        }

        log.debug("No Spring SecurityContext or Authentication - i.e. no logged in user");
        throw new RmsSessionExpiredException("Could not find logged in user");
    }

    public static String getLoggedInUserId() throws Exception {
        User user = getLoggedInUser();
        if(user==null){
            throw new Exception("Logged in user not found");
        }
        return user.getId();
    }

    /**
     * check the given user if they have a clear text password and then creates
     * a hashed password for them to use and assign them a salt as well
     *
     * @param user
     */
    public static void prepUserCredentials(User user) {
        if (isBlankOrEmpty(user.getSalt())) {
            user.setSalt(UUID.randomUUID().toString());
        }
        if (isNotBlankOrEmpty(user.getClearTextPassword())) {
            user.setPassword(encodeString(user.getClearTextPassword() + user.getSalt()));
        }
    }

}
