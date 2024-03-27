
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
                    "子序列", "岗位族", "基准岗位", "职级", "职衔", "职位名称", "一级组织", "二级组织", "三级组织"
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

            PositionReportDto positionReportDto = wrapDto();
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
            if (positionIndex > 0) {
                Row rowJobBasic = sheet.createRow(rowNums.addAndGet(1));
                Cell cellPositionLast = positionInfoCellProcess(positionInfo,
                        rowJobBasic, cellNumJobPos);
                if (isLastPosition) {
                    lastRow = cellPositionLast.getRowIndex();
                    lastCol = cellPositionLast.getColumnIndex() - 7;
                    rowNums.set(lastRow);
                }
            } else {
                Cell cellPositionFirst = positionInfoCellProcess(positionInfo, row, cellNumJobPos);
                firstRow = cellPositionFirst.getRowIndex();
                firstCol = cellPositionFirst.getColumnIndex() - 7;
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


        Cell cellJobLevel = row.createCell(cellNum);
        cellJobLevel.setCellValue(jobLevel.getName());
        cellNum++;

        Cell cellJobTitle = row.createCell(cellNum);
        cellJobTitle.setCellValue(jobTitle.getName());
        cellNum++;

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
