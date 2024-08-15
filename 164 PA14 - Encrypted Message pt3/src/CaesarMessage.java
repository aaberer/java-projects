public class CaesarMessage extends OpenMessage implements EncryptedMessage{ //TODO

    protected Key messageKey;

    public CaesarMessage(String to, String from, String subject, String body, Key key){
        this.to = to;
        this.from = from;
        this.subject = subject;
        this.body = body;
    }
    public String encrypt(String message, Key key){
        String encrypting = "";

        for(int i = 0; i < message.length(); i++){
            char temp2 = (char)(message.charAt(i) + key.shift);
            encrypting += temp2;
        }
        return encrypting;
    }
    public String decrypt(String message, Key key){
        String decrypting = "";

        for(int i = 0; i < message.length(); i++){
            char temp = (char)(message.charAt(i) - key.shift);
            decrypting += temp;
        }
        return decrypting;
    }

    public static void main(String[] args){

    }
}
