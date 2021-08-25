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

    private PermissionManager() {
        this.name = "GlobalPermissionManager";
    }

    public PermissionManager(String name) {
        this.name = name;
        parent = Global;
    }

    public PermissionManager(String name, PermissionManager parent) {
        this.name = name;
        this.parent = parent == null ? Global : parent;
    }

    /**
     * 权限域名称
     */
    private final String name;

    public String getName() {
        return name;
    }

    /**
     * 父级权限域,用户权限继承父级
     */
    private PermissionManager parent = null;

    /**
     * 储存所有用户的权限凭证
     */
    private final ArrayList<Permission> permissions = new ArrayList<>();

    /**
     * 检测权限
     *
     * @param id    用户id
     * @param level 需要的权限级别
     * @return 检查结果
     * @see Permission.CheckResult
     */
    public Permission.CheckResult check(long id, Permission.Level level) {
        for (Permission p : permissions) {
            if (p.getId() == id) {
                if (p.getLevel().equals(Permission.Level.Black)) return Permission.CheckResult.Black;
                if (p.getLevel().compareWith(level)) return Permission.CheckResult.Success;
                return Permission.CheckResult.LowerLevel;
            }
        }
        if (parent != null) return parent.check(id, level);
        return Permission.CheckResult.NoPermission;
    }

    public void addPermission(Permission permission) {
        permissions.add(permission);
    }
}
