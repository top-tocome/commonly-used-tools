package top.tocome.control.account;

import java.util.ArrayList;

/**
 * 账号系统
 */
public abstract class AccountSystem<T extends Account>  {

    protected ArrayList<T> accounts = new ArrayList<>();

    /**
     * 登录
     */
    public boolean login(long id, String password) {
        T a = getAccount(id);
        return a != null && a.login(password);
    }

    /**
     * 注册一个账号
     */
    public void register(long id, String password) {
        accounts.add(newAccount(id, password));
    }

    /**
     * 创建一个账号
     */
    protected abstract T newAccount(long id, String password);

    /**
     * 获取账户
     */
    public T getAccount(long id) {
        for (T a : accounts) {
            if (a.getId() == id) return a;
        }
        return null;
    }
}
