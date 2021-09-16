package top.tocome.control.account;

/**
 * 一个账户信息
 */
public class Account {

    protected Account(long id, String password) {
        this.id = id;
        this.password = password;
    }

    /**
     * 唯一id
     */
    protected long id;

    public long getId() {
        return id;
    }

    /**
     * 密码
     */
    protected String password;

    /**
     * 登录此账号
     */
    public boolean login(String password) {
        return this.password.equals(password);
    }
}
