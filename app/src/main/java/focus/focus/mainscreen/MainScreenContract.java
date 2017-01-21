package focus.focus.mainscreen;

public interface MainScreenContract {
    public interface Presenter{
        void setCount();
        void start();
        void checkFirstStart();
        void pause();
    }

    public interface View{
        void beat(int min, int sec, int tiker);
        void initiate(int min, int sec);
        void setCount(int count);
    }

}
