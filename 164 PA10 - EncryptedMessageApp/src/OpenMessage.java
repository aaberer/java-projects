//  Andrew Aberer | aaberer@colosate.edu
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
}
