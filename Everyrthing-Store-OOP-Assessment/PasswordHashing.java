import java.nio.charset.*;
import java.security.*;


public class PasswordHashing {
    public static String hashPassword(String password){
        String passwordHashed = null;
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes(StandardCharsets.UTF_8));

            byte[] passwordInByte = messageDigest.digest();
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : passwordInByte){
                stringBuilder.append(String.format("%02x", b));
            }
            passwordHashed = stringBuilder.toString();
        }catch (NoSuchAlgorithmException e){
            System.out.println(e.toString());
        }
        return passwordHashed;
    }
}
