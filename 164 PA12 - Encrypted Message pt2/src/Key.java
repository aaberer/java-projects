public class Key extends Object{
    int id;
    int shift;

    Key(){
        this.id = 0;
        this.shift = 0;
    }

    Key(int shift){
        this.id = 0;
        this.shift = shift;
    }
    
    Key(int id, int shift){
        this.id = id;
        this.shift = shift;
    }
}
