
import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;


public class ExcelUtil {

    public static void main(String[] args) {
        try {
            // 创建工作簿
            Workbook workbook = new XSSFWorkbook();
            int rowNum = 0;

            // 创建工作表
            Sheet sheet = workbook.createSheet("基准岗位列表");


            String[] secondHeaderVal = {"岗位类别", "岗位序列",
                    "子序列", "岗位族", "基准岗位", "职位名称", "一级组织", "二级组织", "三级组织"
                    , "四级组织"};
            for (int i = 0; i < secondHeaderVal.length; i++) {
                sheet.setColumnWidth(i, 20 * 256);
            }

            // 创建第一行
            Row titleRow = sheet.createRow(rowNum);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("基准岗位列表");

            // 合并第一行单元格
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, secondHeaderVal.length - 1));

            // 创建第二行
            Row headerRow = sheet.createRow(rowNum += 1);

            for (int i = 0; i < secondHeaderVal.length; i++) {
                Cell headerCell = headerRow.createCell(i);
                headerCell.setCellValue(secondHeaderVal[i]);
            }
            String deptId = "";

            String str = "{ \"jobCategoryList\": [ { \"id\": \"rdEAAAAJVq32V1KN\", \"jobSequenceList\": [ { \"childSequenceList\": [ { \"id\": \"rdEAAAAKE8+jdRE7\", \"jobFamilyList\": [ { \"id\": \"rdEAAAAKFU2p9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAKFyi4hF0d\", \"jobId\": \"rdEAAAAKFyd1cCSY\", \"name\": \"内审岗\", \"number\": \"75\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKFyu4hF0d\", \"jobId\": \"rdEAAAAKFyp1cCSY\", \"name\": \"内控岗\", \"number\": \"76\", \"positionInfoList\": [] } ], \"name\": \"风险管控\", \"number\": \"33\" }, { \"id\": \"rdEAAAAKFUqp9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAKFyW4hF0d\", \"jobId\": \"rdEAAAAKFyR1cCSY\", \"name\": \"投资岗\", \"number\": \"74\", \"positionInfoList\": [] } ], \"name\": \"投资者关系\", \"number\": \"32\" }, { \"id\": \"rdEAAAAKFVap9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAKFze4hF0d\", \"jobId\": \"rdEAAAAKFzZ1cCSY\", \"name\": \"知识产权岗\", \"number\": \"80\", \"positionInfoList\": [] } ], \"name\": \"知识产权\", \"number\": \"36\" }, { \"id\": \"rdEAAAAKFVCp9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAKFy64hF0d\", \"jobId\": \"rdEAAAAKFy11cCSY\", \"name\": \"证券事务岗\", \"number\": \"77\", \"positionInfoList\": [] } ], \"name\": \"证券事务\", \"number\": \"34\" }, { \"id\": \"rdEAAAAKFVmp9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAKF0a4hF0d\", \"jobId\": \"rdEAAAAKF0V1cCSY\", \"name\": \"资金岗\", \"number\": \"85\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKF0C4hF0d\", \"jobId\": \"rdEAAAAKFz91cCSY\", \"name\": \"信控岗\", \"number\": \"83\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKF0m4hF0d\", \"jobId\": \"rdEAAAAKF0h1cCSY\", \"name\": \"预算岗\", \"number\": \"86\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKF0O4hF0d\", \"jobId\": \"rdEAAAAKF0J1cCSY\", \"name\": \"税务岗\", \"number\": \"84\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKFz24hF0d\", \"jobId\": \"rdEAAAAKFzx1cCSY\", \"name\": \"会计岗\", \"number\": \"82\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKFzq4hF0d\", \"jobId\": \"rdEAAAAKFzl1cCSY\", \"name\": \"财务分析岗\", \"number\": \"81\", \"positionInfoList\": [] } ], \"name\": \"财务管理\", \"number\": \"37\" }, { \"id\": \"rdEAAAAKFVOp9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAKFzG4hF0d\", \"jobId\": \"rdEAAAAKFzB1cCSY\", \"name\": \"法务岗\", \"number\": \"78\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKFzS4hF0d\", \"jobId\": \"rdEAAAAKFzN1cCSY\", \"name\": \"机要管理岗\", \"number\": \"79\", \"positionInfoList\": [] } ], \"name\": \"法务合规\", \"number\": \"35\" } ], \"name\": \"财经与风控\", \"number\": \"21\" }, { \"id\": \"rdEAAAAKE96jdRE7\", \"jobFamilyList\": [ { \"id\": \"rdEAAAAKFW6p9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAKF5S4hF0d\", \"jobId\": \"rdEAAAAKF5N1cCSY\", \"name\": \"流程管理岗\", \"number\": \"111\", \"positionInfoList\": [] } ], \"name\": \"流程管理\", \"number\": \"44\" }, { \"id\": \"rdEAAAAKFWup9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAKF5G4hF0d\", \"jobId\": \"rdEAAAAKF5B1cCSY\", \"name\": \"经营管理岗\", \"number\": \"110\", \"positionInfoList\": [] } ], \"name\": \"经营管理\", \"number\": \"43\" }, { \"id\": \"rdEAAAAKFXep9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAKF6C4hF0d\", \"jobId\": \"rdEAAAAKF591cCSY\", \"name\": \"行政岗\", \"number\": \"115\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKF6O4hF0d\", \"jobId\": \"rdEAAAAKF6J1cCSY\", \"name\": \"助理岗\", \"number\": \"116\", \"positionInfoList\": [ { \"org\": { \"id\": \"rdEAAAAABkLM567U\", \"name\": \"总裁办\", \"number\": \"108.006\", \"orgName1\": \"管理中心\", \"orgName2\": \"总裁办\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"n8KdC7jDRNGBaICHyVzLRHSuYS4=\", \"name\": \"总裁助理\", \"number\": \"ZW0000159\" } } ] } ], \"name\": \"行政管理\", \"number\": \"47\" }, { \"id\": \"rdEAAAAKFXGp9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAKF5e4hF0d\", \"jobId\": \"rdEAAAAKF5Z1cCSY\", \"name\": \"项目管理岗\", \"number\": \"112\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKF5q4hF0d\", \"jobId\": \"rdEAAAAKF5l1cCSY\", \"name\": \"项目申报岗\", \"number\": \"113\", \"positionInfoList\": [] } ], \"name\": \"项目管理\", \"number\": \"45\" }, { \"id\": \"rdEAAAAKFXSp9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAKF524hF0d\", \"jobId\": \"rdEAAAAKF5x1cCSY\", \"name\": \"文控岗\", \"number\": \"114\", \"positionInfoList\": [] } ], \"name\": \"文控\", \"number\": \"46\" } ], \"name\": \"综合管理\", \"number\": \"26\" }, { \"id\": \"rdEAAAAKE9ijdRE7\", \"jobFamilyList\": [ { \"id\": \"rdEAAAAKFWKp9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAJcfu4hF0d\", \"jobId\": \"rdEAAAAJcfp1cCSY\", \"name\": \"6669\", \"number\": \"1\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKF224hF0d\", \"jobId\": \"rdEAAAAKF2x1cCSY\", \"name\": \"组织发展岗\", \"number\": \"98\", \"positionInfoList\": [ { \"org\": { \"id\": \"rdEAAAAABrDM567U\", \"name\": \"OD&COE部\", \"number\": \"110.005\", \"orgName1\": \"人力资源中心\", \"orgName2\": \"OD&COE部\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"I1Ol/MymQ5W0Rf6hBaT5Z3SuYS4=\", \"name\": \"组织发展高级专员\", \"number\": \"ZW0000506\" } }, { \"org\": { \"id\": \"rdEAAAAABrDM567U\", \"name\": \"OD&COE部\", \"number\": \"110.005\", \"orgName1\": \"人力资源中心\", \"orgName2\": \"OD&COE部\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"obPV7mhARiisKkgodgZENnSuYS4=\", \"name\": \"组织发展经理\", \"number\": \"ZW0000507\" } }, { \"org\": { \"id\": \"rdEAAAAADOvM567U\", \"name\": \"组织和人才发展组\", \"number\": \"110.005.002\", \"orgName1\": \"人力资源中心\", \"orgName2\": \"OD&COE部\", \"orgName3\": \"组织和人才发展组\", \"orgName4\": \"\" }, \"position\": { \"id\": \"mTwYehYnTk6Q4Rvqb0HD5HSuYS4=\", \"name\": \"组织发展专业经理\", \"number\": \"ZW0002532\" } } ] }, { \"id\": \"rdEAAAAKF2e4hF0d\", \"jobId\": \"rdEAAAAKF2Z1cCSY\", \"name\": \"HRBP岗\", \"number\": \"96\", \"positionInfoList\": [ { \"org\": { \"id\": \"rdEAAAAABp7M567U\", \"name\": \"HRBP部\", \"number\": \"110.002\", \"orgName1\": \"人力资源中心\", \"orgName2\": \"HRBP部\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"vYV9AwRzS/KHOBMGEgWqMXSuYS4=\", \"name\": \"海外HRBP\", \"number\": \"ZW0000496\" } }, { \"org\": { \"id\": \"rdEAAAAABp7M567U\", \"name\": \"HRBP部\", \"number\": \"110.002\", \"orgName1\": \"人力资源中心\", \"orgName2\": \"HRBP部\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"ODjDxli5RsmFN+k8IzjgHnSuYS4=\", \"name\": \"HRBP-研发\", \"number\": \"ZW0000499\" } }, { \"org\": { \"id\": \"rdEAAAAADKrM567U\", \"name\": \"产品业务线组\", \"number\": \"110.002.002\", \"orgName1\": \"人力资源中心\", \"orgName2\": \"HRBP部\", \"orgName3\": \"产品业务线组\", \"orgName4\": \"\" }, \"position\": { \"id\": \"0ARLMvsiSGCo+Bha9H5rdnSuYS4=\", \"name\": \"HRBP-产品业务线\", \"number\": \"ZW0002332\" } }, { \"org\": { \"id\": \"rdEAAAAADKrM567U\", \"name\": \"产品业务线组\", \"number\": \"110.002.002\", \"orgName1\": \"人力资源中心\", \"orgName2\": \"HRBP部\", \"orgName3\": \"产品业务线组\", \"orgName4\": \"\" }, \"position\": { \"id\": \"HriRHG4fQae9XE5f1VAUwXSuYS4=\", \"name\": \"HRBP-营销\", \"number\": \"ZW0002530\" } }, { \"org\": { \"id\": \"rdEAAAAADLPM567U\", \"name\": \"制造运营中心组\", \"number\": \"110.002.003\", \"orgName1\": \"人力资源中心\", \"orgName2\": \"HRBP部\", \"orgName3\": \"制造运营中心组\", \"orgName4\": \"\" }, \"position\": { \"id\": \"g73RwAZxStuRaO7N4JM4M3SuYS4=\", \"name\": \"HRBP-制造\", \"number\": \"ZW0002258\" } }, { \"org\": { \"id\": \"rdEAAAAADMXM567U\", \"name\": \"全球营销中心组\", \"number\": \"110.002.005\", \"orgName1\": \"人力资源中心\", \"orgName2\": \"HRBP部\", \"orgName3\": \"全球营销中心组\", \"orgName4\": \"\" }, \"position\": { \"id\": \"Q3bV2cVCRbezR05r6ey+/nSuYS4=\", \"name\": \"HRBP\", \"number\": \"ZW0000494\" } }, { \"org\": { \"id\": \"rdEAAAAADMXM567U\", \"name\": \"全球营销中心组\", \"number\": \"110.002.005\", \"orgName1\": \"人力资源中心\", \"orgName2\": \"HRBP部\", \"orgName3\": \"全球营销中心组\", \"orgName4\": \"\" }, \"position\": { \"id\": \"vWdMLcsuQm253blXq1PuaXSuYS4=\", \"name\": \"海外HRBP\", \"number\": \"ZW0000495\" } }, { \"org\": { \"id\": \"rdEAAAAADMXM567U\", \"name\": \"全球营销中心组\", \"number\": \"110.002.005\", \"orgName1\": \"人力资源中心\", \"orgName2\": \"HRBP部\", \"orgName3\": \"全球营销中心组\", \"orgName4\": \"\" }, \"position\": { \"id\": \"LsJY0YLrSMepByw8vqUIY3SuYS4=\", \"name\": \"HRBP-营销\", \"number\": \"ZW0002296\" } } ] }, { \"id\": \"rdEAAAAKF2q4hF0d\", \"jobId\": \"rdEAAAAKF2l1cCSY\", \"name\": \"SSC岗\", \"number\": \"97\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKF2S4hF0d\", \"jobId\": \"rdEAAAAKF2N1cCSY\", \"name\": \"招聘岗\", \"number\": \"95\", \"positionInfoList\": [ { \"org\": { \"id\": \"rdEAAAAADI7M567U\", \"name\": \"国内社招组\", \"number\": \"110.001.002\", \"orgName1\": \"人力资源中心\", \"orgName2\": \"招聘部\", \"orgName3\": \"国内社招组\", \"orgName4\": \"\" }, \"position\": { \"id\": \"j934GiCgRAOVIAFJoaHbTnSuYS4=\", \"name\": \"高级招聘专员\", \"number\": \"ZW0000571\" } }, { \"org\": { \"id\": \"rdEAAAAADI7M567U\", \"name\": \"国内社招组\", \"number\": \"110.001.002\", \"orgName1\": \"人力资源中心\", \"orgName2\": \"招聘部\", \"orgName3\": \"国内社招组\", \"orgName4\": \"\" }, \"position\": { \"id\": \"TygTlsN9RAeA24ZPHcfjynSuYS4=\", \"name\": \"招聘主管\", \"number\": \"ZW0000572\" } }, { \"org\": { \"id\": \"rdEAAAAADJfM567U\", \"name\": \"海外招聘组\", \"number\": \"110.001.003\", \"orgName1\": \"人力资源中心\", \"orgName2\": \"招聘部\", \"orgName3\": \"海外招聘组\", \"orgName4\": \"\" }, \"position\": { \"id\": \"jr9BmgchRIeW5smFoAMcUnSuYS4=\", \"name\": \"高级招聘专员\", \"number\": \"ZW0000490\" } }, { \"org\": { \"id\": \"rdEAAAAADJfM567U\", \"name\": \"海外招聘组\", \"number\": \"110.001.003\", \"orgName1\": \"人力资源中心\", \"orgName2\": \"招聘部\", \"orgName3\": \"海外招聘组\", \"orgName4\": \"\" }, \"position\": { \"id\": \"dwVkDE/XTxeTO/aMyg7cA3SuYS4=\", \"name\": \"高级招聘顾问\", \"number\": \"ZW0002347\" } } ] }, { \"id\": \"rdEAAAAKF3a4hF0d\", \"jobId\": \"rdEAAAAKF3V1cCSY\", \"name\": \"培训岗\", \"number\": \"101\", \"positionInfoList\": [ { \"org\": { \"id\": \"rdEAAAAAEUnM567U\", \"name\": \"制造周边科\", \"number\": \"107.003.007.005\", \"orgName1\": \"制造运营中心\", \"orgName2\": \"制造部\", \"orgName3\": \"制造三处\", \"orgName4\": \"制造周边科\" }, \"position\": { \"id\": \"Y7DZEvngSe+4Dsavuk0DV3SuYS4=\", \"name\": \"培训主管\", \"number\": \"ZW0001138\" } }, { \"org\": { \"id\": \"rdEAAAAABrnM567U\", \"name\": \"企培部\", \"number\": \"110.006\", \"orgName1\": \"人力资源中心\", \"orgName2\": \"企培部\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"iLfT21/4RE6EMo+MNVsc2HSuYS4=\", \"name\": \"培训经理\", \"number\": \"ZW0000528\" } }, { \"org\": { \"id\": \"rdEAAAAADP7M567U\", \"name\": \"培训组\", \"number\": \"110.006.002\", \"orgName1\": \"人力资源中心\", \"orgName2\": \"企培部\", \"orgName3\": \"培训组\", \"orgName4\": \"\" }, \"position\": { \"id\": \"k9HfMO9AS7SFg3alOVFvOHSuYS4=\", \"name\": \"培训专员\", \"number\": \"ZW0000520\" } }, { \"org\": { \"id\": \"rdEAAAAADP7M567U\", \"name\": \"培训组\", \"number\": \"110.006.002\", \"orgName1\": \"人力资源中心\", \"orgName2\": \"企培部\", \"orgName3\": \"培训组\", \"orgName4\": \"\" }, \"position\": { \"id\": \"AdKzJGuBREWxTMxt9EskkXSuYS4=\", \"name\": \"培训主管\", \"number\": \"ZW0000522\" } }, { \"org\": { \"id\": \"rdEAAAAADP7M567U\", \"name\": \"培训组\", \"number\": \"110.006.002\", \"orgName1\": \"人力资源中心\", \"orgName2\": \"企培部\", \"orgName3\": \"培训组\", \"orgName4\": \"\" }, \"position\": { \"id\": \"6e4iduyiRNecp+30xLzvDXSuYS4=\", \"name\": \"高级培训专员\", \"number\": \"ZW0000523\" } } ] }, { \"id\": \"rdEAAAAKF3C4hF0d\", \"jobId\": \"rdEAAAAKF291cCSY\", \"name\": \"薪酬福利岗\", \"number\": \"99\", \"positionInfoList\": [ { \"org\": { \"id\": \"rdEAAAAADOHM567U\", \"name\": \"薪酬和绩效组\", \"number\": \"110.005.001\", \"orgName1\": \"人力资源中心\", \"orgName2\": \"OD&COE部\", \"orgName3\": \"薪酬和绩效组\", \"orgName4\": \"\" }, \"position\": { \"id\": \"YqSZlEjSRpOqzFomINcS5nSuYS4=\", \"name\": \"薪酬福利专员\", \"number\": \"ZW0000504\" } }, { \"org\": { \"id\": \"rdEAAAAADOHM567U\", \"name\": \"薪酬和绩效组\", \"number\": \"110.005.001\", \"orgName1\": \"人力资源中心\", \"orgName2\": \"OD&COE部\", \"orgName3\": \"薪酬和绩效组\", \"orgName4\": \"\" }, \"position\": { \"id\": \"FJQYdh2dSmy5OvhKtqWT83SuYS4=\", \"name\": \"海外薪酬福利经理\", \"number\": \"ZW0002281\" } } ] }, { \"id\": \"rdEAAAAKF3O4hF0d\", \"jobId\": \"rdEAAAAKF3J1cCSY\", \"name\": \"企业文化岗\", \"number\": \"100\", \"positionInfoList\": [] } ], \"name\": \"人力资源\", \"number\": \"40\" } ], \"name\": \"人力资源\", \"number\": \"24\" }, { \"id\": \"rdEAAAAKE9KjdRE7\", \"jobFamilyList\": [ { \"id\": \"rdEAAAAKFVyp9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAKF0+4hF0d\", \"jobId\": \"rdEAAAAKF051cCSY\", \"name\": \"基础运维岗\", \"number\": \"88\", \"positionInfoList\": [ { \"org\": { \"id\": \"rdEAAAAAFjjM567U\", \"name\": \"后台开发部\", \"number\": \"106.010.002\", \"orgName1\": \"研发中心\", \"orgName2\": \"智慧能源管理产品平台\", \"orgName3\": \"后台开发部\", \"orgName4\": \"\" }, \"position\": { \"id\": \"dnFOIcJiTLClWiTlDV1lBHSuYS4=\", \"name\": \"JAVA开发工程师\", \"number\": \"ZW0000875\" } } ] }, { \"id\": \"rdEAAAAKF0y4hF0d\", \"jobId\": \"rdEAAAAKF0t1cCSY\", \"name\": \"IT开发岗\", \"number\": \"87\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKF1K4hF0d\", \"jobId\": \"rdEAAAAKF1F1cCSY\", \"name\": \"系统运维岗\", \"number\": \"89\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKF1W4hF0d\", \"jobId\": \"rdEAAAAKF1R1cCSY\", \"name\": \"信息安全岗\", \"number\": \"90\", \"positionInfoList\": [] } ], \"name\": \"IT运维与支持\", \"number\": \"38\" } ], \"name\": \"企业IT\", \"number\": \"22\" }, { \"id\": \"rdEAAAAKE9ujdRE7\", \"jobFamilyList\": [ { \"id\": \"rdEAAAAKFWip9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAKF464hF0d\", \"jobId\": \"rdEAAAAKF411cCSY\", \"name\": \"EHS岗\", \"number\": \"109\", \"positionInfoList\": [] } ], \"name\": \"安全管理\", \"number\": \"42\" }, { \"id\": \"rdEAAAAKFWWp9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAKF3+4hF0d\", \"jobId\": \"rdEAAAAKF351cCSY\", \"name\": \"CQE岗\", \"number\": \"104\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKF3m4hF0d\", \"jobId\": \"rdEAAAAKF3h1cCSY\", \"name\": \"DQA岗\", \"number\": \"102\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKF3y4hF0d\", \"jobId\": \"rdEAAAAKF3t1cCSY\", \"name\": \"SQE岗\", \"number\": \"103\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKF4i4hF0d\", \"jobId\": \"rdEAAAAKF4d1cCSY\", \"name\": \"质量测试岗\", \"number\": \"107\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKF4K4hF0d\", \"jobId\": \"rdEAAAAKF4F1cCSY\", \"name\": \"PQE岗\", \"number\": \"105\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKF4u4hF0d\", \"jobId\": \"rdEAAAAKF4p1cCSY\", \"name\": \"体系岗\", \"number\": \"108\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKF4W4hF0d\", \"jobId\": \"rdEAAAAKF4R1cCSY\", \"name\": \"售后维修岗\", \"number\": \"106\", \"positionInfoList\": [] } ], \"name\": \"品质管理\", \"number\": \"41\" } ], \"name\": \"质量与安全管理\", \"number\": \"25\" }, { \"id\": \"rdEAAAAKE9WjdRE7\", \"jobFamilyList\": [ { \"id\": \"rdEAAAAKFV+p9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAKF164hF0d\", \"jobId\": \"rdEAAAAKF111cCSY\", \"name\": \"展会设计岗\", \"number\": \"93\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKF1i4hF0d\", \"jobId\": \"rdEAAAAKF1d1cCSY\", \"name\": \"平面设计岗\", \"number\": \"91\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKF1u4hF0d\", \"jobId\": \"rdEAAAAKF1p1cCSY\", \"name\": \"视频设计岗\", \"number\": \"92\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKF2G4hF0d\", \"jobId\": \"rdEAAAAKF2B1cCSY\", \"name\": \"创意设计岗\", \"number\": \"94\", \"positionInfoList\": [] } ], \"name\": \"设计\", \"number\": \"39\" } ], \"name\": \"通用设计\", \"number\": \"23\" } ], \"id\": \"rdEAAAAB2V1kHJrh\", \"name\": \"专业职能\", \"number\": \"01\" }, { \"childSequenceList\": [ { \"id\": \"rdEAAAAKE66jdRE7\", \"jobFamilyList\": [ { \"id\": \"rdEAAAAKFQ6p9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAJcme4hF0d\", \"jobId\": \"rdEAAAAJcmZ1cCSY\", \"name\": \"系统解决方案岗\", \"number\": \"38\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAJcmG4hF0d\", \"jobId\": \"rdEAAAAJcmB1cCSY\", \"name\": \"产品规划岗\", \"number\": \"36\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAJcmq4hF0d\", \"jobId\": \"rdEAAAAJcml1cCSY\", \"name\": \"售前解决方案岗\", \"number\": \"39\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAJcmS4hF0d\", \"jobId\": \"rdEAAAAJcmN1cCSY\", \"name\": \"产品管理岗\", \"number\": \"37\", \"positionInfoList\": [] } ], \"name\": \"产品管理\", \"number\": \"12\" } ], \"name\": \"产品管理\", \"number\": \"10\" }, { \"id\": \"rdEAAAAKE72jdRE7\", \"jobFamilyList\": [ { \"id\": \"rdEAAAAKFTWp9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAKFvu4hF0d\", \"jobId\": \"rdEAAAAKFvp1cCSY\", \"name\": \"研发项目管理岗\", \"number\": \"60\", \"positionInfoList\": [] } ], \"name\": \"研发项目管理\", \"number\": \"25\" } ], \"name\": \"研发项目管理\", \"number\": \"15\" }, { \"id\": \"rdEAAAAKE7ejdRE7\", \"jobFamilyList\": [ { \"id\": \"rdEAAAAKFSmp9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAKFua4hF0d\", \"jobId\": \"rdEAAAAKFuV1cCSY\", \"name\": \"工业设计岗\", \"number\": \"53\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKFum4hF0d\", \"jobId\": \"rdEAAAAKFuh1cCSY\", \"name\": \"UI设计岗\", \"number\": \"54\", \"positionInfoList\": [] } ], \"name\": \"产品设计\", \"number\": \"21\" } ], \"name\": \"产品设计\", \"number\": \"13\" }, { \"id\": \"rdEAAAAKE7GjdRE7\", \"jobFamilyList\": [ { \"id\": \"rdEAAAAKFRGp9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAJcm24hF0d\", \"jobId\": \"rdEAAAAJcmx1cCSY\", \"name\": \"售前技术支持岗\", \"number\": \"40\", \"positionInfoList\": [] } ], \"name\": \"售前技术\", \"number\": \"13\" } ], \"name\": \"售前技术\", \"number\": \"11\" }, { \"id\": \"rdEAAAAKE7qjdRE7\", \"jobFamilyList\": [ { \"id\": \"rdEAAAAKFS+p9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAKFu+4hF0d\", \"jobId\": \"rdEAAAAKFu51cCSY\", \"name\": \"测试岗\", \"number\": \"56\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKFvK4hF0d\", \"jobId\": \"rdEAAAAKFvF1cCSY\", \"name\": \"认证岗\", \"number\": \"57\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKFvW4hF0d\", \"jobId\": \"rdEAAAAKFvR1cCSY\", \"name\": \"安规岗\", \"number\": \"58\", \"positionInfoList\": [] } ], \"name\": \"测试认证\", \"number\": \"23\" }, { \"id\": \"rdEAAAAKFSyp9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAKFuy4hF0d\", \"jobId\": \"rdEAAAAKFut1cCSY\", \"name\": \"器件岗\", \"number\": \"55\", \"positionInfoList\": [] } ], \"name\": \"器件\", \"number\": \"22\" }, { \"id\": \"rdEAAAAKFTKp9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAKFvi4hF0d\", \"jobId\": \"rdEAAAAKFvd1cCSY\", \"name\": \"NPI岗\", \"number\": \"59\", \"positionInfoList\": [] } ], \"name\": \"试制验证\", \"number\": \"24\" } ], \"name\": \"产品验证\", \"number\": \"14\" }, { \"id\": \"rdEAAAAKE7SjdRE7\", \"jobFamilyList\": [ { \"id\": \"rdEAAAAKFR2p9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAKFte4hF0d\", \"jobId\": \"rdEAAAAKFtZ1cCSY\", \"name\": \"CAE仿真岗\", \"number\": \"48\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKFtG4hF0d\", \"jobId\": \"rdEAAAAKFtB1cCSY\", \"name\": \"结构岗\", \"number\": \"46\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKFtS4hF0d\", \"jobId\": \"rdEAAAAKFtN1cCSY\", \"name\": \"热设计岗\", \"number\": \"47\", \"positionInfoList\": [] } ], \"name\": \"结构\", \"number\": \"17\" }, { \"id\": \"rdEAAAAKFRep9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAKFsi4hF0d\", \"jobId\": \"rdEAAAAKFsd1cCSY\", \"name\": \"软件开发岗\", \"number\": \"43\", \"positionInfoList\": [ { \"org\": { \"id\": \"rdEAAAAACUTM567U\", \"name\": \"SEG\", \"number\": \"106.004.001\", \"orgName1\": \"研发中心\", \"orgName2\": \"发电侧光储产品线\", \"orgName3\": \"SEG\", \"orgName4\": \"\" }, \"position\": { \"id\": \"4YRBcnC/QxeQB2tf0xqf23SuYS4=\", \"name\": \"软件工程师\", \"number\": \"ZW0000661\" } }, { \"org\": { \"id\": \"rdEAAAAAFjjM567U\", \"name\": \"后台开发部\", \"number\": \"106.010.002\", \"orgName1\": \"研发中心\", \"orgName2\": \"智慧能源管理产品平台\", \"orgName3\": \"后台开发部\", \"orgName4\": \"\" }, \"position\": { \"id\": \"yz3ebgkFQeKNfdf8B99tCnSuYS4=\", \"name\": \"软件工程师\", \"number\": \"ZW0000870\" } }, { \"org\": { \"id\": \"rdEAAAAAFjjM567U\", \"name\": \"后台开发部\", \"number\": \"106.010.002\", \"orgName1\": \"研发中心\", \"orgName2\": \"智慧能源管理产品平台\", \"orgName3\": \"后台开发部\", \"orgName4\": \"\" }, \"position\": { \"id\": \"Y1NAlrFST3ajujQD3pl7sXSuYS4=\", \"name\": \"初级软件工程师\", \"number\": \"ZW0002420\" } }, { \"org\": { \"id\": \"rdEAAAAAFjjM567U\", \"name\": \"后台开发部\", \"number\": \"106.010.002\", \"orgName1\": \"研发中心\", \"orgName2\": \"智慧能源管理产品平台\", \"orgName3\": \"后台开发部\", \"orgName4\": \"\" }, \"position\": { \"id\": \"AVDzjoRXQumZtkEOuPtUGnSuYS4=\", \"name\": \"初级软件工程师\", \"number\": \"ZW0002421\" } }, { \"org\": { \"id\": \"rdEAAAAAFjjM567U\", \"name\": \"后台开发部\", \"number\": \"106.010.002\", \"orgName1\": \"研发中心\", \"orgName2\": \"智慧能源管理产品平台\", \"orgName3\": \"后台开发部\", \"orgName4\": \"\" }, \"position\": { \"id\": \"hD1wUUtVQaS15bEQ3fqBmnSuYS4=\", \"name\": \"助理软件工程师\", \"number\": \"ZW0002422\" } }, { \"org\": { \"id\": \"rdEAAAAAFkHM567U\", \"name\": \"前台开发部\", \"number\": \"106.010.004\", \"orgName1\": \"研发中心\", \"orgName2\": \"智慧能源管理产品平台\", \"orgName3\": \"前台开发部\", \"orgName4\": \"\" }, \"position\": { \"id\": \"QI/HQJUJQsGtHgTu0p+FhHSuYS4=\", \"name\": \"软件工程师\", \"number\": \"ZW0000872\" } }, { \"org\": { \"id\": \"rdEAAAAAFkHM567U\", \"name\": \"前台开发部\", \"number\": \"106.010.004\", \"orgName1\": \"研发中心\", \"orgName2\": \"智慧能源管理产品平台\", \"orgName3\": \"前台开发部\", \"orgName4\": \"\" }, \"position\": { \"id\": \"qqR2oJwzQeSuPd153jppE3SuYS4=\", \"name\": \"嵌入式软件工程师\", \"number\": \"ZW0000877\" } }, { \"org\": { \"id\": \"rdEAAAAAFkHM567U\", \"name\": \"前台开发部\", \"number\": \"106.010.004\", \"orgName1\": \"研发中心\", \"orgName2\": \"智慧能源管理产品平台\", \"orgName3\": \"前台开发部\", \"orgName4\": \"\" }, \"position\": { \"id\": \"GYITZbq+RlmVQNbO4zFWonSuYS4=\", \"name\": \"助理软件工程师\", \"number\": \"ZW0002423\" } }, { \"org\": { \"id\": \"rdEAAAAAFkrM567U\", \"name\": \"云测试部\", \"number\": \"106.010.005\", \"orgName1\": \"研发中心\", \"orgName2\": \"智慧能源管理产品平台\", \"orgName3\": \"云测试部\", \"orgName4\": \"\" }, \"position\": { \"id\": \"3rd4BGBGTyyHe0uBLtkZuXSuYS4=\", \"name\": \"软件测试工程师\", \"number\": \"ZW0000977\" } }, { \"org\": { \"id\": \"rdEAAAAACfbM567U\", \"name\": \"LMT\", \"number\": \"106.011.002\", \"orgName1\": \"研发中心\", \"orgName2\": \"物联产品平台\", \"orgName3\": \"LMT\", \"orgName4\": \"\" }, \"position\": { \"id\": \"ehsJ4CG4QRCvxz4Hwm65IHSuYS4=\", \"name\": \"嵌入式软件工程师\", \"number\": \"ZW0002349\" } }, { \"org\": { \"id\": \"rdEAAAAACfbM567U\", \"name\": \"LMT\", \"number\": \"106.011.002\", \"orgName1\": \"研发中心\", \"orgName2\": \"物联产品平台\", \"orgName3\": \"LMT\", \"orgName4\": \"\" }, \"position\": { \"id\": \"rtv5n96+RMydIHxY21Bes3SuYS4=\", \"name\": \"嵌入式软件工程师\", \"number\": \"ZW0002360\" } }, { \"org\": { \"id\": \"rdEAAAAACfbM567U\", \"name\": \"LMT\", \"number\": \"106.011.002\", \"orgName1\": \"研发中心\", \"orgName2\": \"物联产品平台\", \"orgName3\": \"LMT\", \"orgName4\": \"\" }, \"position\": { \"id\": \"aAs6vIMeQ8yvsLvFxeQZCnSuYS4=\", \"name\": \"助理嵌入式软件工程师\", \"number\": \"ZW0002531\" } }, { \"org\": { \"id\": \"rdEAAAAABcLM567U\", \"name\": \"软件部\", \"number\": \"106.013\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"bxAIgruSSh6qCVkfimTjZ3SuYS4=\", \"name\": \"高级软件工程师\", \"number\": \"ZW0000810\" } }, { \"org\": { \"id\": \"rdEAAAAADZPM567U\", \"name\": \"系统架构组\", \"number\": \"106.013.001.001\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件平台部\", \"orgName4\": \"系统架构组\" }, \"position\": { \"id\": \"akm+AdVcQJ6pXtj7zWjrAXSuYS4=\", \"name\": \"ARM软件工程师\", \"number\": \"ZW0000783\" } }, { \"org\": { \"id\": \"rdEAAAAADZPM567U\", \"name\": \"系统架构组\", \"number\": \"106.013.001.001\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件平台部\", \"orgName4\": \"系统架构组\" }, \"position\": { \"id\": \"glM/mYvGQCCLO5fRYS+tVnSuYS4=\", \"name\": \"嵌入式软件工程师\", \"number\": \"ZW0000791\" } }, { \"org\": { \"id\": \"rdEAAAAADZPM567U\", \"name\": \"系统架构组\", \"number\": \"106.013.001.001\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件平台部\", \"orgName4\": \"系统架构组\" }, \"position\": { \"id\": \"c1hIJvIOT6CNu28iC8tJMnSuYS4=\", \"name\": \"助理嵌入式软件工程师\", \"number\": \"ZW0002436\" } }, { \"org\": { \"id\": \"rdEAAAAADZ3M567U\", \"name\": \"通信组\", \"number\": \"106.013.001.002\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件平台部\", \"orgName4\": \"通信组\" }, \"position\": { \"id\": \"IaQrujbkQs6ffeiV4ms+63SuYS4=\", \"name\": \"ARM软件工程师\", \"number\": \"ZW0000780\" } }, { \"org\": { \"id\": \"rdEAAAAADZ3M567U\", \"name\": \"通信组\", \"number\": \"106.013.001.002\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件平台部\", \"orgName4\": \"通信组\" }, \"position\": { \"id\": \"mI9zBD0QT22i2+5RhsEBa3SuYS4=\", \"name\": \"嵌入式软件工程师\", \"number\": \"ZW0000781\" } }, { \"org\": { \"id\": \"rdEAAAAADZ3M567U\", \"name\": \"通信组\", \"number\": \"106.013.001.002\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件平台部\", \"orgName4\": \"通信组\" }, \"position\": { \"id\": \"977OrwdzQ2GwABfLbkWb83SuYS4=\", \"name\": \"初级嵌入式软件工程师\", \"number\": \"ZW0002437\" } }, { \"org\": { \"id\": \"rdEAAAAADabM567U\", \"name\": \"IOT组\", \"number\": \"106.013.001.003\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件平台部\", \"orgName4\": \"IOT组\" }, \"position\": { \"id\": \"lSagzKciS9eiDdkytd+mR3SuYS4=\", \"name\": \"嵌入式软件工程师\", \"number\": \"ZW0000800\" } }, { \"org\": { \"id\": \"rdEAAAAADabM567U\", \"name\": \"IOT组\", \"number\": \"106.013.001.003\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件平台部\", \"orgName4\": \"IOT组\" }, \"position\": { \"id\": \"MBSc+h4LT9C1LwxMiM33MXSuYS4=\", \"name\": \"助理嵌入式软件工程师\", \"number\": \"ZW0002438\" } }, { \"org\": { \"id\": \"rdEAAAAADa/M567U\", \"name\": \"软件测试组\", \"number\": \"106.013.001.004\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件平台部\", \"orgName4\": \"软件测试组\" }, \"position\": { \"id\": \"5XbhSblSQ/qpoHPX/dnZznSuYS4=\", \"name\": \"嵌入式软件测试工程师\", \"number\": \"ZW0000785\" } }, { \"org\": { \"id\": \"rdEAAAAADa/M567U\", \"name\": \"软件测试组\", \"number\": \"106.013.001.004\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件平台部\", \"orgName4\": \"软件测试组\" }, \"position\": { \"id\": \"fmfsUAzJSX689lolkrUZj3SuYS4=\", \"name\": \"软件测试工程师\", \"number\": \"ZW0000794\" } }, { \"org\": { \"id\": \"rdEAAAAADa/M567U\", \"name\": \"软件测试组\", \"number\": \"106.013.001.004\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件平台部\", \"orgName4\": \"软件测试组\" }, \"position\": { \"id\": \"bNHmj8bbS4azzSP0YSYUynSuYS4=\", \"name\": \"助理嵌入式软件测试工程师\", \"number\": \"ZW0002439\" } }, { \"org\": { \"id\": \"rdEAAAAACkDM567U\", \"name\": \"软件系统部\", \"number\": \"106.013.002\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件系统部\", \"orgName4\": \"\" }, \"position\": { \"id\": \"WJmLrq6rQWCz05ygsTwQI3SuYS4=\", \"name\": \"软件工程师\", \"number\": \"ZW0000807\" } }, { \"org\": { \"id\": \"rdEAAAAACkDM567U\", \"name\": \"软件系统部\", \"number\": \"106.013.002\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件系统部\", \"orgName4\": \"\" }, \"position\": { \"id\": \"lOQCiCxMSMCJPbgUCHqi63SuYS4=\", \"name\": \"高级DSP软件工程师（武汉）\", \"number\": \"ZW0000829\" } }, { \"org\": { \"id\": \"rdEAAAAACkDM567U\", \"name\": \"软件系统部\", \"number\": \"106.013.002\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件系统部\", \"orgName4\": \"\" }, \"position\": { \"id\": \"1tHwxvVVTj6FrS0HYVXDPXSuYS4=\", \"name\": \"高级DSP软件工程师\", \"number\": \"ZW0002440\" } }, { \"org\": { \"id\": \"rdEAAAAACkDM567U\", \"name\": \"软件系统部\", \"number\": \"106.013.002\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件系统部\", \"orgName4\": \"\" }, \"position\": { \"id\": \"ekiNhf0+SiOq+n45i2Pn+HSuYS4=\", \"name\": \"嵌入式软件实习生\", \"number\": \"ZW0002456\" } }, { \"org\": { \"id\": \"rdEAAAAADbjM567U\", \"name\": \"数字电源组\", \"number\": \"106.013.003.001\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"算法部\", \"orgName4\": \"数字电源组\" }, \"position\": { \"id\": \"UOXZ4wArRr6J1A7hugEPinSuYS4=\", \"name\": \"高级软件工程师\", \"number\": \"ZW0000866\" } }, { \"org\": { \"id\": \"rdEAAAAADbjM567U\", \"name\": \"数字电源组\", \"number\": \"106.013.003.001\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"算法部\", \"orgName4\": \"数字电源组\" }, \"position\": { \"id\": \"JGsf1FvKRmqaTLYVKC21VXSuYS4=\", \"name\": \"软件工程师\", \"number\": \"ZW0000867\" } }, { \"org\": { \"id\": \"rdEAAAAAClLM567U\", \"name\": \"应用装备部\", \"number\": \"106.013.004\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"应用装备部\", \"orgName4\": \"\" }, \"position\": { \"id\": \"MjwRiz0MR+e50UB/B0BjE3SuYS4=\", \"name\": \"软件助理工程师\", \"number\": \"ZW0000868\" } }, { \"org\": { \"id\": \"rdEAAAAAClLM567U\", \"name\": \"应用装备部\", \"number\": \"106.013.004\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"应用装备部\", \"orgName4\": \"\" }, \"position\": { \"id\": \"ppq5ZV9qSxiYJjbYLsOX3HSuYS4=\", \"name\": \"软件工程师\", \"number\": \"ZW0000871\" } }, { \"org\": { \"id\": \"rdEAAAAAClLM567U\", \"name\": \"应用装备部\", \"number\": \"106.013.004\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"应用装备部\", \"orgName4\": \"\" }, \"position\": { \"id\": \"CrbLTGHATKeknUBWbqe1JnSuYS4=\", \"name\": \"高级软件工程师\", \"number\": \"ZW0000991\" } }, { \"org\": { \"id\": \"rdEAAAAAClLM567U\", \"name\": \"应用装备部\", \"number\": \"106.013.004\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"应用装备部\", \"orgName4\": \"\" }, \"position\": { \"id\": \"cyO9Kup5SviBCgRphe4r6nSuYS4=\", \"name\": \"监控平台软件实习生\", \"number\": \"ZW0002303\" } }, { \"org\": { \"id\": \"rdEAAAAAClvM567U\", \"name\": \"软件业务一部\", \"number\": \"106.013.005\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务一部\", \"orgName4\": \"\" }, \"position\": { \"id\": \"dG40DX2hT4Ci2DbhqzlFFXSuYS4=\", \"name\": \"DSP软件工程师\", \"number\": \"ZW0000812\" } }, { \"org\": { \"id\": \"rdEAAAAADdTM567U\", \"name\": \"单相户储组\", \"number\": \"106.013.005.001\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务一部\", \"orgName4\": \"单相户储组\" }, \"position\": { \"id\": \"Bb1RnNzqTTO4b2/Dwux6X3SuYS4=\", \"name\": \"ARM软件工程师\", \"number\": \"ZW0000821\" } }, { \"org\": { \"id\": \"rdEAAAAADdTM567U\", \"name\": \"单相户储组\", \"number\": \"106.013.005.001\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务一部\", \"orgName4\": \"单相户储组\" }, \"position\": { \"id\": \"vpCkc6nCQkGSeIX4CrqowXSuYS4=\", \"name\": \"DSP软件工程师\", \"number\": \"ZW0000852\" } }, { \"org\": { \"id\": \"rdEAAAAADdTM567U\", \"name\": \"单相户储组\", \"number\": \"106.013.005.001\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务一部\", \"orgName4\": \"单相户储组\" }, \"position\": { \"id\": \"JEgJJCaxTqa9MV5RbNhC53SuYS4=\", \"name\": \"嵌入式软件工程师\", \"number\": \"ZW0000861\" } }, { \"org\": { \"id\": \"rdEAAAAADdTM567U\", \"name\": \"单相户储组\", \"number\": \"106.013.005.001\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务一部\", \"orgName4\": \"单相户储组\" }, \"position\": { \"id\": \"K0F2yjR0SYaS1gsu8bmtWnSuYS4=\", \"name\": \"助理嵌入式软件工程师\", \"number\": \"ZW0002443\" } }, { \"org\": { \"id\": \"rdEAAAAADdTM567U\", \"name\": \"单相户储组\", \"number\": \"106.013.005.001\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务一部\", \"orgName4\": \"单相户储组\" }, \"position\": { \"id\": \"HpWBwylwTTKhBvIQywj2dXSuYS4=\", \"name\": \"助理嵌入式软件工程师\", \"number\": \"ZW0002444\" } }, { \"org\": { \"id\": \"rdEAAAAADd7M567U\", \"name\": \"三相户储组\", \"number\": \"106.013.005.002\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务一部\", \"orgName4\": \"三相户储组\" }, \"position\": { \"id\": \"ofrQZRC4SqGK2UK4n6CIW3SuYS4=\", \"name\": \"助理软件工程师\", \"number\": \"ZW0000815\" } }, { \"org\": { \"id\": \"rdEAAAAADd7M567U\", \"name\": \"三相户储组\", \"number\": \"106.013.005.002\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务一部\", \"orgName4\": \"三相户储组\" }, \"position\": { \"id\": \"kMvx6Ia2TVaLCuuKhIKlXXSuYS4=\", \"name\": \"DSP软件工程师\", \"number\": \"ZW0000833\" } }, { \"org\": { \"id\": \"rdEAAAAADd7M567U\", \"name\": \"三相户储组\", \"number\": \"106.013.005.002\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务一部\", \"orgName4\": \"三相户储组\" }, \"position\": { \"id\": \"LPAym+u+Q4CGfha4I59j6XSuYS4=\", \"name\": \"嵌入式软件工程师\", \"number\": \"ZW0000849\" } }, { \"org\": { \"id\": \"rdEAAAAADd7M567U\", \"name\": \"三相户储组\", \"number\": \"106.013.005.002\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务一部\", \"orgName4\": \"三相户储组\" }, \"position\": { \"id\": \"KFjhJccfSSGFpK3SkWDJXnSuYS4=\", \"name\": \"嵌入式软件实习生\", \"number\": \"ZW0001160\" } }, { \"org\": { \"id\": \"rdEAAAAADd7M567U\", \"name\": \"三相户储组\", \"number\": \"106.013.005.002\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务一部\", \"orgName4\": \"三相户储组\" }, \"position\": { \"id\": \"wOXVtCfVSxG5sM9dP2PNJ3SuYS4=\", \"name\": \"ARM软件工程师\", \"number\": \"ZW0002297\" } }, { \"org\": { \"id\": \"rdEAAAAADd7M567U\", \"name\": \"三相户储组\", \"number\": \"106.013.005.002\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务一部\", \"orgName4\": \"三相户储组\" }, \"position\": { \"id\": \"Dj3l1V50SAqcbyGFZwxpSnSuYS4=\", \"name\": \"初级嵌入式软件工程师\", \"number\": \"ZW0002445\" } }, { \"org\": { \"id\": \"rdEAAAAADefM567U\", \"name\": \"车用能源组\", \"number\": \"106.013.005.003\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务一部\", \"orgName4\": \"车用能源组\" }, \"position\": { \"id\": \"RmMEbigkTHOaSEE66anHAHSuYS4=\", \"name\": \"软件工程师\", \"number\": \"ZW0000808\" } }, { \"org\": { \"id\": \"rdEAAAAADefM567U\", \"name\": \"车用能源组\", \"number\": \"106.013.005.003\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务一部\", \"orgName4\": \"车用能源组\" }, \"position\": { \"id\": \"2lGTDCs/QvmOausMpIh/GHSuYS4=\", \"name\": \"助理软件工程师\", \"number\": \"ZW0000814\" } }, { \"org\": { \"id\": \"rdEAAAAADefM567U\", \"name\": \"车用能源组\", \"number\": \"106.013.005.003\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务一部\", \"orgName4\": \"车用能源组\" }, \"position\": { \"id\": \"MIRDG0k7Tve0PPfao8uGLnSuYS4=\", \"name\": \"嵌入式软件工程师\", \"number\": \"ZW0002269\" } }, { \"org\": { \"id\": \"rdEAAAAADefM567U\", \"name\": \"车用能源组\", \"number\": \"106.013.005.003\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务一部\", \"orgName4\": \"车用能源组\" }, \"position\": { \"id\": \"7cQ6lsMsRXuM2bEzbUJJKXSuYS4=\", \"name\": \"嵌入式软件实习生\", \"number\": \"ZW0002326\" } }, { \"org\": { \"id\": \"rdEAAAAADefM567U\", \"name\": \"车用能源组\", \"number\": \"106.013.005.003\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务一部\", \"orgName4\": \"车用能源组\" }, \"position\": { \"id\": \"mhbXzhszSCmmhSjfEwMQfnSuYS4=\", \"name\": \"初级软件工程师\", \"number\": \"ZW0002365\" } }, { \"org\": { \"id\": \"rdEAAAAADefM567U\", \"name\": \"车用能源组\", \"number\": \"106.013.005.003\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务一部\", \"orgName4\": \"车用能源组\" }, \"position\": { \"id\": \"8xrWx1ZrTSOBX1UhMqEUW3SuYS4=\", \"name\": \"助理嵌入式软件工程师\", \"number\": \"ZW0002446\" } }, { \"org\": { \"id\": \"rdEAAAAADefM567U\", \"name\": \"车用能源组\", \"number\": \"106.013.005.003\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务一部\", \"orgName4\": \"车用能源组\" }, \"position\": { \"id\": \"aAts/5zeT0KnT/8031075XSuYS4=\", \"name\": \"助理嵌入式软件工程师\", \"number\": \"ZW0002447\" } }, { \"org\": { \"id\": \"rdEAAAAACmTM567U\", \"name\": \"软件业务二部\", \"number\": \"106.013.006\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务二部\", \"orgName4\": \"\" }, \"position\": { \"id\": \"plojjEHkQD6C9BZ/KX08F3SuYS4=\", \"name\": \"DSP软件工程师（武汉）\", \"number\": \"ZW0000818\" } }, { \"org\": { \"id\": \"rdEAAAAADfDM567U\", \"name\": \"中小功率光伏组\", \"number\": \"106.013.006.001\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务二部\", \"orgName4\": \"中小功率光伏组\" }, \"position\": { \"id\": \"kZq05ny1SmeVMfImOV1KdnSuYS4=\", \"name\": \"软件测试\", \"number\": \"ZW0000792\" } }, { \"org\": { \"id\": \"rdEAAAAADfDM567U\", \"name\": \"中小功率光伏组\", \"number\": \"106.013.006.001\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务二部\", \"orgName4\": \"中小功率光伏组\" }, \"position\": { \"id\": \"hZ1BqGyjTtiIfBRww9dej3SuYS4=\", \"name\": \"软件测试工程师\", \"number\": \"ZW0000795\" } }, { \"org\": { \"id\": \"rdEAAAAADfDM567U\", \"name\": \"中小功率光伏组\", \"number\": \"106.013.006.001\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务二部\", \"orgName4\": \"中小功率光伏组\" }, \"position\": { \"id\": \"xNZfUdoiTq6FR8lz9boEe3SuYS4=\", \"name\": \"软件工程师\", \"number\": \"ZW0000811\" } }, { \"org\": { \"id\": \"rdEAAAAADfDM567U\", \"name\": \"中小功率光伏组\", \"number\": \"106.013.006.001\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务二部\", \"orgName4\": \"中小功率光伏组\" }, \"position\": { \"id\": \"oRGKU+qzSDmpCc60vrZfYXSuYS4=\", \"name\": \"助理软件工程师\", \"number\": \"ZW0000825\" } }, { \"org\": { \"id\": \"rdEAAAAADfDM567U\", \"name\": \"中小功率光伏组\", \"number\": \"106.013.006.001\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务二部\", \"orgName4\": \"中小功率光伏组\" }, \"position\": { \"id\": \"A95903tNRKeTHIgoVFPe9HSuYS4=\", \"name\": \"ARM软件工程师（武汉）\", \"number\": \"ZW0000828\" } }, { \"org\": { \"id\": \"rdEAAAAADfDM567U\", \"name\": \"中小功率光伏组\", \"number\": \"106.013.006.001\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务二部\", \"orgName4\": \"中小功率光伏组\" }, \"position\": { \"id\": \"moBUtvc5TxqJJzzg3/RWaXSuYS4=\", \"name\": \"ARM软件工程师\", \"number\": \"ZW0002448\" } }, { \"org\": { \"id\": \"rdEAAAAADfDM567U\", \"name\": \"中小功率光伏组\", \"number\": \"106.013.006.001\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务二部\", \"orgName4\": \"中小功率光伏组\" }, \"position\": { \"id\": \"EFulvLWZQmyGEmr03pY7bXSuYS4=\", \"name\": \"ARM软件工程师\", \"number\": \"ZW0002449\" } }, { \"org\": { \"id\": \"rdEAAAAADfDM567U\", \"name\": \"中小功率光伏组\", \"number\": \"106.013.006.001\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务二部\", \"orgName4\": \"中小功率光伏组\" }, \"position\": { \"id\": \"6N6g9x3SQpqK2MqBOB0YU3SuYS4=\", \"name\": \"嵌入式软件测试工程师\", \"number\": \"ZW0002450\" } }, { \"org\": { \"id\": \"rdEAAAAADfDM567U\", \"name\": \"中小功率光伏组\", \"number\": \"106.013.006.001\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务二部\", \"orgName4\": \"中小功率光伏组\" }, \"position\": { \"id\": \"xCIVBpI9QsGYWgkUoKLd53SuYS4=\", \"name\": \"软件工程师（上海）\", \"number\": \"ZW0002453\" } }, { \"org\": { \"id\": \"rdEAAAAADfrM567U\", \"name\": \"地面电站组\", \"number\": \"106.013.006.002\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务二部\", \"orgName4\": \"地面电站组\" }, \"position\": { \"id\": \"4BUsUNBHSaO/8ZkwaJOL73SuYS4=\", \"name\": \"软件工程师\", \"number\": \"ZW0000813\" } }, { \"org\": { \"id\": \"rdEAAAAADfrM567U\", \"name\": \"地面电站组\", \"number\": \"106.013.006.002\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务二部\", \"orgName4\": \"地面电站组\" }, \"position\": { \"id\": \"lBKE5F9aQ4O1fohIidnS0XSuYS4=\", \"name\": \"嵌入式软件工程师\", \"number\": \"ZW0000827\" } }, { \"org\": { \"id\": \"rdEAAAAADfrM567U\", \"name\": \"地面电站组\", \"number\": \"106.013.006.002\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务二部\", \"orgName4\": \"地面电站组\" }, \"position\": { \"id\": \"kEHiHeX4T8+LLqvi6b0UL3SuYS4=\", \"name\": \"DSP软件工程师\", \"number\": \"ZW0000835\" } }, { \"org\": { \"id\": \"rdEAAAAADfrM567U\", \"name\": \"地面电站组\", \"number\": \"106.013.006.002\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务二部\", \"orgName4\": \"地面电站组\" }, \"position\": { \"id\": \"TOAbBQYwTJOGNUHvYKGulnSuYS4=\", \"name\": \"助理嵌入式软件工程师\", \"number\": \"ZW0002451\" } }, { \"org\": { \"id\": \"rdEAAAAADfrM567U\", \"name\": \"地面电站组\", \"number\": \"106.013.006.002\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务二部\", \"orgName4\": \"地面电站组\" }, \"position\": { \"id\": \"LMz+xXS/RJ+xTnnmfDNDV3SuYS4=\", \"name\": \"助理嵌入式软件工程师\", \"number\": \"ZW0002452\" } }, { \"org\": { \"id\": \"rdEAAAAACm3M567U\", \"name\": \"软件业务三部\", \"number\": \"106.013.007\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务三部\", \"orgName4\": \"\" }, \"position\": { \"id\": \"bQr/+G2WRV6eoyFgQVSa1nSuYS4=\", \"name\": \"资深软件工程师（上海）\", \"number\": \"ZW0000857\" } }, { \"org\": { \"id\": \"rdEAAAAADgPM567U\", \"name\": \"集中式储能组\", \"number\": \"106.013.007.001\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务三部\", \"orgName4\": \"集中式储能组\" }, \"position\": { \"id\": \"ml5hxuLRQIe7nLQMgwpfDHSuYS4=\", \"name\": \"高级软件工程师\", \"number\": \"ZW0000837\" } }, { \"org\": { \"id\": \"rdEAAAAADgPM567U\", \"name\": \"集中式储能组\", \"number\": \"106.013.007.001\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务三部\", \"orgName4\": \"集中式储能组\" }, \"position\": { \"id\": \"ua861KeMRgeg7VgAgezaL3SuYS4=\", \"name\": \"软件工程师\", \"number\": \"ZW0000851\" } }, { \"org\": { \"id\": \"rdEAAAAADg3M567U\", \"name\": \"数据中心组\", \"number\": \"106.013.007.002\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务三部\", \"orgName4\": \"数据中心组\" }, \"position\": { \"id\": \"qUtFrkCkQKur1lWUk2IE2HSuYS4=\", \"name\": \"软件工程师（上海）\", \"number\": \"ZW0000820\" } }, { \"org\": { \"id\": \"rdEAAAAADg3M567U\", \"name\": \"数据中心组\", \"number\": \"106.013.007.002\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务三部\", \"orgName4\": \"数据中心组\" }, \"position\": { \"id\": \"fL/H2p5lQCiPyComEB5AmXSuYS4=\", \"name\": \"资深软件工程师（上海）\", \"number\": \"ZW0000830\" } }, { \"org\": { \"id\": \"rdEAAAAADg3M567U\", \"name\": \"数据中心组\", \"number\": \"106.013.007.002\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务三部\", \"orgName4\": \"数据中心组\" }, \"position\": { \"id\": \"YBB+W07WRjmvND1Z907Ki3SuYS4=\", \"name\": \"高级软件工程师\", \"number\": \"ZW0000856\" } }, { \"org\": { \"id\": \"rdEAAAAADg3M567U\", \"name\": \"数据中心组\", \"number\": \"106.013.007.002\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务三部\", \"orgName4\": \"数据中心组\" }, \"position\": { \"id\": \"N8z/X8xOQC6oQ2/N91pGJHSuYS4=\", \"name\": \"嵌入式软件实习生\", \"number\": \"ZW0001164\" } }, { \"org\": { \"id\": \"rdEAAAAADg3M567U\", \"name\": \"数据中心组\", \"number\": \"106.013.007.002\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务三部\", \"orgName4\": \"数据中心组\" }, \"position\": { \"id\": \"mOuXMqR1SkGOacmD4wu4LHSuYS4=\", \"name\": \"初级软件工程师\", \"number\": \"ZW0002454\" } }, { \"org\": { \"id\": \"rdEAAAAADhbM567U\", \"name\": \"移动能源组\", \"number\": \"106.013.007.003\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务三部\", \"orgName4\": \"移动能源组\" }, \"position\": { \"id\": \"Quh7gxQtTOuDjdyV9pp3knSuYS4=\", \"name\": \"中级软件工程师\", \"number\": \"ZW0002354\" } }, { \"org\": { \"id\": \"rdEAAAAACnbM567U\", \"name\": \"软件业务四部\", \"number\": \"106.013.008\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务四部\", \"orgName4\": \"\" }, \"position\": { \"id\": \"rbMpO25pQ4uyJIu83wXpk3SuYS4=\", \"name\": \"高级ARM软件工程师\", \"number\": \"ZW0000834\" } }, { \"org\": { \"id\": \"rdEAAAAADh/M567U\", \"name\": \"电池产品组\", \"number\": \"106.013.008.001\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务四部\", \"orgName4\": \"电池产品组\" }, \"position\": { \"id\": \"uInRkIsTTZCAsY4FETlBMHSuYS4=\", \"name\": \"助理软件工程师\", \"number\": \"ZW0000824\" } }, { \"org\": { \"id\": \"rdEAAAAADh/M567U\", \"name\": \"电池产品组\", \"number\": \"106.013.008.001\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务四部\", \"orgName4\": \"电池产品组\" }, \"position\": { \"id\": \"iiXvBVfXS7SZ0EKK7R4jkXSuYS4=\", \"name\": \"嵌入式软件工程师\", \"number\": \"ZW0000850\" } }, { \"org\": { \"id\": \"rdEAAAAADh/M567U\", \"name\": \"电池产品组\", \"number\": \"106.013.008.001\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务四部\", \"orgName4\": \"电池产品组\" }, \"position\": { \"id\": \"NJf7D7oFSmyMjI5Td6f6fnSuYS4=\", \"name\": \"BMS软件工程师\", \"number\": \"ZW0000853\" } }, { \"org\": { \"id\": \"rdEAAAAADh/M567U\", \"name\": \"电池产品组\", \"number\": \"106.013.008.001\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务四部\", \"orgName4\": \"电池产品组\" }, \"position\": { \"id\": \"A+GV+rGVQsmaraD102P6yXSuYS4=\", \"name\": \"高级BMS软件工程师\", \"number\": \"ZW0000859\" } }, { \"org\": { \"id\": \"rdEAAAAADinM567U\", \"name\": \"物联产品组\", \"number\": \"106.013.008.002\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务四部\", \"orgName4\": \"物联产品组\" }, \"position\": { \"id\": \"m4X+wtgtRjWlIagoMvx7sHSuYS4=\", \"name\": \"软件工程师\", \"number\": \"ZW0000809\" } }, { \"org\": { \"id\": \"rdEAAAAADinM567U\", \"name\": \"物联产品组\", \"number\": \"106.013.008.002\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务四部\", \"orgName4\": \"物联产品组\" }, \"position\": { \"id\": \"wPZQXmbkQae+XOB9L4C0t3SuYS4=\", \"name\": \"嵌入式软件工程师\", \"number\": \"ZW0000854\" } }, { \"org\": { \"id\": \"rdEAAAAADjLM567U\", \"name\": \"数据中心监控组\", \"number\": \"106.013.008.003\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务四部\", \"orgName4\": \"数据中心监控组\" }, \"position\": { \"id\": \"+eEX1SvFSwyYAaNaTSKPQXSuYS4=\", \"name\": \"中级嵌入式软件工程师\", \"number\": \"ZW0000860\" } }, { \"org\": { \"id\": \"rdEAAAAADjLM567U\", \"name\": \"数据中心监控组\", \"number\": \"106.013.008.003\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务四部\", \"orgName4\": \"数据中心监控组\" }, \"position\": { \"id\": \"q/61CsO9SGqbCoxh3x9oFnSuYS4=\", \"name\": \"嵌入式软件工程师（上海）\", \"number\": \"ZW0000862\" } }, { \"org\": { \"id\": \"rdEAAAAADjLM567U\", \"name\": \"数据中心监控组\", \"number\": \"106.013.008.003\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务四部\", \"orgName4\": \"数据中心监控组\" }, \"position\": { \"id\": \"XkplukC7RSq2vUswJQgfa3SuYS4=\", \"name\": \"嵌入式软件实习生\", \"number\": \"ZW0001159\" } }, { \"org\": { \"id\": \"rdEAAAAADjLM567U\", \"name\": \"数据中心监控组\", \"number\": \"106.013.008.003\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件业务四部\", \"orgName4\": \"数据中心监控组\" }, \"position\": { \"id\": \"QBxnlEjWSPK8s4LTa+q7oXSuYS4=\", \"name\": \"初级嵌入式软件工程师\", \"number\": \"ZW0002455\" } }, { \"org\": { \"id\": \"rdEAAAAAEZTM567U\", \"name\": \"自动化开发科\", \"number\": \"107.003.012.001\", \"orgName1\": \"制造运营中心\", \"orgName2\": \"制造部\", \"orgName3\": \"设施与自动化处\", \"orgName4\": \"自动化开发科\" }, \"position\": { \"id\": \"bu9xj3+aRWaPBB4WTLaFiHSuYS4=\", \"name\": \"自动化软件工程师\", \"number\": \"ZW0001128\" } }, { \"org\": { \"id\": \"rdEAAAAAC8HM567U\", \"name\": \"应用交互组\", \"number\": \"108.001.005\", \"orgName1\": \"管理中心\", \"orgName2\": \"信息化部\", \"orgName3\": \"应用交互组\", \"orgName4\": \"\" }, \"position\": { \"id\": \"WV4qn8LYQOmFpjzkXD4djHSuYS4=\", \"name\": \"软件开发\", \"number\": \"A04\" } } ] }, { \"id\": \"rdEAAAAKFsu4hF0d\", \"jobId\": \"rdEAAAAKFsp1cCSY\", \"name\": \"算法岗\", \"number\": \"44\", \"positionInfoList\": [] } ], \"name\": \"软件\", \"number\": \"15\" }, { \"id\": \"rdEAAAAKFRqp9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAKFs64hF0d\", \"jobId\": \"rdEAAAAKFs11cCSY\", \"name\": \"电气岗\", \"number\": \"45\", \"positionInfoList\": [] } ], \"name\": \"电气\", \"number\": \"16\" }, { \"id\": \"rdEAAAAKFRSp9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAJcnC4hF0d\", \"jobId\": \"rdEAAAAJcm91cCSY\", \"name\": \"硬件开发岗\", \"number\": \"41\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAJcnO4hF0d\", \"jobId\": \"rdEAAAAJcnJ1cCSY\", \"name\": \"EMC岗\", \"number\": \"42\", \"positionInfoList\": [] } ], \"name\": \"硬件\", \"number\": \"14\" }, { \"id\": \"rdEAAAAKFSap9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAKFuO4hF0d\", \"jobId\": \"rdEAAAAKFuJ1cCSY\", \"name\": \"研发运维岗\", \"number\": \"52\", \"positionInfoList\": [] } ], \"name\": \"研发运维\", \"number\": \"20\" }, { \"id\": \"rdEAAAAKFSCp9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAKFtq4hF0d\", \"jobId\": \"rdEAAAAKFtl1cCSY\", \"name\": \"系统岗\", \"number\": \"49\", \"positionInfoList\": [] } ], \"name\": \"系统\", \"number\": \"18\" }, { \"id\": \"rdEAAAAKFSOp9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAKFt24hF0d\", \"jobId\": \"rdEAAAAKFtx1cCSY\", \"name\": \"研发工艺岗\", \"number\": \"50\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKFuC4hF0d\", \"jobId\": \"rdEAAAAKFt91cCSY\", \"name\": \"PCB设计岗\", \"number\": \"51\", \"positionInfoList\": [] } ], \"name\": \"产品工艺\", \"number\": \"19\" } ], \"name\": \"研究开发\", \"number\": \"12\" } ], \"id\": \"rdEAAAAJ7wJkHJrh\", \"name\": \"产品研发与技术\", \"number\": \"04\" }, { \"childSequenceList\": [ { \"id\": \"rdEAAAAKE8ajdRE7\", \"jobFamilyList\": [ { \"id\": \"rdEAAAAKFT6p9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAKFwe4hF0d\", \"jobId\": \"rdEAAAAKFwZ1cCSY\", \"name\": \"仓储岗\", \"number\": \"64\", \"positionInfoList\": [] } ], \"name\": \"仓储管理\", \"number\": \"28\" }, { \"id\": \"rdEAAAAKFUGp9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAKFwq4hF0d\", \"jobId\": \"rdEAAAAKFwl1cCSY\", \"name\": \"物流岗\", \"number\": \"65\", \"positionInfoList\": [] } ], \"name\": \"物流管理\", \"number\": \"29\" } ], \"name\": \"仓储物流管理\", \"number\": \"18\" }, { \"id\": \"rdEAAAAKE8CjdRE7\", \"jobFamilyList\": [ { \"id\": \"rdEAAAAKFTip9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAKFv64hF0d\", \"jobId\": \"rdEAAAAKFv11cCSY\", \"name\": \"计划岗\", \"number\": \"61\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKFwG4hF0d\", \"jobId\": \"rdEAAAAKFwB1cCSY\", \"name\": \"物控岗\", \"number\": \"62\", \"positionInfoList\": [] } ], \"name\": \"计划物控\", \"number\": \"26\" } ], \"name\": \"计划管理\", \"number\": \"16\" }, { \"id\": \"rdEAAAAKE8mjdRE7\", \"jobFamilyList\": [ { \"id\": \"rdEAAAAKFUSp9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAKFw24hF0d\", \"jobId\": \"rdEAAAAKFwx1cCSY\", \"name\": \"自动化岗\", \"number\": \"66\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKFxC4hF0d\", \"jobId\": \"rdEAAAAKFw91cCSY\", \"name\": \"MES岗\", \"number\": \"67\", \"positionInfoList\": [] } ], \"name\": \"设施与自动化\", \"number\": \"30\" } ], \"name\": \"设施与自动化\", \"number\": \"19\" }, { \"id\": \"rdEAAAAKE8OjdRE7\", \"jobFamilyList\": [ { \"id\": \"rdEAAAAKFTup9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAKFwS4hF0d\", \"jobId\": \"rdEAAAAKFwN1cCSY\", \"name\": \"采购岗\", \"number\": \"63\", \"positionInfoList\": [] } ], \"name\": \"采购\", \"number\": \"27\" } ], \"name\": \"采购管理\", \"number\": \"17\" }, { \"id\": \"rdEAAAAKE8yjdRE7\", \"jobFamilyList\": [ { \"id\": \"rdEAAAAKFUep9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAKFx+4hF0d\", \"jobId\": \"rdEAAAAKFx51cCSY\", \"name\": \"RE岗\", \"number\": \"72\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKFxa4hF0d\", \"jobId\": \"rdEAAAAKFxV1cCSY\", \"name\": \"PE岗\", \"number\": \"69\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKFxm4hF0d\", \"jobId\": \"rdEAAAAKFxh1cCSY\", \"name\": \"ME岗\", \"number\": \"70\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKFxO4hF0d\", \"jobId\": \"rdEAAAAKFxJ1cCSY\", \"name\": \"IE岗\", \"number\": \"68\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKFxy4hF0d\", \"jobId\": \"rdEAAAAKFxt1cCSY\", \"name\": \"TE岗\", \"number\": \"71\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKFyK4hF0d\", \"jobId\": \"rdEAAAAKFyF1cCSY\", \"name\": \"SMT岗\", \"number\": \"73\", \"positionInfoList\": [] } ], \"name\": \"工艺工程\", \"number\": \"31\" } ], \"name\": \"工艺工程\", \"number\": \"20\" } ], \"id\": \"rdEAAAAJ7wVkHJrh\", \"name\": \"供应链与生产技术\", \"number\": \"05\" }, { \"childSequenceList\": [ { \"id\": \"rdEAAAAKE5+jdRE7\", \"jobFamilyList\": [ { \"id\": \"rdEAAAAKFP+p9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAJcj24hF0d\", \"jobId\": \"rdEAAAAJcjx1cCSY\", \"name\": \"电商运营岗\", \"number\": \"23\", \"positionInfoList\": [ { \"org\": { \"id\": \"rdEAAAAADKDM567U\", \"name\": \"研发中心组\", \"number\": \"110.002.001\", \"orgName1\": \"人力资源中心\", \"orgName2\": \"HRBP部\", \"orgName3\": \"研发中心组\", \"orgName4\": \"\" }, \"position\": { \"id\": \"WZFT+9sGRqaPe9pcnyv7r3SuYS4=\", \"name\": \"HRBP-研发\", \"number\": \"ZW0000500\" } }, { \"org\": { \"id\": \"rdEAAAAADKDM567U\", \"name\": \"研发中心组\", \"number\": \"110.002.001\", \"orgName1\": \"人力资源中心\", \"orgName2\": \"HRBP部\", \"orgName3\": \"研发中心组\", \"orgName4\": \"\" }, \"position\": { \"id\": \"1WZ7Fya5R/GGP1LjtVvVpnSuYS4=\", \"name\": \"HRBP专业经理\", \"number\": \"ZW0002300\" } } ] }, { \"id\": \"rdEAAAAJcje4hF0d\", \"jobId\": \"rdEAAAAJcjZ1cCSY\", \"name\": \"渠道销售岗\", \"number\": \"21\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAJcjG4hF0d\", \"jobId\": \"rdEAAAAJcjB1cCSY\", \"name\": \"销售岗\", \"number\": \"19\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAJcjq4hF0d\", \"jobId\": \"rdEAAAAJcjl1cCSY\", \"name\": \"BD岗\", \"number\": \"22\", \"positionInfoList\": [ { \"org\": { \"id\": \"rdEAAAAABELM567U\", \"name\": \"人力资源中心\", \"number\": \"110\", \"orgName1\": \"人力资源中心\", \"orgName2\": \"\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"IEigT6EHRJuX7DEitehi23SuYS4=\", \"name\": \"招聘负责人\", \"number\": \"M1-001.003\" } }, { \"org\": { \"id\": \"rdEAAAAADITM567U\", \"name\": \"国内校招组\", \"number\": \"110.001.001\", \"orgName1\": \"人力资源中心\", \"orgName2\": \"招聘部\", \"orgName3\": \"国内校招组\", \"orgName4\": \"\" }, \"position\": { \"id\": \"BlcYuSPyRvyWUsvpDU2fv3SuYS4=\", \"name\": \"招聘专员\", \"number\": \"ZW0000577\" } }, { \"org\": { \"id\": \"rdEAAAAADITM567U\", \"name\": \"国内校招组\", \"number\": \"110.001.001\", \"orgName1\": \"人力资源中心\", \"orgName2\": \"招聘部\", \"orgName3\": \"国内校招组\", \"orgName4\": \"\" }, \"position\": { \"id\": \"xaRZZcrMS6KLWxCvCYGj+XSuYS4=\", \"name\": \"高级招聘专员\", \"number\": \"ZW0000579\" } }, { \"org\": { \"id\": \"rdEAAAAADI7M567U\", \"name\": \"国内社招组\", \"number\": \"110.001.002\", \"orgName1\": \"人力资源中心\", \"orgName2\": \"招聘部\", \"orgName3\": \"国内社招组\", \"orgName4\": \"\" }, \"position\": { \"id\": \"D5kdi9dPQ8Osm4tPnL/M0HSuYS4=\", \"name\": \"招聘专员\", \"number\": \"ZW0000566\" } }, { \"org\": { \"id\": \"rdEAAAAADJfM567U\", \"name\": \"海外招聘组\", \"number\": \"110.001.003\", \"orgName1\": \"人力资源中心\", \"orgName2\": \"招聘部\", \"orgName3\": \"海外招聘组\", \"orgName4\": \"\" }, \"position\": { \"id\": \"y1RXlRunSbK8FxxkYox+jnSuYS4=\", \"name\": \"招聘专员\", \"number\": \"ZW0000489\" } } ] }, { \"id\": \"rdEAAAAJcjS4hF0d\", \"jobId\": \"rdEAAAAJcjN1cCSY\", \"name\": \"大客户销售岗\", \"number\": \"20\", \"positionInfoList\": [ { \"org\": { \"id\": \"rdEAAAAABwzM567U\", \"name\": \"合作客户组\", \"number\": \"101.001.006\", \"orgName1\": \"全球营销中心1\", \"orgName2\": \"中国区\", \"orgName3\": \"合作客户组\", \"orgName4\": \"\" }, \"position\": { \"id\": \"Xv3kkDzfQG++7nu/JgACRnSuYS4=\", \"name\": \"大客户销售经理\", \"number\": \"ZW0000454\" } }, { \"org\": { \"id\": \"rdEAAAAABwzM567U\", \"name\": \"合作客户组\", \"number\": \"101.001.006\", \"orgName1\": \"全球营销中心1\", \"orgName2\": \"中国区\", \"orgName3\": \"合作客户组\", \"orgName4\": \"\" }, \"position\": { \"id\": \"R86oFo7bQLuQa2eCg+1X83SuYS4=\", \"name\": \"高级大客户销售经理\", \"number\": \"ZW0000459\" } }, { \"org\": { \"id\": \"rdEAAAAABxXM567U\", \"name\": \"战略客户组\", \"number\": \"101.001.007\", \"orgName1\": \"全球营销中心1\", \"orgName2\": \"中国区\", \"orgName3\": \"战略客户组\", \"orgName4\": \"\" }, \"position\": { \"id\": \"/CwBiFNCT9+GPxZwDsFXQ3SuYS4=\", \"name\": \"大客户销售经理\", \"number\": \"ZW0000485\" } }, { \"org\": { \"id\": \"rdEAAAAAByfM567U\", \"name\": \"数据中心组\", \"number\": \"101.001.009\", \"orgName1\": \"全球营销中心1\", \"orgName2\": \"中国区\", \"orgName3\": \"数据中心组\", \"orgName4\": \"\" }, \"position\": { \"id\": \"wIx+quOSS3y/ZqfafKEaaHSuYS4=\", \"name\": \"大客户销售经理\", \"number\": \"ZW0000584\" } }, { \"org\": { \"id\": \"rdEAAAAABILM567U\", \"name\": \"日本区\", \"number\": \"101.004\", \"orgName1\": \"全球营销中心1\", \"orgName2\": \"日本区\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"o+bTzZnSTsWaFk+wrYSqJ3SuYS4=\", \"name\": \"大客户销售经理\", \"number\": \"ZW0002276\" } } ] } ], \"name\": \"销售\", \"number\": \"07\" } ], \"name\": \"销售\", \"number\": \"05\" }, { \"id\": \"rdEAAAAKE5mjdRE7\", \"jobFamilyList\": [ { \"id\": \"rdEAAAAKFPmp9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAJcii4hF0d\", \"jobId\": \"rdEAAAAJcid1cCSY\", \"name\": \"数字营销岗\", \"number\": \"16\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAJciK4hF0d\", \"jobId\": \"rdEAAAAJciF1cCSY\", \"name\": \"市场推广岗\", \"number\": \"14\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAJciu4hF0d\", \"jobId\": \"rdEAAAAJcip1cCSY\", \"name\": \"整合营销岗\", \"number\": \"17\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAJciW4hF0d\", \"jobId\": \"rdEAAAAJciR1cCSY\", \"name\": \"市场研究岗\", \"number\": \"15\", \"positionInfoList\": [] } ], \"name\": \"市场运营\", \"number\": \"05\" } ], \"name\": \"市场运营\", \"number\": \"03\" }, { \"id\": \"rdEAAAAKE5yjdRE7\", \"jobFamilyList\": [ { \"id\": \"rdEAAAAKFPyp9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAJci64hF0d\", \"jobId\": \"rdEAAAAJci11cCSY\", \"name\": \"品牌管理岗\", \"number\": \"18\", \"positionInfoList\": [] } ], \"name\": \"品牌管理\", \"number\": \"06\" } ], \"name\": \"品牌管理\", \"number\": \"04\" }, { \"id\": \"rdEAAAAKE6ijdRE7\", \"jobFamilyList\": [ { \"id\": \"rdEAAAAKFQip9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAJck+4hF0d\", \"jobId\": \"rdEAAAAJck51cCSY\", \"name\": \"技术服务岗\", \"number\": \"30\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAJcl64hF0d\", \"jobId\": \"rdEAAAAJcl11cCSY\", \"name\": \"交付及售后岗\", \"number\": \"35\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAJcli4hF0d\", \"jobId\": \"rdEAAAAJcld1cCSY\", \"name\": \"服务运营岗\", \"number\": \"33\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAJclK4hF0d\", \"jobId\": \"rdEAAAAJclF1cCSY\", \"name\": \"远程服务岗\", \"number\": \"31\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAJclu4hF0d\", \"jobId\": \"rdEAAAAJclp1cCSY\", \"name\": \"备件运营岗\", \"number\": \"34\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKGAm4hF0d\", \"jobId\": \"rdEAAAAKGAh1cCSY\", \"name\": \"售后支持岗\", \"number\": \"150\", \"positionInfoList\": [] } ], \"name\": \"售后服务\", \"number\": \"10\" } ], \"name\": \"售后服务\", \"number\": \"08\" }, { \"id\": \"rdEAAAAKE6KjdRE7\", \"jobFamilyList\": [ { \"id\": \"rdEAAAAKFQKp9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAJcka4hF0d\", \"jobId\": \"rdEAAAAJckV1cCSY\", \"name\": \"销售支持岗\", \"number\": \"27\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAJckC4hF0d\", \"jobId\": \"rdEAAAAJcj91cCSY\", \"name\": \"销售运营岗\", \"number\": \"24\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAJckm4hF0d\", \"jobId\": \"rdEAAAAJckh1cCSY\", \"name\": \"投标运营岗\", \"number\": \"28\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAJckO4hF0d\", \"jobId\": \"rdEAAAAJckJ1cCSY\", \"name\": \"商务岗\", \"number\": \"26\", \"positionInfoList\": [ { \"org\": { \"id\": \"rdEAAAAADOHM567U\", \"name\": \"薪酬和绩效组\", \"number\": \"110.005.001\", \"orgName1\": \"人力资源中心\", \"orgName2\": \"OD&COE部\", \"orgName3\": \"薪酬和绩效组\", \"orgName4\": \"\" }, \"position\": { \"id\": \"lTfRcp8AR2GnaOrRVi2ZZnSuYS4=\", \"name\": \"高级薪酬福利专员\", \"number\": \"ZW0000505\" } } ] }, { \"id\": \"rdEAAAAKFqO4hF0d\", \"jobId\": \"rdEAAAAKFqJ1cCSY\", \"name\": \"订单交付岗\", \"number\": \"25\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKGAy4hF0d\", \"jobId\": \"rdEAAAAKGAt1cCSY\", \"name\": \"销售辅助岗\", \"number\": \"151\", \"positionInfoList\": [] } ], \"name\": \"销售管理\", \"number\": \"08\" } ], \"name\": \"销售管理\", \"number\": \"06\" }, { \"id\": \"rdEAAAAKE6ujdRE7\", \"jobFamilyList\": [ { \"id\": \"rdEAAAAKFQup9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAJclW4hF0d\", \"jobId\": \"rdEAAAAJclR1cCSY\", \"name\": \"客服岗\", \"number\": \"32\", \"positionInfoList\": [] } ], \"name\": \"客户服务\", \"number\": \"11\" } ], \"name\": \"客户服务\", \"number\": \"09\" }, { \"id\": \"rdEAAAAKE6WjdRE7\", \"jobFamilyList\": [ { \"id\": \"rdEAAAAKFQWp9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAJcky4hF0d\", \"jobId\": \"rdEAAAAJckt1cCSY\", \"name\": \"客户管理岗\", \"number\": \"29\", \"positionInfoList\": [] } ], \"name\": \"客户管理\", \"number\": \"09\" } ], \"name\": \"客户管理\", \"number\": \"07\" } ], \"id\": \"rdEAAAAJVxRkHJrh\", \"name\": \"市场营销\", \"number\": \"03\" }, { \"childSequenceList\": [ { \"id\": \"rdEAAAAKNWijdRE7\", \"jobFamilyList\": [ { \"id\": \"rdEAAAAKNVap9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAKNXK4hF0d\", \"jobId\": \"rdEAAAAKNXF1cCSY\", \"name\": \"测试职务\", \"number\": \"0199\", \"positionInfoList\": [] } ], \"name\": \"测试职务子类\", \"number\": \"199\" } ], \"name\": \"测试职务类\", \"number\": \"99\" } ], \"id\": \"rdEAAAAKNV9kHJrh\", \"name\": \"测试职务族\", \"number\": \"88\" } ], \"name\": \"专业类\", \"number\": \"01\" }, { \"id\": \"rdEAAAAJVvz2V1KN\", \"jobSequenceList\": [ { \"childSequenceList\": [ { \"id\": \"rdEAAAAKE5ajdRE7\", \"jobFamilyList\": [ { \"id\": \"rdEAAAAKFPap9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAJch+4hF0d\", \"jobId\": \"rdEAAAAJch51cCSY\", \"name\": \"仓库管理\", \"number\": \"13\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAJchm4hF0d\", \"jobId\": \"rdEAAAAJchh1cCSY\", \"name\": \"生产管理\", \"number\": \"11\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKFn+4hF0d\", \"jobId\": \"rdEAAAAKFn51cCSY\", \"name\": \"部门负责人\", \"number\": \"08\", \"positionInfoList\": [] } ], \"name\": \"基层管理\", \"number\": \"04\" }, { \"id\": \"rdEAAAAKFPCp9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAKFnm4hF0d\", \"jobId\": \"rdEAAAAKFnh1cCSY\", \"name\": \"部门负责人\", \"number\": \"06\", \"positionInfoList\": [ { \"org\": { \"id\": \"rdEAAAAABt7M567U\", \"name\": \"南区\", \"number\": \"101.001.001\", \"orgName1\": \"全球营销中心1\", \"orgName2\": \"中国区\", \"orgName3\": \"南区\", \"orgName4\": \"\" }, \"position\": { \"id\": \"JVY9vhFyS3y3ra+yJM4LsXSuYS4=\", \"name\": \"区域销售总监\", \"number\": \"ZW0000471\" } }, { \"org\": { \"id\": \"rdEAAAAABujM567U\", \"name\": \"北区\", \"number\": \"101.001.002\", \"orgName1\": \"全球营销中心1\", \"orgName2\": \"中国区\", \"orgName3\": \"北区\", \"orgName4\": \"\" }, \"position\": { \"id\": \"kFOXViAoQzSLZx8n4sK5vnSuYS4=\", \"name\": \"区域销售总监\", \"number\": \"ZW0000435\" } }, { \"org\": { \"id\": \"rdEAAAAABvHM567U\", \"name\": \"东区\", \"number\": \"101.001.003\", \"orgName1\": \"全球营销中心1\", \"orgName2\": \"中国区\", \"orgName3\": \"东区\", \"orgName4\": \"\" }, \"position\": { \"id\": \"VMDH/bHdTm2ZEXU3hWAZznSuYS4=\", \"name\": \"区域销售总监\", \"number\": \"ZW0000443\" } }, { \"org\": { \"id\": \"rdEAAAAABwPM567U\", \"name\": \"西北区\", \"number\": \"101.001.005\", \"orgName1\": \"全球营销中心1\", \"orgName2\": \"中国区\", \"orgName3\": \"西北区\", \"orgName4\": \"\" }, \"position\": { \"id\": \"Te1LeBQ2Sg+bM35bTmdl23SuYS4=\", \"name\": \"区域销售总监\", \"number\": \"ZW0000478\" } }, { \"org\": { \"id\": \"rdEAAAAABwzM567U\", \"name\": \"合作客户组\", \"number\": \"101.001.006\", \"orgName1\": \"全球营销中心1\", \"orgName2\": \"中国区\", \"orgName3\": \"合作客户组\", \"orgName4\": \"\" }, \"position\": { \"id\": \"QYoXiQTFR/+OD+NFXFFjSnSuYS4=\", \"name\": \"大客户销售总监\", \"number\": \"ZW0000451\" } }, { \"org\": { \"id\": \"rdEAAAAABwzM567U\", \"name\": \"合作客户组\", \"number\": \"101.001.006\", \"orgName1\": \"全球营销中心1\", \"orgName2\": \"中国区\", \"orgName3\": \"合作客户组\", \"orgName4\": \"\" }, \"position\": { \"id\": \"Ssh9rN0mQwWWPt7MDRVJDnSuYS4=\", \"name\": \"中国区大客户销售总监\", \"number\": \"ZW0002373\" } }, { \"org\": { \"id\": \"rdEAAAAABxXM567U\", \"name\": \"战略客户组\", \"number\": \"101.001.007\", \"orgName1\": \"全球营销中心1\", \"orgName2\": \"中国区\", \"orgName3\": \"战略客户组\", \"orgName4\": \"\" }, \"position\": { \"id\": \"TiFh2Q2jRr23q3em5peZo3SuYS4=\", \"name\": \"战略客户部副总监\", \"number\": \"ZW0000482\" } }, { \"org\": { \"id\": \"rdEAAAAAByfM567U\", \"name\": \"数据中心组\", \"number\": \"101.001.009\", \"orgName1\": \"全球营销中心1\", \"orgName2\": \"中国区\", \"orgName3\": \"数据中心组\", \"orgName4\": \"\" }, \"position\": { \"id\": \"O6vYB2ugSDWlcGtGbLpqEHSuYS4=\", \"name\": \"BD总监\", \"number\": \"ZW0000583\" } }, { \"org\": { \"id\": \"rdEAAAAABIvM567U\", \"name\": \"中东非区\", \"number\": \"101.005\", \"orgName1\": \"全球营销中心1\", \"orgName2\": \"中东非区\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"TwXvJM6KT+CAh8z1LmR4vXSuYS4=\", \"name\": \"中东非大区总监\", \"number\": \"ZW0000428\" } }, { \"org\": { \"id\": \"rdEAAAAACDjM567U\", \"name\": \"巴西\", \"number\": \"101.006.001\", \"orgName1\": \"全球营销中心1\", \"orgName2\": \"拉美区\", \"orgName3\": \"巴西\", \"orgName4\": \"\" }, \"position\": { \"id\": \"9ZUSaNFeTzys9ucrTVIIeXSuYS4=\", \"name\": \"海外区域销售总监\", \"number\": \"ZW0000175\" } }, { \"org\": { \"id\": \"rdEAAAAABKbM567U\", \"name\": \"市场部\", \"number\": \"101.008\", \"orgName1\": \"全球营销中心1\", \"orgName2\": \"市场部\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"TnRJgYSNQ/6/CfJTSOFUNXSuYS4=\", \"name\": \"市场总监\", \"number\": \"ZW0000316\" } }, { \"org\": { \"id\": \"rdEAAAAABK/M567U\", \"name\": \"市场产品部\", \"number\": \"101.009\", \"orgName1\": \"全球营销中心1\", \"orgName2\": \"市场产品部\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"j2PSBiI1QjW3mpvNRGqrrXSuYS4=\", \"name\": \"产品营销总监\", \"number\": \"ZW0000317\" } }, { \"org\": { \"id\": \"rdEAAAAABLjM567U\", \"name\": \"全球服务部\", \"number\": \"101.010\", \"orgName1\": \"全球营销中心1\", \"orgName2\": \"全球服务部\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"W0GE0fTcSz+AEXvBv2ZBNXSuYS4=\", \"name\": \"全球服务部总监\", \"number\": \"ZW0000289\" } }, { \"org\": { \"id\": \"rdEAAAAABMHM567U\", \"name\": \"销售运营与管理部\", \"number\": \"101.011\", \"orgName1\": \"全球营销中心1\", \"orgName2\": \"销售运营与管理部\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"dYrCbm+nRVyB9k2KV93UtnSuYS4=\", \"name\": \"销售运营和管理部副总监\", \"number\": \"ZW0000377\" } }, { \"org\": { \"id\": \"rdEAAAAABMHM567U\", \"name\": \"销售运营与管理部\", \"number\": \"101.011\", \"orgName1\": \"全球营销中心1\", \"orgName2\": \"销售运营与管理部\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"SVv+Q2/WR4G89tgsJ+MmXnSuYS4=\", \"name\": \"销售运营和管理部总监\", \"number\": \"ZW0000378\" } }, { \"org\": { \"id\": \"rdEAAAAABPDM567U\", \"name\": \"交付及售后部\", \"number\": \"103.002\", \"orgName1\": \"集中储能业务线\", \"orgName2\": \"交付及售后部\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"xQ5eephQSz2Orx9fDlQXt3SuYS4=\", \"name\": \"交付及售后总监\", \"number\": \"ZW0002534\" } }, { \"org\": { \"id\": \"rdEAAAAABSfM567U\", \"name\": \"产品规划部\", \"number\": \"105.001\", \"orgName1\": \"移动储能业务线\", \"orgName2\": \"产品规划部\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"jhHAzbWsT42hFkAVK6GDn3SuYS4=\", \"name\": \"产品副总监\", \"number\": \"ZW0000995\" } }, { \"org\": { \"id\": \"rdEAAAAABXrM567U\", \"name\": \"车用能源产品线\", \"number\": \"106.005\", \"orgName1\": \"研发中心\", \"orgName2\": \"车用能源产品线\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"fU7pWrW7Qc+RrVtEBUuPq3SuYS4=\", \"name\": \"研发副总监\", \"number\": \"ZW0000638\" } }, { \"org\": { \"id\": \"rdEAAAAABYPM567U\", \"name\": \"集中式储能产品线\", \"number\": \"106.006\", \"orgName1\": \"研发中心\", \"orgName2\": \"集中式储能产品线\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"ZbOaDVCHR4uj5LqCGHVkUHSuYS4=\", \"name\": \"大储能技术总监\", \"number\": \"ZW0000679\" } }, { \"org\": { \"id\": \"rdEAAAAABYPM567U\", \"name\": \"集中式储能产品线\", \"number\": \"106.006\", \"orgName1\": \"研发中心\", \"orgName2\": \"集中式储能产品线\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"AvGJsk6+S1quh2DV4gjPPnSuYS4=\", \"name\": \"集中式储能技术总监\", \"number\": \"ZW0002417\" } }, { \"org\": { \"id\": \"rdEAAAAABYzM567U\", \"name\": \"数据中心能源产品线\", \"number\": \"106.007\", \"orgName1\": \"研发中心\", \"orgName2\": \"数据中心能源产品线\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"+SrYcTfBSjyShfX8YxZDSnSuYS4=\", \"name\": \"上海分公司研发总监；数据中心负责人\", \"number\": \"ZW0000900\" } }, { \"org\": { \"id\": \"rdEAAAAABbnM567U\", \"name\": \"硬件部\", \"number\": \"106.012\", \"orgName1\": \"研发中心\", \"orgName2\": \"硬件部\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"j8Mdh7gTSvGrYvaPuqT5x3SuYS4=\", \"name\": \"硬件总监\", \"number\": \"ZW0000974\" } }, { \"org\": { \"id\": \"rdEAAAAACrbM567U\", \"name\": \"结构部\", \"number\": \"106.015.002\", \"orgName1\": \"研发中心\", \"orgName2\": \"平台部\", \"orgName3\": \"结构部\", \"orgName4\": \"\" }, \"position\": { \"id\": \"QtH3xg6sTb2nOwtxbVSWpnSuYS4=\", \"name\": \"结构总监\", \"number\": \"ZW0000728\" } }, { \"org\": { \"id\": \"rdEAAAAABebM567U\", \"name\": \"采购部\", \"number\": \"107.001\", \"orgName1\": \"制造运营中心\", \"orgName2\": \"采购部\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"oewAi34TTLGi2hicoN0oeHSuYS4=\", \"name\": \"供应链总监\", \"number\": \"ZW0001011\" } }, { \"org\": { \"id\": \"rdEAAAAABfDM567U\", \"name\": \"计划物控部\", \"number\": \"107.002\", \"orgName1\": \"制造运营中心\", \"orgName2\": \"计划物控部\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"/9lJ78qBSLaGTM6JbnHcZ3SuYS4=\", \"name\": \"PMC总监\", \"number\": \"ZW0001050\" } }, { \"org\": { \"id\": \"rdEAAAAABfnM567U\", \"name\": \"制造部\", \"number\": \"107.003\", \"orgName1\": \"制造运营中心\", \"orgName2\": \"制造部\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"E0DX5FWJS+6gRT8bZFGKj3SuYS4=\", \"name\": \"生产总监\", \"number\": \"ZW0001152\" } }, { \"org\": { \"id\": \"rdEAAAAABgvM567U\", \"name\": \"制造运营管理部\", \"number\": \"107.005\", \"orgName1\": \"制造运营中心\", \"orgName2\": \"制造运营管理部\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"mvbbQq4bSDuiyumx/xZqbXSuYS4=\", \"name\": \"新产能规划总监\", \"number\": \"ZW0002322\" } }, { \"org\": { \"id\": \"rdEAAAAABhTM567U\", \"name\": \"信息化部\", \"number\": \"108.001\", \"orgName1\": \"管理中心\", \"orgName2\": \"信息化部\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"JFArEWf0SuermHJNTWH3knSuYS4=\", \"name\": \"信息化部副总监\", \"number\": \"ZW0002261\" } }, { \"org\": { \"id\": \"rdEAAAABEXHM567U\", \"name\": \"质检中心\", \"number\": \"114\", \"orgName1\": \"质检中心\", \"orgName2\": \"品质部\", \"orgName3\": \"质检中心\", \"orgName4\": \"\" }, \"position\": { \"id\": \"SWHpvlchTM+Ou/2kZUl77XSuYS4=\", \"name\": \"质检总监\", \"number\": \"ZW0002536\" } }, { \"org\": { \"id\": \"rdEAAAAABifM567U\", \"name\": \"法务部\", \"number\": \"108.003\", \"orgName1\": \"管理中心\", \"orgName2\": \"法务部\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"OxXtaxb7SFST5tvybQVlWXSuYS4=\", \"name\": \"法务总监\", \"number\": \"ZW0000073\" } }, { \"org\": { \"id\": \"rdEAAAAABifM567U\", \"name\": \"法务部\", \"number\": \"108.003\", \"orgName1\": \"管理中心\", \"orgName2\": \"法务部\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"wE0lJM2gSMCkj3Tau3NF5XSuYS4=\", \"name\": \"全球法务副总监\", \"number\": \"ZW0000074\" } }, { \"org\": { \"id\": \"rdEAAAAABjDM567U\", \"name\": \"经营管理部\", \"number\": \"108.004\", \"orgName1\": \"管理中心\", \"orgName2\": \"经营管理部\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"ge08q9+cR+uaDdPXI/InLXSuYS4=\", \"name\": \"经营管理部副总监\", \"number\": \"ZW0000079\" } }, { \"org\": { \"id\": \"rdEAAAAABkLM567U\", \"name\": \"总裁办\", \"number\": \"108.006\", \"orgName1\": \"管理中心\", \"orgName2\": \"总裁办\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"GgYVTnC1Smuefo4psIJyXHSuYS4=\", \"name\": \"投融资总监\", \"number\": \"ZW0000157\" } }, { \"org\": { \"id\": \"rdEAAAAABDnM567U\", \"name\": \"财务中心\", \"number\": \"109\", \"orgName1\": \"财务中心\", \"orgName2\": \"\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"zuWcqmdITE+Me4e1iG8D3nSuYS4=\", \"name\": \"财务中心高级总监\", \"number\": \"M1-109\" } }, { \"org\": { \"id\": \"rdEAAAAABDnM567U\", \"name\": \"财务中心\", \"number\": \"109\", \"orgName1\": \"财务中心\", \"orgName2\": \"\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"HV9qQZWdTjOPWvMjZRcrdnSuYS4=\", \"name\": \"财务总监\", \"number\": \"ZW0000054\" } }, { \"org\": { \"id\": \"rdEAAAAABDnM567U\", \"name\": \"财务中心\", \"number\": \"109\", \"orgName1\": \"财务中心\", \"orgName2\": \"\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"NQbkv+4cRDaEzDfDovI4K3SuYS4=\", \"name\": \"财务副总监\", \"number\": \"ZW0000055\" } }, { \"org\": { \"id\": \"rdEAAAAABELM567U\", \"name\": \"人力资源中心\", \"number\": \"110\", \"orgName1\": \"人力资源中心\", \"orgName2\": \"\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"oD8GDtlNTUuBZQGhbLIq53SuYS4=\", \"name\": \"人力资源中心总监\", \"number\": \"M1-001\" } }, { \"org\": { \"id\": \"rdEAAAAABELM567U\", \"name\": \"人力资源中心\", \"number\": \"110\", \"orgName1\": \"人力资源中心\", \"orgName2\": \"\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"EVSJIr+4TVONVYNAdRy0AXSuYS4=\", \"name\": \"人力资源总监\", \"number\": \"ZW0000582\" } }, { \"org\": { \"id\": \"rdEAAAAABsLM567U\", \"name\": \"行政部\", \"number\": \"110.007\", \"orgName1\": \"人力资源中心\", \"orgName2\": \"行政部\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"zvit+VvqSGO5yasyKEcAB3SuYS4=\", \"name\": \"行政总监\", \"number\": \"ZW0000563\" } }, { \"org\": { \"id\": \"rdEAAAAADSPM567U\", \"name\": \"区域管理组\", \"number\": \"110.007.004\", \"orgName1\": \"人力资源中心\", \"orgName2\": \"行政部\", \"orgName3\": \"区域管理组\", \"orgName4\": \"\" }, \"position\": { \"id\": \"0qp4lZeHQlmfNTKmJHaqcHSuYS4=\", \"name\": \"行政副总监\", \"number\": \"ZW0000545\" } }, { \"org\": { \"id\": \"rdEAAAAABtXM567U\", \"name\": \"投资者关系部\", \"number\": \"111.002\", \"orgName1\": \"董事会办公室\", \"orgName2\": \"投资者关系部\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"BG+Hv2xFQ9eb+603QTeSd3SuYS4=\", \"name\": \"投资者关系总监\", \"number\": \"ZW0000068\" } } ] }, { \"id\": \"rdEAAAAKFoK4hF0d\", \"jobId\": \"rdEAAAAKFoF1cCSY\", \"name\": \"投融资\", \"number\": \"09\", \"positionInfoList\": [] } ], \"name\": \"高层管理\", \"number\": \"02\" }, { \"id\": \"rdEAAAAKFPOp9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAJcha4hF0d\", \"jobId\": \"rdEAAAAJchV1cCSY\", \"name\": \"生产管理\", \"number\": \"10\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAJchy4hF0d\", \"jobId\": \"rdEAAAAJcht1cCSY\", \"name\": \"仓库管理\", \"number\": \"12\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKFny4hF0d\", \"jobId\": \"rdEAAAAKFnt1cCSY\", \"name\": \"部门负责人\", \"number\": \"07\", \"positionInfoList\": [ { \"org\": { \"id\": \"rdEAAAAACdDM567U\", \"name\": \"SEG\", \"number\": \"106.009.001\", \"orgName1\": \"研发中心\", \"orgName2\": \"电池产品平台\", \"orgName3\": \"SEG\", \"orgName4\": \"\" }, \"position\": { \"id\": \"1/qrQfH1QQyDLowM8T7TH3SuYS4=\", \"name\": \"BMS算法高级经理\", \"number\": \"ZW0000799\" } }, { \"org\": { \"id\": \"rdEAAAAABafM567U\", \"name\": \"智慧能源管理产品平台\", \"number\": \"106.010\", \"orgName1\": \"研发中心\", \"orgName2\": \"智慧能源管理产品平台\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"0TzEywTdQ52UDwGlCmFcj3SuYS4=\", \"name\": \"智云高级经理\", \"number\": \"ZW0002362\" } }, { \"org\": { \"id\": \"rdEAAAAABafM567U\", \"name\": \"智慧能源管理产品平台\", \"number\": \"106.010\", \"orgName1\": \"研发中心\", \"orgName2\": \"智慧能源管理产品平台\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"Uh/dqy62R5e6KS3f4UcEDnSuYS4=\", \"name\": \"智控高级经理\", \"number\": \"ZW0002535\" } }, { \"org\": { \"id\": \"rdEAAAAABbDM567U\", \"name\": \"物联产品平台\", \"number\": \"106.011\", \"orgName1\": \"研发中心\", \"orgName2\": \"物联产品平台\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"wPFLGminTxW6T9SvWwCrS3SuYS4=\", \"name\": \"软件开发经理\", \"number\": \"ZW0000985\" } }, { \"org\": { \"id\": \"rdEAAAAABcLM567U\", \"name\": \"软件部\", \"number\": \"106.013\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"nJqLfkOzSaiQIlyNlel4H3SuYS4=\", \"name\": \"软件经理\", \"number\": \"ZW0002434\" } }, { \"org\": { \"id\": \"rdEAAAAACjbM567U\", \"name\": \"软件平台部\", \"number\": \"106.013.001\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件平台部\", \"orgName4\": \"\" }, \"position\": { \"id\": \"H4nUfcrrTpKgBCLMfSVGCHSuYS4=\", \"name\": \"ARM软件经理\", \"number\": \"ZW0000778\" } }, { \"org\": { \"id\": \"rdEAAAAACjbM567U\", \"name\": \"软件平台部\", \"number\": \"106.013.001\", \"orgName1\": \"研发中心\", \"orgName2\": \"软件部\", \"orgName3\": \"软件平台部\", \"orgName4\": \"\" }, \"position\": { \"id\": \"2O5Pt29nQVOVF8eTL0bAHXSuYS4=\", \"name\": \"软件经理\", \"number\": \"ZW0002435\" } }, { \"org\": { \"id\": \"rdEAAAAABifM567U\", \"name\": \"法务部\", \"number\": \"108.003\", \"orgName1\": \"管理中心\", \"orgName2\": \"法务部\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"XcIe8DY0R+alpjZzAwXlx3SuYS4=\", \"name\": \"海外法务高级经理\", \"number\": \"ZW0002348\" } }, { \"org\": { \"id\": \"rdEAAAAADJfM567U\", \"name\": \"海外招聘组\", \"number\": \"110.001.003\", \"orgName1\": \"人力资源中心\", \"orgName2\": \"招聘部\", \"orgName3\": \"海外招聘组\", \"orgName4\": \"\" }, \"position\": { \"id\": \"Wpqt/lEjQn+AqeoNjXdo7HSuYS4=\", \"name\": \"招聘经理\", \"number\": \"ZW0000488\" } } ] } ], \"name\": \"中层管理\", \"number\": \"03\" } ], \"name\": \"管理层\", \"number\": \"02\" }, { \"id\": \"rdEAAAAKE5OjdRE7\", \"jobFamilyList\": [ { \"id\": \"rdEAAAAKFO2p9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAACSkq4hF0d\", \"jobId\": \"rdEAAAACSkl1cCSY\", \"name\": \"常务副总裁\", \"number\": \"02\", \"positionInfoList\": [ { \"org\": { \"id\": \"rdEAAAAAC8HM567U\", \"name\": \"应用交互组\", \"number\": \"108.001.005\", \"orgName1\": \"管理中心\", \"orgName2\": \"信息化部\", \"orgName3\": \"应用交互组\", \"orgName4\": \"\" }, \"position\": { \"id\": \"OAXiZ1BsRoy1sLLk/Gxt53SuYS4=\", \"name\": \"软件工程师\", \"number\": \"ZW0000151\" } }, { \"org\": { \"id\": \"rdEAAAAAC8HM567U\", \"name\": \"应用交互组\", \"number\": \"108.001.005\", \"orgName1\": \"管理中心\", \"orgName2\": \"信息化部\", \"orgName3\": \"应用交互组\", \"orgName4\": \"\" }, \"position\": { \"id\": \"+5om+dg6R3yvKlvK0/MetHSuYS4=\", \"name\": \"高级Java工程师\", \"number\": \"ZW0000156\" } }, { \"org\": { \"id\": \"rdEAAAAADITM567U\", \"name\": \"国内校招组\", \"number\": \"110.001.001\", \"orgName1\": \"人力资源中心\", \"orgName2\": \"招聘部\", \"orgName3\": \"国内校招组\", \"orgName4\": \"\" }, \"position\": { \"id\": \"5japs17ZTAy4HFiIdvtuVHSuYS4=\", \"name\": \"实习生\", \"number\": \"ZW0000565\" } } ] }, { \"id\": \"rdEAAAAKFmu4hF0d\", \"jobId\": \"rdEAAAAKFmp1cCSY\", \"name\": \"总裁\", \"number\": \"01\", \"positionInfoList\": [ { \"org\": { \"id\": \"rdEAAAAABkLM567U\", \"name\": \"总裁办\", \"number\": \"108.006\", \"orgName1\": \"管理中心\", \"orgName2\": \"总裁办\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"j7sLvb3oSdqVyWQ3NN/OwnSuYS4=\", \"name\": \"总裁\", \"number\": \"ZW0000158\" } } ] }, { \"id\": \"rdEAAAAKFna4hF0d\", \"jobId\": \"rdEAAAAKFnV1cCSY\", \"name\": \"助理副总裁\", \"number\": \"05\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKFnC4hF0d\", \"jobId\": \"rdEAAAAKFm91cCSY\", \"name\": \"高级副总裁\", \"number\": \"03\", \"positionInfoList\": [ { \"org\": { \"id\": \"rdEAAAAAA/HM567U\", \"name\": \"全球营销中心1\", \"number\": \"101\", \"orgName1\": \"全球营销中心1\", \"orgName2\": \"\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"eAESIy3HSwqmX7aih8OdL3SuYS4=\", \"name\": \"公司高级副总裁\", \"number\": \"ZW0000487\" } }, { \"org\": { \"id\": \"rdEAAAAABB7M567U\", \"name\": \"研发中心\", \"number\": \"106\", \"orgName1\": \"研发中心\", \"orgName2\": \"\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"Ls5C9ySLQCyIy6W+V9v5Z3SuYS4=\", \"name\": \"公司高级副总裁\", \"number\": \"ZW0000989\" } }, { \"org\": { \"id\": \"rdEAAAAABCfM567U\", \"name\": \"制造运营中心\", \"number\": \"107\", \"orgName1\": \"制造运营中心\", \"orgName2\": \"\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"P8+aZ8ydRbeAL9RWHz7vk3SuYS4=\", \"name\": \"公司高级副总裁\", \"number\": \"ZW0001155\" } } ] }, { \"id\": \"rdEAAAAKFnO4hF0d\", \"jobId\": \"rdEAAAAKFnJ1cCSY\", \"name\": \"副总裁\", \"number\": \"04\", \"positionInfoList\": [ { \"org\": { \"id\": \"rdEAAAAABAPM567U\", \"name\": \"集中储能业务线\", \"number\": \"103\", \"orgName1\": \"集中储能业务线\", \"orgName2\": \"\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"Fkxhx8akRCKgPAWvnhWEiXSuYS4=\", \"name\": \"副总裁\", \"number\": \"ZW0000164\" } }, { \"org\": { \"id\": \"rdEAAAAABkLM567U\", \"name\": \"总裁办\", \"number\": \"108.006\", \"orgName1\": \"管理中心\", \"orgName2\": \"总裁办\", \"orgName3\": \"\", \"orgName4\": \"\" }, \"position\": { \"id\": \"gyVAebeVQ2KxSmS4k0o5yXSuYS4=\", \"name\": \"副总裁\", \"number\": \"ZW0000155\" } } ] } ], \"name\": \"决策管理\", \"number\": \"01\" } ], \"name\": \"决策层\", \"number\": \"01\" } ], \"id\": \"rdEAAAAJVw5kHJrh\", \"name\": \"管理\", \"number\": \"02\" } ], \"name\": \"管理类\", \"number\": \"02\" }, { \"id\": \"rdEAAAAKEvb2V1KN\", \"jobSequenceList\": [ { \"childSequenceList\": [ { \"id\": \"rdEAAAAKE+ejdRE7\", \"jobFamilyList\": [ { \"id\": \"rdEAAAAKFZKp9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAKF/24hF0d\", \"jobId\": \"rdEAAAAKF/x1cCSY\", \"name\": \"司机岗\", \"number\": \"146\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKGAa4hF0d\", \"jobId\": \"rdEAAAAKGAV1cCSY\", \"name\": \"物业服务岗\", \"number\": \"149\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKGAC4hF0d\", \"jobId\": \"rdEAAAAKF/91cCSY\", \"name\": \"厨师岗\", \"number\": \"147\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKGAO4hF0d\", \"jobId\": \"rdEAAAAKGAJ1cCSY\", \"name\": \"电工岗\", \"number\": \"148\", \"positionInfoList\": [] } ], \"name\": \"后勤服务\", \"number\": \"56\" } ], \"name\": \"后勤服务\", \"number\": \"29\" }, { \"id\": \"rdEAAAAKE+GjdRE7\", \"jobFamilyList\": [ { \"id\": \"rdEAAAAKFX2p9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAKF6+4hF0d\", \"jobId\": \"rdEAAAAKF651cCSY\", \"name\": \"备料员岗\", \"number\": \"120\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKF6y4hF0d\", \"jobId\": \"rdEAAAAKF6t1cCSY\", \"name\": \"物料员岗\", \"number\": \"119\", \"positionInfoList\": [] } ], \"name\": \"物料\", \"number\": \"49\" }, { \"id\": \"rdEAAAAKFXqp9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAKF6a4hF0d\", \"jobId\": \"rdEAAAAKF6V1cCSY\", \"name\": \"仓库管理员岗\", \"number\": \"117\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKF6m4hF0d\", \"jobId\": \"rdEAAAAKF6h1cCSY\", \"name\": \"账务员岗\", \"number\": \"118\", \"positionInfoList\": [] } ], \"name\": \"仓库\", \"number\": \"48\" }, { \"id\": \"rdEAAAAKFYap9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAKF9+4hF0d\", \"jobId\": \"rdEAAAAKF951cCSY\", \"name\": \"班组长岗\", \"number\": \"136\", \"positionInfoList\": [] } ], \"name\": \"现场管理\", \"number\": \"52\" }, { \"id\": \"rdEAAAAKFYCp9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAKF764hF0d\", \"jobId\": \"rdEAAAAKF711cCSY\", \"name\": \"OQC检验员岗\", \"number\": \"125\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKF7i4hF0d\", \"jobId\": \"rdEAAAAKF7d1cCSY\", \"name\": \"IPQC检验员岗\", \"number\": \"123\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKF7K4hF0d\", \"jobId\": \"rdEAAAAKF7F1cCSY\", \"name\": \"IQC检验员岗\", \"number\": \"121\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKF7u4hF0d\", \"jobId\": \"rdEAAAAKF7p1cCSY\", \"name\": \"QA检验员岗\", \"number\": \"124\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKF7W4hF0d\", \"jobId\": \"rdEAAAAKF7R1cCSY\", \"name\": \"QC检验员岗\", \"number\": \"122\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKF8G4hF0d\", \"jobId\": \"rdEAAAAKF8B1cCSY\", \"name\": \"RMA检验员岗\", \"number\": \"126\", \"positionInfoList\": [] } ], \"name\": \"检验\", \"number\": \"50\" }, { \"id\": \"rdEAAAAKFYmp9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAKF+i4hF0d\", \"jobId\": \"rdEAAAAKF+d1cCSY\", \"name\": \"焊锡员岗\", \"number\": \"139\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKF+K4hF0d\", \"jobId\": \"rdEAAAAKF+F1cCSY\", \"name\": \"全能员岗\", \"number\": \"137\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKF+W4hF0d\", \"jobId\": \"rdEAAAAKF+R1cCSY\", \"name\": \"作业员岗\", \"number\": \"138\", \"positionInfoList\": [] } ], \"name\": \"生产作业\", \"number\": \"53\" }, { \"id\": \"rdEAAAAKFYOp9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAKF824hF0d\", \"jobId\": \"rdEAAAAKF8x1cCSY\", \"name\": \"维修工程技术员岗\", \"number\": \"130\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKF8e4hF0d\", \"jobId\": \"rdEAAAAKF8Z1cCSY\", \"name\": \"ME工程技术员岗\", \"number\": \"128\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKF8q4hF0d\", \"jobId\": \"rdEAAAAKF8l1cCSY\", \"name\": \"TE工程技术员岗\", \"number\": \"129\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKF8S4hF0d\", \"jobId\": \"rdEAAAAKF8N1cCSY\", \"name\": \"IE工程技术员岗\", \"number\": \"127\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKF9a4hF0d\", \"jobId\": \"rdEAAAAKF9V1cCSY\", \"name\": \"SMT技术员岗\", \"number\": \"133\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKF9C4hF0d\", \"jobId\": \"rdEAAAAKF891cCSY\", \"name\": \"大储能工程技术员岗\", \"number\": \"131\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKF9m4hF0d\", \"jobId\": \"rdEAAAAKF9h1cCSY\", \"name\": \"MES技术员岗\", \"number\": \"134\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKF9O4hF0d\", \"jobId\": \"rdEAAAAKF9J1cCSY\", \"name\": \"大储能装配技术员岗\", \"number\": \"132\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKF9y4hF0d\", \"jobId\": \"rdEAAAAKF9t1cCSY\", \"name\": \"大储能集装箱机柜装配工岗\", \"number\": \"135\", \"positionInfoList\": [] } ], \"name\": \"工程技术\", \"number\": \"51\" }, { \"id\": \"rdEAAAAKFYyp9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAKF/e4hF0d\", \"jobId\": \"rdEAAAAKF/Z1cCSY\", \"name\": \"大储能生产测试员岗\", \"number\": \"144\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKF/G4hF0d\", \"jobId\": \"rdEAAAAKF/B1cCSY\", \"name\": \"耐压测试员岗\", \"number\": \"142\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKF/S4hF0d\", \"jobId\": \"rdEAAAAKF/N1cCSY\", \"name\": \"老化测试员岗\", \"number\": \"143\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKF+64hF0d\", \"jobId\": \"rdEAAAAKF+11cCSY\", \"name\": \"T2测试员岗\", \"number\": \"141\", \"positionInfoList\": [] }, { \"id\": \"rdEAAAAKF+u4hF0d\", \"jobId\": \"rdEAAAAKF+p1cCSY\", \"name\": \"T1测试员岗\", \"number\": \"140\", \"positionInfoList\": [] } ], \"name\": \"测试作业\", \"number\": \"54\" } ], \"name\": \"生产操作\", \"number\": \"27\" }, { \"id\": \"rdEAAAAKE+SjdRE7\", \"jobFamilyList\": [ { \"id\": \"rdEAAAAKFY+p9YYB\", \"jobBasicList\": [ { \"id\": \"rdEAAAAKF/q4hF0d\", \"jobId\": \"rdEAAAAKF/l1cCSY\", \"name\": \"文员岗\", \"number\": \"145\", \"positionInfoList\": [] } ], \"name\": \"生产辅助\", \"number\": \"55\" } ], \"name\": \"生产辅助\", \"number\": \"28\" } ], \"id\": \"rdEAAAAJ7whkHJrh\", \"name\": \"操作/辅助\", \"number\": \"06\" } ], \"name\": \"操作/辅助类\", \"number\": \"03\" } ] }";

