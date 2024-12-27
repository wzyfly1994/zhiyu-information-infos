import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Collections;

/**
 * 实体生产类
 *
 * @author wengzhiyu
 * @date 2020/11/12
 */
public class MybatisPlusGenerator {
    static final String TABLE_NAME = "user,department,role,menu,role_menu,user_role";
    static final String MODULE_NAME = "user";
    static final String AUTHOR = "Jason";

    //修改TABLE_NAME、MODULE_NAME、AUTHOR后运行main方法
    //注意，会覆盖MODULE_NAME下的所有代码
    public static void main(String[] args) {
//        // 代码生成器
//        AutoGenerator autoGenerator = new AutoGenerator();
//
//        // 全局配置
//        GlobalConfig globalConfig = new GlobalConfig();
//        String projectPath = "D:/generator"; //项目目录
//        System.out.println("projectPath -> " + projectPath);
//        //String projectPath = "D://generator";
//        globalConfig.setOutputDir(projectPath + "/src/main/java");
//        globalConfig.setOpen(true);  //生成后打开文件目录
//        globalConfig.setFileOverride(true);  //覆盖文件
//        globalConfig.setBaseResultMap(true);  //生成BaseResultMap
//        globalConfig.setBaseColumnList(true);  //生成BaseColumnList
//        globalConfig.setServiceName("%sService");  //自定义service接口名
//        globalConfig.setDateType(DateType.ONLY_DATE);  //使用java.util.Date
//        globalConfig.setAuthor(AUTHOR);
//        autoGenerator.setGlobalConfig(globalConfig);
//
//        // 数据源配置
//        DataSourceConfig dsc = new DataSourceConfig();
//        // 改数据库连接
//        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/wzy?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false&allowPublicKeyRetrieval=true");
//        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
//        // 改账号密码
//        dsc.setUsername("root");
//        dsc.setPassword("123456");
//        autoGenerator.setDataSource(dsc);
//
//        // 包配置
//        PackageConfig pc = new PackageConfig();
//        // 实体包名
//        pc.setParent("com.zhiyu.security");
//        pc.setServiceImpl("service");  //在service包下生成Impl
//        pc.setXml("mapper");  //在mapper生成xml
//        //pc.setModuleName(MODULE_NAME);
//        autoGenerator.setPackageInfo(pc);
//
//        // 策略配置
//        StrategyConfig strategy = new StrategyConfig();
//        //数据库表映射到实体的命名策略
//        strategy.setNaming(NamingStrategy.underline_to_camel);
//        //数据库表字段映射到实体的命名策略, 未指定按照 naming 执行
//        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        //是否为lombok模型
//        strategy.setEntityLombokModel(true);
//        //生成 @RestController 控制器
//        strategy.setRestControllerStyle(true);
//        // 改表前缀
//        //strategy.setTablePrefix("td");
//        //需要包含的表名，允许正则表达式（与exclude二选一配置）
//        strategy.setInclude(TABLE_NAME);
//        autoGenerator.setStrategy(strategy);
//
//        //autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
//
//        autoGenerator.execute();

        // 全局配置
        GlobalConfig gc = new GlobalConfig.Builder()
                .outputDir("D:\\generator")
                .author(AUTHOR)
                .fileOverride()
                .dateType(DateType.ONLY_DATE)
                .build();
        // 包配置
        PackageConfig pc = new PackageConfig.Builder()
                .parent("com.zhiyu.security")
                // .moduleName(MODULE_NAME)
                .entity("entity.pojo")
                .mapper("mapper")
                .service("service")
                .serviceImpl("service.Impl")
                .controller("controller")
                .xml("mapper")
                .build();
        // 策略配置
        StrategyConfig strategy = new StrategyConfig.Builder()
                //映射的表名
                .addInclude(TABLE_NAME.split(","))
                //策略开启⼤写命名
                .enableCapitalMode()
                .entityBuilder()
                //后缀
                // .formatFileName("%sDO")
                //lombock注解
                .enableLombok()
                // 实体命名策略（小驼峰）
                .columnNaming(NamingStrategy.underline_to_camel)
                .naming(NamingStrategy.underline_to_camel)
                .addTableFills(new Column("create_time", FieldFill.INSERT))
                .addTableFills(new Column("update_time", FieldFill.INSERT_UPDATE))
                .mapperBuilder()
                .enableBaseColumnList()
                .enableBaseResultMap()
                .serviceBuilder()
                .formatServiceFileName("%sService")
                .controllerBuilder().enableRestStyle()
                .build();
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig.Builder("jdbc:mysql://127.0.0.1:3306/wzy?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false&allowPublicKeyRetrieval=true",
                "root", "123456").build();

        // 创建AutoGenerator对象，加载配置
        AutoGenerator autoGenerator = new AutoGenerator(dsc);
        autoGenerator.global(gc);
        autoGenerator.packageInfo(pc);
        autoGenerator.strategy(strategy);

        //执行
        autoGenerator.execute();
        System.out.println("=======  代码生成完毕  ========");


    }
}
