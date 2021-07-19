package com.zhiyu.pay;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * 实体生产类
 *
 * @author wengzhiyu
 * @date 2020/11/12
 */
public class MybatisPlusGenerator {
    static final String TABLE_NAME = "b2b_supplier_info";
    static final String MODULE_NAME = "b2bsupplierinfo";
    static final String AUTHOR = "wengzhiyu";

    //修改TABLE_NAME、MODULE_NAME、AUTHOR后运行main方法
    //注意，会覆盖MODULE_NAME下的所有代码
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();

        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        String projectPath = "D:/polyWeb1117/zhiyu-information-info/zhiyu-pay-service"; //项目目录
        System.out.println("11"+projectPath);
        //String projectPath = "D://generator";
        globalConfig.setOutputDir(projectPath + "/src/main/java");
        globalConfig.setOpen(true);  //生成后打开文件目录
        globalConfig.setFileOverride(true);  //覆盖文件
        globalConfig.setBaseResultMap(true);  //生成BaseResultMap
        globalConfig.setBaseColumnList(true);  //生成BaseColumnList
        globalConfig.setServiceName("%sService");  //自定义service接口名
        globalConfig.setDateType(DateType.ONLY_DATE);  //使用java.util.Date
        globalConfig.setAuthor(AUTHOR);
        autoGenerator.setGlobalConfig(globalConfig);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        // 改数据库连接
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/wzy?useUnicode=true&useSSL=false&characterEncoding=utf8");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        // 改账号密码
        dsc.setUsername("root");
        dsc.setPassword("123456");
        autoGenerator.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        // 实体包名
        pc.setParent("com.zhiyu.pay.entity.pojo");
        pc.setServiceImpl("service");  //在service包下生成Impl
        pc.setXml("mapper");  //在mapper生成xml
        pc.setModuleName(MODULE_NAME);
        autoGenerator.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        //数据库表映射到实体的命名策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //数据库表字段映射到实体的命名策略, 未指定按照 naming 执行
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //是否为lombok模型
        strategy.setEntityLombokModel(true);
        //生成 @RestController 控制器
        strategy.setRestControllerStyle(true);
        // 改表前缀
        //strategy.setTablePrefix("td");
        //需要包含的表名，允许正则表达式（与exclude二选一配置）
        strategy.setInclude(TABLE_NAME);
        autoGenerator.setStrategy(strategy);

        //autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());

        autoGenerator.execute();

    }
}
