package jp.com.pollseed.wrapper.eval;

import java.util.HashMap;
import java.util.Map;

public class EvalItemVO {

    public EvalItemVO(int size) {
        this.size = size;
        this.evalMap = new HashMap<EvalItemVO.EvalName, EvaluationVO>(EvalName.values().length);
    }

    public final int size;
    public final Map<EvalName, EvaluationVO> evalMap;

    public static enum EvalName {
        MAE(1, "mean average error"),
        RMS(2, "root mean squared"), ;

        public int num;
        public String name;

        EvalName(int num, String name) {
            this.num = num;
            this.name = name;
        }

        public static String getName(long val) {
            for (EvalName _val : EvalName.values()) {
                if (_val.num == val) {
                    return _val.name;
                }
            }
            return null;
        }
    }
}
