import com.alibaba.fastjson.JSON;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;


public class ExcelUtil {
    public static void main(String[] args) {
        try {
            int rowNum = 0;
            // 创建工作簿
            Workbook workbook = new XSSFWorkbook();

            // 创建工作表
            Sheet sheet = workbook.createSheet("基准岗位列表");


            String[] secondHeaderVal = {"所属行政组织", "岗位类别", "岗位序列",
                    "子序列", "岗位族", "基准岗位", "职级", "职衔", "职等", "职位名称"};
            for (int i = 0; i < secondHeaderVal.length; i++) {
                // 设置默认单元格宽度
                sheet.setColumnWidth(i, 20 * 256);
            }

            // 创建第一行
            Row titleRow = sheet.createRow(rowNum);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("基准岗位列表");
            //wrapCellStyle(titleCell, workbook);

            //   firstRow: 第一个单元格的行号（从0开始）。
            //   lastRow: 最后一个单元格的行号（从0开始）。
            //   firstCol: 第一个单元格的列号（从0开始）。
            //   lastCol: 最后一个单元格的列号（从0开始）。
            // 合并第一行单元格
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, secondHeaderVal.length - 1));

            // 创建第二行
            Row headerRow = sheet.createRow(rowNum += 1);

            for (int i = 0; i < secondHeaderVal.length; i++) {
                Cell headerCell = headerRow.createCell(i);
                headerCell.setCellValue(secondHeaderVal[i]);
                // wrapCellStyle(headerCell, workbook);
            }

            PositionReportDto positionReportDto = wrapDto();

            wrapSheetData(positionReportDto.getOrgList(), rowNum, sheet, workbook);

