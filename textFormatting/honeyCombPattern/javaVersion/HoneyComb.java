public class HoneyComb {

    private int width = 10;
    private int height  = 10;

    private int toggle = 0;

    private String firstPattern = "/ \\_";
    private String secondPattern = "\\_/ ";

    public HoneyComb(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    public void run() {
        print();
    }

    public void print() {
        for (int i = 0; i < height * 2; i++) {
            System.out.println("");
            for (int j = 0; j < width; j++) {
                switch (toggle) {
                    case 0 -> System.out.print(firstPattern);
                    case 1 -> System.out.print(secondPattern);
                }
            }
            toggle = 1 - toggle;
        }
    }
}
