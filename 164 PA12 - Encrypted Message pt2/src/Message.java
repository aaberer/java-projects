public interface Message { 
    //TODO
    //Provided interface containing methods you *must* 
    //implement in OpenMessage.java
    
    String getBody();
    String getTo();
    String getSubject();
    String getFrom();

    boolean search(String term, String part);
    boolean searchSubject(String term);
    boolean searchTo(String term);
    boolean searchFrom(String term);

}
