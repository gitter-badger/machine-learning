package jp.com.pollseed.wrapper.eval;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.model.DataModel;

abstract class AbstractEvaluator {

    protected DataModel dataModel;
    protected EvalItemVO dto;

    protected AbstractEvaluator(DataModel dataModel, EvalItemVO dto) {
        if (dataModel == null || dto == null || dto.evalMap == null || dto.evalMap.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.dataModel = dataModel;
        this.dto = dto;
    }

    protected abstract void eval() throws TasteException;

}
