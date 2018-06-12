import S.singleResponsibility.DataRow;
import S.singleResponsibility.MultiPurposeModel;

import java.util.Collection;

public class SingleResponsibilityDemonstrator {

    public static void main(String[] args) {
        MultiPurposeModel multiPurposeModel = new MultiPurposeModel();

        multiPurposeModel.setModelStringAttribute("best string ever");
        multiPurposeModel.setModelId(1L);
        multiPurposeModel.save();

        multiPurposeModel.setModelId(2L);
        multiPurposeModel.save();

        multiPurposeModel.setModelId(3L);
        multiPurposeModel.save();

        multiPurposeModel.setModelId(4L);
        multiPurposeModel.save();

        Collection<DataRow> dataRows = multiPurposeModel.selectAll();
    }
}
