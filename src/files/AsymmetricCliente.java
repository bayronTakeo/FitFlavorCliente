package files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

public class AsymmetricCliente {

    public byte[] cipher(String mensaje) {
        byte[] encodedMessage = null;
        try {
            // Obtener la ruta del directorio de inicio del usuario
            String userHomeDir = System.getProperty("user.home");

            // Leer la clave pública desde el directorio del usuario
            String publicKeyFilePath = Paths.get(userHomeDir, "Public.key").toString();
            byte[] fileKey = fileReader(publicKeyFilePath);

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(fileKey);
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

            // Cifrar el mensaje con la clave pública
            Cipher c = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            c.init(Cipher.ENCRYPT_MODE, publicKey);
            encodedMessage = c.doFinal(mensaje.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodedMessage;
    }

    public byte[] decrypt(byte[] mensaje) {
        byte[] decodedMessage = null;
        try {
            // Obtener la ruta del directorio de inicio del usuario
            String userHomeDir = System.getProperty("user.home");

            // Leer la clave privada desde el directorio del usuario
            String privateKeyFilePath = Paths.get(userHomeDir, "Private.key").toString();
            byte[] fileKey = fileReader(privateKeyFilePath);

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(fileKey);
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

            // Descifrar el mensaje con la clave privada
            Cipher c = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            c.init(Cipher.DECRYPT_MODE, privateKey);
            decodedMessage = c.doFinal(mensaje);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decodedMessage;
    }

    private byte[] fileReader(String path) {
        byte[] ret = null;
        File file = new File(path);
        try {
            ret = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
