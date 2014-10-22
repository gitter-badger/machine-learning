package jp.com.pollseed.wrapper.main;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import jp.com.pollseed.wrapper.eval.EvaluationVO;
import jp.com.pollseed.wrapper.eval.EvalItemVO;
import jp.com.pollseed.wrapper.eval.EvalItemVO.EvalName;
import jp.com.pollseed.wrapper.eval.Evaluator;
import jp.com.pollseed.wrapper.item.Item;
import jp.com.pollseed.wrapper.item.ItemAffinityVO;
import jp.com.pollseed.wrapper.item.ItemVO;
import jp.com.pollseed.wrapper.item.ItemVO.ItemName;
import jp.com.pollseed.wrapper.user.User;
import jp.com.pollseed.wrapper.user.UserAffinityVO;
import jp.com.pollseed.wrapper.user.UserItemVO;
import jp.com.pollseed.wrapper.user.UserItemVO.UserName;

import org.apache.commons.io.FileUtils;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;

/**
 * Mahoutのアルゴリズムを使うためのHOW TO USEクラス<br>
 * <ol><li>{@link #main(String[])}のみcall可能です.</li>
 * <li>only UTF-8</li></ol>
 */
class MahoutGenerator {

    private MahoutGenerator() throws IOException, TasteException {
        this.generate();
    }

    public static void main(String[] args) throws IOException, TasteException {
        new MahoutGenerator();
    }

    private void generate() throws IOException, TasteException {
        final DataModel dataModel = new FileDataModel(FileUtils.getFile(this.getFile()));

        EvalItemVO evalItemVO = new EvalItemVO(2);
        evalItemVO.evalMap.put(EvalName.MAE, new EvaluationVO(0.7, 1.0));
        evalItemVO.evalMap.put(EvalName.RMS, new EvaluationVO(0.7, 1.0));
        Evaluator eval = new Evaluator(dataModel, evalItemVO);
        eval.eval();

        UserItemVO userItemVO = new UserItemVO();
        userItemVO.userMap.put(UserName.PEARSON, new UserAffinityVO(2, 6, 1));
        userItemVO.userMap.put(UserName.EUCLIDEAN, new UserAffinityVO(2, 6, 1));
        userItemVO.userMap.put(UserName.COSINE, new UserAffinityVO(2, 6, 1));
        User user = new User(dataModel, userItemVO);
        user.affinity();

        ItemVO itemVO = new ItemVO();
        itemVO.itemMap.put(ItemName.TANIMOTO, new ItemAffinityVO(1, 5));
        itemVO.itemMap.put(ItemName.CITY_BLOCK, new ItemAffinityVO(1, 5));
        itemVO.itemMap.put(ItemName.LOG_LIKE, new ItemAffinityVO(1, 5));
        itemVO.itemMap.put(ItemName.EUCLIDEAN, new ItemAffinityVO(1, 5));
        itemVO.itemMap.put(ItemName.COSINE, new ItemAffinityVO(1, 5));
        Item item = new Item(dataModel, itemVO);
        item.affinity();
    }

    private File getFile() {
        System.out.println("> Enter input file path.");
        Scanner sc = null;
        try {
            sc = new Scanner(System.in);
            File file = FileUtils.getFile(new Scanner(System.in).next());
            System.out.println("< Auto output result.");
            return file;
        } finally {
            sc.close();
        }
    }
}
