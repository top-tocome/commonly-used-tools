package top.tocome.control.permission;

import java.util.ArrayList;

/**
 * 权限管理
 */
public class PermissionManager {
    /**
     * 全局的权限管理
     */
    public static final PermissionManager Global = new PermissionManager();


    public PermissionManager() {

    }

    /**
     * 储存所有用户的权限凭证
     */
    private final ArrayList<Permission> permissions = new ArrayList<>();
    /**
     * 黑名单
     */
    private final ArrayList<Long> blacklist = new ArrayList<>();

    /**
     * 检测权限
     *
     * @param id    用户id
     * @param level 需要的权限级别
     * @return 检查结果
     * @see CheckResult
     */
    public CheckResult check(long id, Permission.Level level) {
        for (Long p : blacklist) {
            if (p == id) {
                return CheckResult.Black;
            }
        }
        for (Permission p : permissions) {
            if (p.getId() == id) {
                if (p.getLevel().compareWith(level)) return CheckResult.Success;
                return CheckResult.LowerLevel;
            }
        }
        return CheckResult.NoPermission;
    }

    /**
     * 权限对比结果
     */
    public enum CheckResult {
        /**
         * 有权限
         */
        Success,
        /**
         * 权限不足
         */
        LowerLevel,
        /**
         * 无权限
         */
        NoPermission,
        /**
         * 已被加入黑名单
         */
        Black
    }
}
