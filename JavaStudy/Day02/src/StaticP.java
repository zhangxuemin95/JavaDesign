public  class StaticP {
    static int age;
    int sex;
    public final int a ;
    public static final int b;
    public StaticP() {
        a = 10;

    }
    static {

        b = 10;
    }

    public final void run(int s, char a) {

    }

    public void run(char a, int s) {

    }
    public void go() {
        age = 10;
    }
    public static void main(String[] args) {
        StaticP staticP = new StaticP();
        System.out.println(staticP.a);
    }
}
