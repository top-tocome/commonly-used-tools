package top.tocome.control.permission;

/**
 * 权限凭证
 */
public class Permission {
    /**
     * 权限id,用户唯一标识
     */
    private final long id;
    /**
     * 权限级别
     */
    private final Level level;

    /**
     * 注册一个权限凭证
     *
     * @param id    {@link #id}
     * @param level {@link #level}
     */
    public Permission(long id, Level level) {
        this.id = id;
        this.level = level;
    }

    public long getId() {
        return id;
    }

    public Level getLevel() {
        return level;
    }

    /**
     * 权限级别
     */
    public static class Level {
        /**
         * 最高权限级别
         */
        public static final Level Root = new Level("Root", 0);
        /**
         * 管理员权限
         */
        public static final Level Admin = new Level("Admin", 100);
        /**
         * 用户权限
         */
        public static final Level User = new Level("User", 200);
        /**
         * 访客
         */
        public static final Level Visitor = new Level("Visitor", 300);
        /**
         * 加入黑名单
         */
        public static final Level Black = new Level("Black", Integer.MAX_VALUE);
        /**
         * 别名
         */
        private final String name;
        /**
         * 权限值，值越小权限越大<br>
         * 范围：[0,{@link Integer#MAX_VALUE}]<br>
         * if(value<0) value=0;
         */
        private final int value;

        /**
         * 创建一个新的权限级别，建议设为常量
         *
         * @param name  {@link #name}
         * @param value {@link #value}
         */
        public Level(String name, int value) {
            this.name = name;
            this.value = Math.max(value, 0);
        }

        public String getName() {
            return name;
        }

        public int getValue() {
            return value;
        }

        /**
         * 权限比较
         *
         * @param level 被比较的级别
         * @return true-前者级别更高或相等   false-后者级别更高
         */
        public boolean compareWith(Level level) {
            return getValue() <= level.getValue();
        }

        /**
         * 判断权限级别是否相同
         */
        public boolean equals(Level level) {
            return getValue() == level.getValue();
        }
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
