public interface Message {

    String getBody();
    String getTo();
    String getSubject();
    String getFrom();

    boolean search(String term, String part);
    boolean searchSubject(String term);
    boolean searchTo(String term);
    boolean searchFrom(String term);

}