            for (Row row : sheet) {
                for (Cell cell : row) {
                    CellStyle cellStyle = workbook.createCellStyle();
                    cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void wrapSheetData(List<PositionReportDto.Org> orgList, int rowNum, Sheet sheet, Workbook workbook) {
        int cellNum = 0;
        for (PositionReportDto.Org org : orgList) {
            Row row = sheet.createRow(rowNum += 1);
            Cell cell = row.createCell(cellNum);
            cell.setCellValue(org.getName());
            //wrapCellStyle(cell, workbook);
            List<PositionReportDto.JobCategory> jobCategoryList = org.getJobCategoryList();
            if (CollectionUtils.isNotEmpty(jobCategoryList)) {
                cellNum++;
            }

            for (PositionReportDto.JobCategory jobCategory : jobCategoryList) {
                Cell cellJobCategory = row.createCell(cellNum);
                cellJobCategory.setCellValue(jobCategory.getName());
                // wrapCellStyle(cellJobCategory, workbook);
                List<PositionReportDto.JobSequence> jobSequenceList = jobCategory.getJobSequenceList();
                if (CollectionUtils.isNotEmpty(jobSequenceList)) {
                    cellNum++;
                }

                for (PositionReportDto.JobSequence jobSequence : jobSequenceList) {
                    Cell cellJobSequence = row.createCell(cellNum);
                    cellJobSequence.setCellValue(jobSequence.getName());
                    // wrapCellStyle(cellJobSequence, workbook);

                    List<PositionReportDto.ChildSequence> childSequenceList = jobSequence.getChildSequenceList();
                    if (CollectionUtils.isNotEmpty(childSequenceList)) {
                        cellNum++;
                    }

                    for (PositionReportDto.ChildSequence childSequence : childSequenceList) {

                        Cell cellChildSequence = row.createCell(cellNum);
                        cellChildSequence.setCellValue(childSequence.getName());
                        // wrapCellStyle(cellChildSequence, workbook);

                        List<PositionReportDto.JobFamily> jobFamilyList = childSequence.getJobFamilyList();
                        if (CollectionUtils.isNotEmpty(jobFamilyList)) {
                            cellNum++;
                        }


                        for (PositionReportDto.JobFamily jobFamily : jobFamilyList) {
                            // Cell cellJobFamily = row.createCell(cellNum);
                            Cell cellJobFamily = sheet.getRow(sheet.getLastRowNum()).createCell(cellNum);
                            cellJobFamily.setCellValue(jobFamily.getName());
                            // wrapCellStyle(cellJobFamily, workbook);

                            List<PositionReportDto.JobBasic> jobBasicList = jobFamily.getJobBasicList();
                            if (CollectionUtils.isNotEmpty(jobBasicList)) {
                                cellNum++;
                            } else {
                                jobBasicList = new ArrayList<>();
                            }

                            int basicIndex = 0;
                            for (PositionReportDto.JobBasic jobBasic : jobBasicList) {
                                boolean isLastBasic = (basicIndex == jobBasicList.size() - 1);
                                //  wrapCellStyle(cellJobBasic, workbook);
                                if (basicIndex > 0) {
                                    int rowBasicNum = sheet.getLastRowNum() + 1;
                                    Row rowBasic = sheet.createRow(rowBasicNum);
                                    Cell cellJobBasic = rowBasic.createCell(cellNum);
                                    cellJobBasic.setCellValue(jobBasic.getName());
                                    positionInfoProcess(jobBasic, sheet, workbook, rowBasic, rowBasicNum);
                                } else {
                                    Cell cellJobBasic = sheet.getRow(sheet.getLastRowNum()).createCell(cellNum);
                                    cellJobBasic.setCellValue(jobBasic.getName());
                                    positionInfoProcess(jobBasic, sheet, workbook,
                                            sheet.getRow(sheet.getLastRowNum()), rowNum);
                                }
                                basicIndex++;
                            }
                        }
                    }
                }
            }
        }
    }


    private static void positionInfoProcess(PositionReportDto.JobBasic jobBasic, Sheet sheet, Workbook workbook, Row row,
                                            int rowNum) {
        List<PositionReportDto.PositionInfo> positionInfoList = jobBasic.getPositionInfoList();
        if (CollectionUtils.isEmpty(positionInfoList)) {
            positionInfoList = new ArrayList<>();
        }
        int cellNumJobPos = 6;
        int positionIndex = 0;
        int firstRow = 0;
        int lastRow = 0;
        int firstCol = 0;
        int lastCol = 0;
        for (PositionReportDto.PositionInfo positionInfo : positionInfoList) {

            boolean isLastPosition = (positionIndex == positionInfoList.size() - 1);
            if (positionIndex > 0) {
                Row rowJobBasic = sheet.createRow(rowNum += 1);
                Cell cellPositionLast = positionInfoCellProcess(positionInfo,
                        rowJobBasic, cellNumJobPos, workbook);
                if (isLastPosition) {
                    lastRow = cellPositionLast.getRowIndex();
                    lastCol = cellPositionLast.getColumnIndex() - 4;
                }
            } else {
                Cell cellPositionFirst = positionInfoCellProcess(positionInfo, row, cellNumJobPos, workbook);
                firstRow = cellPositionFirst.getRowIndex();
                firstCol = cellPositionFirst.getColumnIndex() - 4;
            }

            if (isLastPosition) {
                System.out.println("firstRow-->" + firstRow);
                System.out.println("lastRow-->" + lastRow);
                System.out.println("firstCol-->" + firstCol);
                System.out.println("lastCol-->" + lastCol);
                System.out.println();
                if (lastRow < firstRow || lastCol < firstCol) {
                    continue;
                }
                // TODO
                if (lastRow == 4) {
                    continue;
                }
                // 合并父级单元格
                sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
            }
            positionIndex++;
        }

    }


    public static void mergeCellsIfNotMerged(Sheet sheet, int firstRow, int lastRow, int firstCol, int lastCol) {
        CellRangeAddress range = new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);

        boolean isMerged = false;
        // 检查是否已经存在合并的区域
        for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
            CellRangeAddress mergedRange = sheet.getMergedRegion(i);
            if (range.equals(mergedRange)) {
                isMerged = true;
                break;
            }
        }

        // 如果区域未被合并，则进行合并
        if (!isMerged) {
            sheet.addMergedRegion(range);
        }
    }

    public static void mergeCellsIfNotMerged(Sheet sheet, int firstRow, int lastRow, int firstCol, int lastCol, String value) {
        // 创建合并区域对象
        CellRangeAddress range = new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);

