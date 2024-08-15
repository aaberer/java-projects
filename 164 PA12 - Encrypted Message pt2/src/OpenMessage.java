public class OpenMessage implements Message {
    protected String to;
    protected String from;
    protected String subject;
    protected String body;
    
    public OpenMessage(String to, String from, String subject, String body) {
        this.to = to;
        this.from = from;
        this.subject = subject;
        this.body = body;
    }

    public String decrypt(String message, Key key){ //TODO
        String decrypting = "";

        for(int i = 0; i < message.length(); i++){
            char temp = (char)(message.charAt(i) - key.shift);
            decrypting += temp;
        } 
        return decrypting;
    }
    
    public String encrypt(String message, Key key){ //TODO
        String encrypting = "";

        for(int i = 0; i < message.length(); i++){
            char temp2 = (char)(message.charAt(i) + key.shift);
            encrypting += temp2;
        } 
        return encrypting;
    }

    @Override
    public String getBody() {
        return body;
    }
    
    @Override
    public String getTo() {
        return to;
    }
    
    @Override
    public String getSubject() {
        return subject;
    }
    
    @Override
    public String getFrom() {
        return from;
    }

    public static void main(String[] args){ //TODO
        
    }
    
    @Override
    public boolean search(String term, String part) {
        if (term == null || part == null) {
            return false;
        }
        
        return part.toLowerCase().contains(term.toLowerCase());
    }
    
    @Override
    public boolean searchSubject(String term) {
        return search(term, subject);
    }
    
    @Override
    public boolean searchTo(String term) {
        return search(term, to);
    }
    
    @Override
    public boolean searchFrom(String term) {
        return search(term, from);
    }

    public String toString() {
        String s = "TO: " + getTo() + "\n";
        s += "FROM: " + getFrom() + "\n";
        s += "SUBJECT: " + getSubject() + "\n";
        s += "BODY: " + getBody() + "\n";
        return s;
    }
}
