import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Data
public class PositionReportDto {

    private List<Org> orgList;


    @Data
    public static class Org {

        private String name = StringUtils.EMPTY;

        private List<JobCategory> jobCategoryList;

    }

    @Data
    public static class JobCategory {

        private String name = StringUtils.EMPTY;

        private List<JobSequence> jobSequenceList;

    }

    @Data
    public static class JobSequence {

        private String name = StringUtils.EMPTY;

        private List<ChildSequence> childSequenceList;


    }

    @Data
    public static class ChildSequence {

        private String name = StringUtils.EMPTY;

        private List<JobFamily> jobFamilyList;

    }

    @Data
    public static class JobFamily {

        private String name = StringUtils.EMPTY;

        private List<JobBasic> jobBasicList;

    }

    @Data
    public static class JobBasic {

        private String name = StringUtils.EMPTY;

        private List<PositionInfo> positionInfoList;


    }

    @Data
    // 职衔
    public static class JobTitle {
        private String name = StringUtils.EMPTY;

    }

    @Data
    // 职级
    public static class JobLevel {
        private String name = StringUtils.EMPTY;

    }

    @Data
    // 职等
    public static class JobGrade {
        private String name = StringUtils.EMPTY;


    }

    @Data
    public static class Position {
        private String name = StringUtils.EMPTY;


    }

    @Data
    public static class PositionInfo {
        private JobTitle jobTitle;

        private JobLevel jobLevel;

        private JobGrade jobGrade;

        private Position position;

    }

}
