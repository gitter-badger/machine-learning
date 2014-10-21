package jp.com.machine.test;

import java.io.File;
import java.io.IOException;

import jp.com.machine.test.eval.EvalDto;
import jp.com.machine.test.eval.EvalItemDto;
import jp.com.machine.test.eval.Evaluator;
import jp.com.machine.test.eval.EvalItemDto.EvalName;
import jp.com.machine.test.item.Item;
import jp.com.machine.test.item.ItemDto;
import jp.com.machine.test.item.ItemItemDto;
import jp.com.machine.test.item.ItemItemDto.ItemName;
import jp.com.machine.test.user.User;
import jp.com.machine.test.user.UserDto;
import jp.com.machine.test.user.UserItemDto;
import jp.com.machine.test.user.UserItemDto.UserName;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;

/**
 * Mahoutのアルゴリズムを使うためのイントロダクションクラス<br>
 * <b>※only UTF-8
 */
class Introduction {

    private static final String DIRECTORY_PATH = "\\machineTest\\jp\\com\\machine\\test\\";
    private static final String INPUT_PATH = String.format("%ssegment.csv", DIRECTORY_PATH);
    private static final String OUTPUT_NAME = String.format("%ssegoutput.csv", DIRECTORY_PATH);

    public static void main(String[] args) throws IOException, TasteException {

        DataModel dataModel = new FileDataModel(new File(OUTPUT_NAME));

        EvalItemDto evalDto = new EvalItemDto();
        evalDto.evalMap.put(EvalName.MAE, new EvalDto(0.78, 1.0));
        evalDto.evalMap.put(EvalName.RMS, new EvalDto(0.69, 1.0));
        Evaluator eval = new Evaluator(dataModel, evalDto);
        eval.eval();

        UserItemDto userDto = new UserItemDto();
        userDto.userMap.put(UserName.PEARSON, new UserDto(3, 1, 100));
        userDto.userMap.put(UserName.EUCLIDEAN, new UserDto(3, 1, 100));
        userDto.userMap.put(UserName.COSINE, new UserDto(3, 1, 100));
        User user = new User(dataModel, userDto);
        user.affinity();

        ItemItemDto itemDto = new ItemItemDto();
        itemDto.itemMap.put(ItemName.TANIMOTO, new ItemDto(10, 100));
        itemDto.itemMap.put(ItemName.CITY_BLOCK, new ItemDto(1, 100));
        itemDto.itemMap.put(ItemName.LOG_LIKE, new ItemDto(1, 100));
        itemDto.itemMap.put(ItemName.EUCLIDEAN, new ItemDto(1, 100));
        itemDto.itemMap.put(ItemName.COSINE, new ItemDto(1, 100));
        Item item = new Item(dataModel, itemDto);
        item.affinity();
    }
}
