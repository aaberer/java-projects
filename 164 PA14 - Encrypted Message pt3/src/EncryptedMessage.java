public interface EncryptedMessage {
    String encrypt(String message, Key key);
    String decrypt(String message, Key key);
}
