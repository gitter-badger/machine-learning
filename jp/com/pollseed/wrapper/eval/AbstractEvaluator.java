package jp.com.machine.test.eval;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.model.DataModel;

abstract class AbstractEvaluator {

    protected DataModel dataModel;
    protected EvalItemDto dto;

    protected AbstractEvaluator(DataModel dataModel, EvalItemDto dto) {
        if (dataModel == null || dto == null || dto.evalMap == null || dto.evalMap.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.dataModel = dataModel;
        this.dto = dto;
    }

    protected abstract void eval() throws TasteException;

}
