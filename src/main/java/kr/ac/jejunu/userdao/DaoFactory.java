package kr.ac.jejunu.userdao;

public class DaoFactory {
    ConnectionMaker connectionMaker;
    public DaoFactory(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public UserDao getUserDao() {
        return new UserDao(this.connectionMaker);
    }

}