            //  PositionReportDto positionReportDto = wrapDto();
            PositionReportDto positionReportDto = JSON.parseObject(str, PositionReportDto.class);
            List<PositionReportDto.JobCategory> jobCategoryList = positionReportDto.getJobCategoryList();
            if (StringUtils.isNotBlank(deptId)) {
                washCategoryByOrgId(jobCategoryList, deptId);
            }
            wrapSheetData(jobCategoryList, rowNum, sheet);

            for (Row row : sheet) {
                for (int i = 0; i < secondHeaderVal.length; i++) {
                    Cell cell = row.getCell(i);
                    if (cell == null || StringUtils.isBlank(cell.getStringCellValue())) {
                        cell = row.createCell(i);
                        cell.setCellValue("");
                    }
                }
            }

            for (Row row : sheet) {
                for (Cell cell : row) {
                    CellStyle cellStyle = workbook.createCellStyle();
                    cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
                    cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
                    cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
                    cellStyle.setBorderTop(CellStyle.BORDER_THIN);
                    cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
                    cellStyle.setBorderRight(CellStyle.BORDER_THIN);
                    cell.setCellStyle(cellStyle);
                }
            }


            // 写入文件
            FileOutputStream fileOut = new FileOutputStream("基准岗位列表.xlsx");
            workbook.write(fileOut);
            fileOut.close();
            // 关闭工作簿
            workbook.close();
            System.out.println("Excel 文件已创建成功！");
            System.out.println(JSON.toJSONString(positionReportDto));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }


    private static void wrapSheetData(List<PositionReportDto.JobCategory> jobCategoryList, int rowNum, Sheet sheet) {
        if (CollectionUtils.isEmpty(jobCategoryList)) {
            return;
        }
        AtomicInteger rowNums = new AtomicInteger(rowNum);
        int index = 0;
        for (PositionReportDto.JobCategory jobCategory : jobCategoryList) {
            if (index > 0) {
                int rows = sheet.getLastRowNum() + 1;
                Row row = sheet.createRow(rows);
                Cell cell = row.createCell(0);
                cell.setCellValue(jobCategory.getName());
            } else {
                Row row = sheet.createRow(rowNums.addAndGet(1));
                Cell cell = row.createCell(0);
                cell.setCellValue(jobCategory.getName());
            }
            jobSequenceProcess(jobCategory, sheet, rowNums);
            index++;
        }
    }


    private static void jobSequenceProcess(PositionReportDto.JobCategory jobCategory, Sheet sheet, AtomicInteger rowNums) {
        List<PositionReportDto.JobSequence> jobSequenceList = jobCategory.getJobSequenceList();
        if (CollectionUtils.isEmpty(jobSequenceList)) {
            jobSequenceList = new ArrayList<>();
        }
        int jobSequenceIndex = 0;
        int firstRow = 0;
        int lastRow;
        int firstCol;
        int lastCol;
        for (PositionReportDto.JobSequence jobSequence : jobSequenceList) {
            boolean isLast = (jobSequenceIndex == jobSequenceList.size() - 1);
            if (jobSequenceIndex > 0) {
                int jobSequencesNum = sheet.getLastRowNum() + 1;
                Row row = sheet.createRow(jobSequencesNum);
                Cell cellJobSequence = row.createCell(1);
                cellJobSequence.setCellValue(jobSequence.getName());

            } else {
                Row row = sheet.getRow(sheet.getLastRowNum());
                Cell cellJobSequence = row.createCell(1);
                cellJobSequence.setCellValue(jobSequence.getName());
                firstRow = cellJobSequence.getRowIndex();
            }
            childSequenceProcess(jobSequence, sheet, rowNums);
            if (isLast) {
                firstCol = 0;
                lastRow = sheet.getLastRowNum();
                lastCol = 0;
                sheet.addMergedRegion(applyCellStyleToRegion(sheet, firstRow, lastRow, firstCol, lastCol));
            }
            jobSequenceIndex++;
        }
    }

    private static void childSequenceProcess(PositionReportDto.JobSequence jobSequence, Sheet sheet, AtomicInteger rowNums) {
        List<PositionReportDto.ChildSequence> childSequenceList = jobSequence.getChildSequenceList();
        if (CollectionUtils.isEmpty(childSequenceList)) {
            childSequenceList = new ArrayList<>();
        }
        int childSequenceIndex = 0;
        int firstRow = 0;
        int lastRow;
        int firstCol;
        int lastCol;
        for (PositionReportDto.ChildSequence childSequence : childSequenceList) {
            boolean isLast = (childSequenceIndex == childSequenceList.size() - 1);
            if (childSequenceIndex > 0) {
                int childSequenceNum = sheet.getLastRowNum() + 1;
                Row rowChildSequence = sheet.createRow(childSequenceNum);
                Cell cell = rowChildSequence.createCell(2);
                cell.setCellValue(childSequence.getName());
            } else {
                Row row = sheet.getRow(sheet.getLastRowNum());
                Cell cell = row.createCell(2);
                cell.setCellValue(childSequence.getName());
                firstRow = cell.getRowIndex();
            }
            jobFamilyProcess(childSequence, sheet, rowNums);
            if (isLast) {
                firstCol = 1;
                lastRow = sheet.getLastRowNum();
                lastCol = 1;
                sheet.addMergedRegion(applyCellStyleToRegion(sheet, firstRow, lastRow, firstCol, lastCol));
            }
            childSequenceIndex++;
        }
    }

    private static void jobFamilyProcess(PositionReportDto.ChildSequence childSequence, Sheet sheet, AtomicInteger rowNums) {
        List<PositionReportDto.JobFamily> jobFamilyList = childSequence.getJobFamilyList();
        if (CollectionUtils.isEmpty(jobFamilyList)) {
            jobFamilyList = new ArrayList<>();
        }
        int familyIndex = 0;
        int firstRow = 0;
        int lastRow;
        int firstCol;
        int lastCol;
        for (PositionReportDto.JobFamily jobFamily : jobFamilyList) {
            boolean isLast = (familyIndex == jobFamilyList.size() - 1);
            if (familyIndex > 0) {
                int rowFamilyNum = sheet.getLastRowNum() + 1;
                Row rowFamily = sheet.createRow(rowFamilyNum);
                Cell cellJobFamily = rowFamily.createCell(3);
                cellJobFamily.setCellValue(jobFamily.getName());
            } else {
                Row row = sheet.getRow(sheet.getLastRowNum());
                Cell cellJobFamily = row.createCell(3);
                cellJobFamily.setCellValue(jobFamily.getName());
                firstRow = cellJobFamily.getRowIndex();
            }
            jobBasicProcess(jobFamily, sheet, rowNums);
            if (isLast) {
                firstCol = 2;
                lastRow = sheet.getLastRowNum();
                lastCol = 2;
                sheet.addMergedRegion(applyCellStyleToRegion(sheet, firstRow, lastRow, firstCol, lastCol));
            }
            familyIndex++;
        }
    }


    private static void jobBasicProcess(PositionReportDto.JobFamily jobFamily, Sheet sheet,
                                        AtomicInteger rowNums) {
        List<PositionReportDto.JobBasic> jobBasicList = jobFamily.getJobBasicList();
        if (CollectionUtils.isEmpty(jobBasicList)) {
            jobBasicList = new ArrayList<>();
        }
        int basicIndex = 0;
        int firstRow = 0;
        int lastRow;
        int firstCol;
        int lastCol;
        for (PositionReportDto.JobBasic jobBasic : jobBasicList) {
            boolean isLast = (basicIndex == jobBasicList.size() - 1);
            Row row = sheet.getRow(sheet.getLastRowNum());
            if (basicIndex > 0) {
                int rowBasicNum = sheet.getLastRowNum() + 1;
                Row rowBasic = sheet.createRow(rowBasicNum);
                Cell cellJobBasic = rowBasic.createCell(4);
                cellJobBasic.setCellValue(jobBasic.getName());
                rowNums.set(rowBasicNum);
                positionInfoProcess(jobBasic, sheet, rowBasic, rowNums);
            } else {
                Cell cellJobBasic = row.createCell(4);
                cellJobBasic.setCellValue(jobBasic.getName());
                firstRow = cellJobBasic.getRowIndex();
                positionInfoProcess(jobBasic, sheet, row, rowNums);
            }

            if (isLast) {
                firstCol = 3;
                lastRow = sheet.getLastRowNum();
                lastCol = 3;
                sheet.addMergedRegion(applyCellStyleToRegion(sheet, firstRow, lastRow, firstCol, lastCol));
            }
            basicIndex++;
        }

    }


    private static void positionInfoProcess(PositionReportDto.JobBasic jobBasic, Sheet sheet, Row row, AtomicInteger rowNums) {
        List<PositionReportDto.PositionInfo> positionInfoList = jobBasic.getPositionInfoList();
        if (CollectionUtils.isEmpty(positionInfoList)) {
            positionInfoList = new ArrayList<>();
        }
        int cellNumJobPos = 5;
        int positionIndex = 0;
        int firstRow = 0;
        int lastRow = 0;
        int firstCol = 0;
        int lastCol = 0;
        for (PositionReportDto.PositionInfo positionInfo : positionInfoList) {

            boolean isLastPosition = (positionIndex == positionInfoList.size() - 1);
            int num = rowNums.addAndGet(1);
            if (positionIndex > 0) {
                Row rowJobBasic = sheet.createRow(num);
                Cell cellPositionLast = positionInfoCellProcess(positionInfo,
                        rowJobBasic, cellNumJobPos);
                if (isLastPosition) {
                    lastRow = cellPositionLast.getRowIndex();
                    lastCol = cellPositionLast.getColumnIndex() - 5;
                    rowNums.set(lastRow);
                }
            } else {
                Cell cellPositionFirst = positionInfoCellProcess(positionInfo, row, cellNumJobPos);
                firstRow = cellPositionFirst.getRowIndex();
                firstCol = cellPositionFirst.getColumnIndex() - 5;
            }

            if (isLastPosition) {
                if (lastRow < firstRow || lastCol < firstCol) {
                    continue;
                }
                // 合并父级单元格
                sheet.addMergedRegion(applyCellStyleToRegion(sheet, firstRow, lastRow, firstCol, lastCol));
            }
            positionIndex++;
        }
    }

    public static CellRangeAddress applyCellStyleToRegion(Sheet sheet, int firstRow, int lastRow, int firstCol, int lastCol) {
        // 获取合并区域的第一个单元格
        Row firstRowObj = sheet.getRow(firstRow);
        if (firstRowObj == null) {
            firstRowObj = sheet.createRow(firstRow);
        }
        Cell firstCell = firstRowObj.getCell(firstCol);
        if (firstCell == null) {
            firstCell = firstRowObj.createCell(firstCol);
        }
        // 获取第一个单元格的样式
        CellStyle mergedCellStyle = firstCell.getCellStyle();
        // 应用样式到指定区域的所有单元格
        for (int rowIndex = firstRow; rowIndex <= lastRow; rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            if (row == null) {
                row = sheet.createRow(rowIndex);
            }
            for (int colIndex = firstCol; colIndex <= lastCol; colIndex++) {
                Cell cell = row.getCell(colIndex);
                if (cell == null) {
                    cell = row.createCell(colIndex);
                }
                cell.setCellStyle(mergedCellStyle);
            }
        }
        // 返回 CellRangeAddress 对象
        return new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);
    }

    public static void setCellStyleForRegion(Sheet sheet, int firstRow, int lastRow, int firstCol, int lastCol) {
        // 获取第一个单元格的样式
        Row firstRowObj = sheet.getRow(firstRow);
        Cell firstCell = firstRowObj.getCell(firstCol);
        CellStyle firstCellStyle = firstCell.getCellStyle();

        // 应用样式到指定区域的所有单元格
        for (int rowIndex = firstRow; rowIndex <= lastRow; rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            if (row == null) {
                row = sheet.createRow(rowIndex);
            }
            for (int colIndex = firstCol; colIndex <= lastCol; colIndex++) {
                Cell cell = row.getCell(colIndex);
                if (cell == null) {
                    cell = row.createCell(colIndex);
                }
                cell.setCellStyle(firstCellStyle);
            }
        }
    }


    private static Cell positionInfoCellProcess(PositionReportDto.PositionInfo positionInfo,
                                                Row row, int cellNum) {
        if (Objects.isNull(positionInfo)) {
            positionInfo = new PositionReportDto.PositionInfo();
        }
        PositionReportDto.JobLevel jobLevel = positionInfo.getJobLevel();
        PositionReportDto.JobTitle jobTitle = positionInfo.getJobTitle();
        PositionReportDto.Position position = positionInfo.getPosition();
        PositionReportDto.Org org = positionInfo.getOrg();
        if (Objects.isNull(jobLevel)) {
            jobLevel = new PositionReportDto.JobLevel();
        }
        if (Objects.isNull(jobTitle)) {
            jobTitle = new PositionReportDto.JobTitle();
        }
        if (Objects.isNull(position)) {
            position = new PositionReportDto.Position();
        }
        if (Objects.isNull(org)) {
            org = new PositionReportDto.Org();
        }


//        Cell cellJobLevel = row.createCell(cellNum);
//        cellJobLevel.setCellValue(jobLevel.getName());
//        cellNum++;
//
//        Cell cellJobTitle = row.createCell(cellNum);
//        cellJobTitle.setCellValue(jobTitle.getName());
//        cellNum++;

//        Cell cellJobGrade = row.createCell(cellNum);
//        cellJobGrade.setCellValue(positionInfo.getJobGrade().getName());
//        cellNum++;

        Cell cellPosition = row.createCell(cellNum);
        cellPosition.setCellValue(position.getName());
        cellNum++;

//        Cell cellOrg = row.createCell(cellNum);
////        cellOrg.setCellValue(org.getName());

        Cell cellOrg1 = row.createCell(cellNum);
        cellOrg1.setCellValue(org.getOrgName1());
        cellNum++;

        Cell cellOrg2 = row.createCell(cellNum);
        cellOrg2.setCellValue(org.getOrgName2());
        cellNum++;

        Cell cellOrg3 = row.createCell(cellNum);
        cellOrg3.setCellValue(org.getOrgName3());
        cellNum++;

        Cell cellOrg4 = row.createCell(cellNum);
        cellOrg4.setCellValue(org.getOrgName4());

        return cellOrg4;
    }


    /**
     * 设置单元格样式
     *
     * @param cell
     * @param workbook
     */
    private static void wrapCellStyle(Cell cell, Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        cell.setCellStyle(cellStyle);
    }


    private static PositionReportDto wrapDto() {
        PositionReportDto positionReportDto = new PositionReportDto();

        List<PositionReportDto.JobCategory> jobCategoryList = new ArrayList<>();
        PositionReportDto.JobCategory jobCategory = new PositionReportDto.JobCategory();
        jobCategory.setName("专业类");
        jobCategoryList.add(jobCategory);

        List<PositionReportDto.JobSequence> jobSequenceList = new ArrayList<>();
        PositionReportDto.JobSequence jobSequence1 = new PositionReportDto.JobSequence();
        jobSequence1.setName("市场营销");
        jobSequenceList.add(jobSequence1);
        PositionReportDto.JobSequence jobSequence2 = new PositionReportDto.JobSequence();
        jobSequence2.setName("产品研发与技术");
        jobSequenceList.add(jobSequence2);
        jobCategory.setJobSequenceList(jobSequenceList);

        List<PositionReportDto.ChildSequence> childSequenceList = new ArrayList<>();
        PositionReportDto.ChildSequence childSequence1 = new PositionReportDto.ChildSequence();
        childSequence1.setName("市场运营");
        childSequenceList.add(childSequence1);
        PositionReportDto.ChildSequence childSequence2 = new PositionReportDto.ChildSequence();
        childSequence2.setName("品牌管理");
        childSequenceList.add(childSequence2);
        jobSequence1.setChildSequenceList(childSequenceList);

        List<PositionReportDto.ChildSequence> childSequenceList2 = new ArrayList<>();
        PositionReportDto.ChildSequence childSequence3 = new PositionReportDto.ChildSequence();
        childSequence3.setName("产品管理");
        childSequenceList2.add(childSequence3);
        PositionReportDto.ChildSequence childSequence4 = new PositionReportDto.ChildSequence();
        childSequence4.setName("售前技术");
        childSequenceList2.add(childSequence4);
        jobSequence2.setChildSequenceList(childSequenceList2);


        List<PositionReportDto.JobFamily> jobFamilyList = new ArrayList<>();
        PositionReportDto.JobFamily jobFamily1 = new PositionReportDto.JobFamily();
        jobFamily1.setName("市场运营");
        jobFamilyList.add(jobFamily1);
        childSequence1.setJobFamilyList(jobFamilyList);

        List<PositionReportDto.JobFamily> jobFamilyList2 = new ArrayList<>();
        PositionReportDto.JobFamily jobFamily2 = new PositionReportDto.JobFamily();
        jobFamily2.setName("品牌管理");
        jobFamilyList2.add(jobFamily2);
        childSequence2.setJobFamilyList(jobFamilyList2);


        List<PositionReportDto.JobFamily> jobFamilyList3 = new ArrayList<>();
        PositionReportDto.JobFamily jobFamily3 = new PositionReportDto.JobFamily();
        jobFamily3.setName("产品管理");
        jobFamilyList3.add(jobFamily3);
        childSequence3.setJobFamilyList(jobFamilyList3);

        List<PositionReportDto.JobFamily> jobFamilyList4 = new ArrayList<>();
        PositionReportDto.JobFamily jobFamily4 = new PositionReportDto.JobFamily();
        jobFamily4.setName("售前技术");
        jobFamilyList4.add(jobFamily4);
        childSequence4.setJobFamilyList(jobFamilyList4);


        List<PositionReportDto.JobBasic> jobBasicList = new ArrayList<>();
        PositionReportDto.JobBasic jobBasic = new PositionReportDto.JobBasic();
        jobBasic.setName("市场推广岗");

        PositionReportDto.PositionInfo positionInfo = new PositionReportDto.PositionInfo();
        PositionReportDto.JobTitle jobTitle = new PositionReportDto.JobTitle();
        jobTitle.setName("专员");
        PositionReportDto.JobLevel jobLevel = new PositionReportDto.JobLevel();
        jobLevel.setName("P1");
        PositionReportDto.JobGrade jobGrade = new PositionReportDto.JobGrade();
        jobGrade.setName("P1-1");
        PositionReportDto.Position position = new PositionReportDto.Position();
        position.setName("市场推广专员");
        PositionReportDto.Org org = new PositionReportDto.Org();
        org.setName("应用交付组");
        positionInfo.setJobTitle(jobTitle);
        positionInfo.setJobLevel(jobLevel);
        positionInfo.setJobGrade(jobGrade);
        positionInfo.setPosition(position);
        positionInfo.setOrg(org);

        PositionReportDto.PositionInfo positionInfo4 = new PositionReportDto.PositionInfo();
        PositionReportDto.JobTitle jobTitle4 = new PositionReportDto.JobTitle();
        jobTitle4.setName("专员4");
        PositionReportDto.JobLevel jobLevel4 = new PositionReportDto.JobLevel();
        jobLevel4.setName("P1");
        PositionReportDto.JobGrade jobGrade4 = new PositionReportDto.JobGrade();
        jobGrade4.setName("P1-1");
        PositionReportDto.Position position4 = new PositionReportDto.Position();
        position4.setName("市场推广专员4");
        PositionReportDto.Org org4 = new PositionReportDto.Org();
        org4.setName("应用交付组");
        positionInfo4.setJobTitle(jobTitle4);
        positionInfo4.setJobLevel(jobLevel4);
        positionInfo4.setJobGrade(jobGrade4);
        positionInfo4.setPosition(position4);
        positionInfo4.setOrg(org4);

        List<PositionReportDto.PositionInfo> positionInfoList = new ArrayList<>();
        positionInfoList.add(positionInfo);
        positionInfoList.add(positionInfo4);
        jobBasic.setPositionInfoList(positionInfoList);
        jobBasicList.add(jobBasic);

        //=========

        PositionReportDto.JobBasic jobBasic3 = new PositionReportDto.JobBasic();
        jobBasic3.setName("市场推广岗3");

        PositionReportDto.PositionInfo positionInfo3 = new PositionReportDto.PositionInfo();
        PositionReportDto.JobTitle jobTitle3 = new PositionReportDto.JobTitle();
        jobTitle3.setName("专员3");
        PositionReportDto.JobLevel jobLevel3 = new PositionReportDto.JobLevel();
        jobLevel3.setName("P1");
        PositionReportDto.JobGrade jobGrade3 = new PositionReportDto.JobGrade();
        jobGrade3.setName("P1-1");
        PositionReportDto.Position position3 = new PositionReportDto.Position();
        position3.setName("市场推广专员3");
        PositionReportDto.Org org3 = new PositionReportDto.Org();
        org3.setName("应用交付组");
        positionInfo3.setJobTitle(jobTitle3);
        positionInfo3.setJobLevel(jobLevel3);
        positionInfo3.setJobGrade(jobGrade3);
        positionInfo3.setPosition(position3);
        positionInfo3.setOrg(org3);

        List<PositionReportDto.PositionInfo> positionInfoList3 = new ArrayList<>();
        positionInfoList3.add(positionInfo3);
        jobBasic3.setPositionInfoList(positionInfoList3);
        jobBasicList.add(jobBasic3);
        jobFamily1.setJobBasicList(jobBasicList);


        //=========
        List<PositionReportDto.JobBasic> jobBasicList1 = new ArrayList<>();
        PositionReportDto.JobBasic jobBasic1 = new PositionReportDto.JobBasic();
        jobBasic1.setName("市场研究岗1");

        PositionReportDto.PositionInfo positionInfo1 = new PositionReportDto.PositionInfo();
        PositionReportDto.JobTitle jobTitle1 = new PositionReportDto.JobTitle();
        jobTitle1.setName("专员1");
        PositionReportDto.JobLevel jobLevel1 = new PositionReportDto.JobLevel();
        jobLevel1.setName("P1");
        PositionReportDto.JobGrade jobGrade1 = new PositionReportDto.JobGrade();
        jobGrade1.setName("P1-1");
        PositionReportDto.Position position1 = new PositionReportDto.Position();
        position1.setName("市场推广专员");
        PositionReportDto.Org org1 = new PositionReportDto.Org();
        org1.setName("应用交付组");

        positionInfo1.setJobTitle(jobTitle1);
        positionInfo1.setJobLevel(jobLevel1);
        positionInfo1.setJobGrade(jobGrade1);
        positionInfo1.setPosition(position1);
        positionInfo1.setOrg(org1);

        List<PositionReportDto.PositionInfo> positionInfoList1 = new ArrayList<>();
        positionInfoList3.add(positionInfo1);
        jobBasic1.setPositionInfoList(positionInfoList1);
        jobBasicList1.add(jobBasic1);
        jobFamily2.setJobBasicList(jobBasicList1);

        //=========
        List<PositionReportDto.JobBasic> jobBasicList2 = new ArrayList<>();
        PositionReportDto.JobBasic jobBasic2 = new PositionReportDto.JobBasic();
        jobBasic2.setName("市场研究岗2");


        PositionReportDto.PositionInfo positionInfo2 = new PositionReportDto.PositionInfo();
        PositionReportDto.JobTitle jobTitle2 = new PositionReportDto.JobTitle();
        jobTitle2.setName("专2");
        PositionReportDto.JobLevel jobLevel2 = new PositionReportDto.JobLevel();
        jobLevel2.setName("P1");
        PositionReportDto.JobGrade jobGrade2 = new PositionReportDto.JobGrade();
        jobGrade2.setName("P1-1");
        PositionReportDto.Position position2 = new PositionReportDto.Position();
        position2.setName("市场推广专员");
        PositionReportDto.Org org2 = new PositionReportDto.Org();
        org2.setName("应用交付组");

        positionInfo2.setJobTitle(jobTitle2);
        positionInfo2.setJobLevel(jobLevel2);
        positionInfo2.setJobGrade(jobGrade2);
        positionInfo2.setPosition(position2);
        positionInfo2.setOrg(org2);

        List<PositionReportDto.PositionInfo> positionInfoList2 = new ArrayList<>();
        positionInfoList2.add(positionInfo2);
        jobBasic2.setPositionInfoList(positionInfoList2);
        jobBasicList2.add(jobBasic2);
        jobFamily3.setJobBasicList(jobBasicList2);


        positionReportDto.setJobCategoryList(jobCategoryList);
        return positionReportDto;
    }


    public static void washCategoryByOrgId(List<PositionReportDto.JobCategory> jobCategories, String orgId) {
        if (CollectionUtils.isEmpty(jobCategories)) {
            return;
        }
        List<PositionReportDto.JobCategory> removeIndexList = new ArrayList<>();
        for (PositionReportDto.JobCategory jobCategory : jobCategories) {
            List<PositionReportDto.JobSequence> jobSequenceList = jobCategory.getJobSequenceList();
            boolean isRemove = washJobSequenceByOrgId(jobSequenceList, orgId);
            if (isRemove) {
                removeIndexList.add(jobCategory);
            }
        }

        if (CollectionUtils.isNotEmpty(removeIndexList)) {
            for (PositionReportDto.JobCategory jobCategory : removeIndexList) {
                jobCategories.remove(jobCategory);
            }
        }
    }


    public static boolean washJobSequenceByOrgId(List<PositionReportDto.JobSequence> jobSequenceList, String orgId) {
        if (CollectionUtils.isEmpty(jobSequenceList)) {
            return true;
        }
        int size = jobSequenceList.size();

        List<PositionReportDto.JobSequence> removeIndexList = new ArrayList<>();
        for (PositionReportDto.JobSequence jobSequence : jobSequenceList) {
            List<PositionReportDto.ChildSequence> childSequenceList = jobSequence.getChildSequenceList();
            boolean isRemove = washChildSequenceByOrgId(childSequenceList, orgId);
            if (isRemove) {
                removeIndexList.add(jobSequence);
            }
        }

        if (CollectionUtils.isNotEmpty(removeIndexList)) {
            for (PositionReportDto.JobSequence jobSequence : removeIndexList) {
                jobSequenceList.remove(jobSequence);
            }
        }
        return removeIndexList.size() == size;
    }


    public static boolean washChildSequenceByOrgId(List<PositionReportDto.ChildSequence> childSequenceList, String orgId) {
        if (CollectionUtils.isEmpty(childSequenceList)) {
            return true;
        }
        int size = childSequenceList.size();

        List<PositionReportDto.ChildSequence> removeIndexList = new ArrayList<>();
        for (PositionReportDto.ChildSequence childSequence : childSequenceList) {
            List<PositionReportDto.JobFamily> jobFamilyList = childSequence.getJobFamilyList();
            boolean isRemove = washJobFamilyByOrgId(jobFamilyList, orgId);
            if (isRemove) {
                removeIndexList.add(childSequence);
            }
        }

        if (CollectionUtils.isNotEmpty(removeIndexList)) {
            for (PositionReportDto.ChildSequence childSequence : removeIndexList) {
                childSequenceList.remove(childSequence);
            }
        }
        return removeIndexList.size() == size;
    }


    public static boolean washJobFamilyByOrgId(List<PositionReportDto.JobFamily> jobFamilyList, String orgId) {
        if (CollectionUtils.isEmpty(jobFamilyList)) {
            return true;
        }
        int size = jobFamilyList.size();

        List<PositionReportDto.JobFamily> removeIndexList = new ArrayList<>();
        for (PositionReportDto.JobFamily jobFamily : jobFamilyList) {
            List<PositionReportDto.JobBasic> jobBasicList = jobFamily.getJobBasicList();
            boolean isRemove = washJobBasicByOrgId(jobBasicList, orgId);
            if (isRemove) {
                removeIndexList.add(jobFamily);
            }
        }

        if (CollectionUtils.isNotEmpty(removeIndexList)) {
            for (PositionReportDto.JobFamily jobFamily : removeIndexList) {
                jobFamilyList.remove(jobFamily);
            }
        }
        return removeIndexList.size() == size;
    }

    public static boolean washJobBasicByOrgId(List<PositionReportDto.JobBasic> jobBasicList, String orgId) {
        if (CollectionUtils.isEmpty(jobBasicList)) {
            return true;
        }
        int size = jobBasicList.size();

        List<PositionReportDto.JobBasic> removeIndexList = new ArrayList<>();
        for (PositionReportDto.JobBasic jobBasic : jobBasicList) {
            List<PositionReportDto.PositionInfo> positionInfoList = jobBasic.getPositionInfoList();
            boolean isRemove = washPositionInfoByOrgId(positionInfoList, orgId);
            if (isRemove) {
                removeIndexList.add(jobBasic);
            }
        }

        if (CollectionUtils.isNotEmpty(removeIndexList)) {
            for (PositionReportDto.JobBasic jobBasic : removeIndexList) {
                jobBasicList.remove(jobBasic);
            }
        }

        return removeIndexList.size() == size;
    }


    public static boolean washPositionInfoByOrgId(List<PositionReportDto.PositionInfo> positionInfoList, String orgId) {
        if (CollectionUtils.isEmpty(positionInfoList)) {
            return true;
        }
        int size = positionInfoList.size();

        List<PositionReportDto.PositionInfo> removeIndexList = new ArrayList<>();
        for (PositionReportDto.PositionInfo positionInfo : positionInfoList) {
            PositionReportDto.Org org = positionInfo.getOrg();
            if (Objects.isNull(org)) {
                continue;
            }
            if (!orgId.equals(org.getId())) {
                removeIndexList.add(positionInfo);
            }
        }
        if (CollectionUtils.isNotEmpty(removeIndexList)) {
            for (PositionReportDto.PositionInfo positionInfo : removeIndexList) {
                positionInfoList.remove(positionInfo);
            }
        }
        return removeIndexList.size() == size;
    }
}
