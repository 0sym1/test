package utilz;

public class Contants {
    public static class Direction{
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }
    public static class PlayerConstants{
        public static final int IDLE = 0;
        public static final int WALKING = 1;
        public static final int RUNNING = 2;
        public static final int JUMPING = 3;

        public static int getSpriteAmount(int playerAction){
            switch (playerAction){
                case IDLE -> {
                    return 6;
                }
                case WALKING -> {
                    return 23;
                }
                case RUNNING -> {
                    return 37;
                }
                case JUMPING -> {
                    return 15;
                }
                default -> {
                    return -1;
                }
            }
        }
    }
}