        boolean isMerged = false;
        // 检查是否已经存在合并的区域
        for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
            CellRangeAddress mergedRange = sheet.getMergedRegion(i);
            if (range.equals(mergedRange)) {
                isMerged = true;
                break;
            }
        }

        // 如果区域未被合并，则进行合并并设置值
        if (!isMerged) {
            sheet.getRow(firstRow).getCell(firstCol).setCellValue(value); // 设置第一个单元格的值
            sheet.addMergedRegion(range); // 合并区域
        }
    }


    private static Cell positionInfoCellProcess(PositionReportDto.PositionInfo positionInfo,
                                                Row row, int cellNum, Workbook workbook) {

        Cell cellJobLevel = row.createCell(cellNum);
        cellJobLevel.setCellValue(positionInfo.getJobLevel().getName());
        // wrapCellStyle(cellJobLevel, workbook);
        cellNum++;

        Cell cellJobTitle = row.createCell(cellNum);
        cellJobTitle.setCellValue(positionInfo.getJobTitle().getName());
        // wrapCellStyle(cellJobTitle, workbook);
        cellNum++;

        Cell cellJobGrade = row.createCell(cellNum);
        cellJobGrade.setCellValue(positionInfo.getJobGrade().getName());
        // wrapCellStyle(cellJobGrade, workbook);
        cellNum++;

        Cell cellPosition = row.createCell(cellNum);
        cellPosition.setCellValue(positionInfo.getPosition().getName());
        //wrapCellStyle(cellPosition, workbook);

        return cellPosition;
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
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        cell.setCellStyle(cellStyle);
    }


    private static PositionReportDto wrapDto() {
        PositionReportDto positionReportDto = new PositionReportDto();

        List<PositionReportDto.Org> orgList = new ArrayList<>();
        PositionReportDto.Org org = new PositionReportDto.Org();
        org.setName("应用交付组");
        orgList.add(org);

        List<PositionReportDto.JobCategory> jobCategoryList = new ArrayList<>();
        PositionReportDto.JobCategory jobCategory = new PositionReportDto.JobCategory();
        jobCategory.setName("专业类");
        jobCategoryList.add(jobCategory);
        org.setJobCategoryList(jobCategoryList);

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
        positionInfo.setJobTitle(jobTitle);
        positionInfo.setJobLevel(jobLevel);
        positionInfo.setJobGrade(jobGrade);
        positionInfo.setPosition(position);

        PositionReportDto.PositionInfo positionInfo4 = new PositionReportDto.PositionInfo();
        PositionReportDto.JobTitle jobTitle4 = new PositionReportDto.JobTitle();
        jobTitle4.setName("专员4");
        PositionReportDto.JobLevel jobLevel4 = new PositionReportDto.JobLevel();
        jobLevel4.setName("P1");
        PositionReportDto.JobGrade jobGrade4 = new PositionReportDto.JobGrade();
        jobGrade4.setName("P1-1");
        PositionReportDto.Position position4 = new PositionReportDto.Position();
        position4.setName("市场推广专员4");
        positionInfo4.setJobTitle(jobTitle4);
        positionInfo4.setJobLevel(jobLevel4);
        positionInfo4.setJobGrade(jobGrade4);
        positionInfo4.setPosition(position4);

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
        positionInfo3.setJobTitle(jobTitle3);
        positionInfo3.setJobLevel(jobLevel3);
        positionInfo3.setJobGrade(jobGrade3);
        positionInfo3.setPosition(position3);

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

        positionInfo1.setJobTitle(jobTitle1);
        positionInfo1.setJobLevel(jobLevel1);
        positionInfo1.setJobGrade(jobGrade1);
        positionInfo1.setPosition(position1);

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

        positionInfo2.setJobTitle(jobTitle2);
        positionInfo2.setJobLevel(jobLevel2);
        positionInfo2.setJobGrade(jobGrade2);
        positionInfo2.setPosition(position2);

        List<PositionReportDto.PositionInfo> positionInfoList2 = new ArrayList<>();
        positionInfoList2.add(positionInfo2);
        jobBasic2.setPositionInfoList(positionInfoList2);
        jobBasicList2.add(jobBasic2);
        jobFamily3.setJobBasicList(jobBasicList2);


        positionReportDto.setOrgList(orgList);
        return positionReportDto;
    }
}
