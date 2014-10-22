package jp.com.pollseed.wrapper.main;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import jp.com.pollseed.wrapper.eval.EvalDto;
import jp.com.pollseed.wrapper.eval.EvalItemDto;
import jp.com.pollseed.wrapper.eval.EvalItemDto.EvalName;
import jp.com.pollseed.wrapper.eval.Evaluator;
import jp.com.pollseed.wrapper.item.Item;
import jp.com.pollseed.wrapper.item.ItemDto;
import jp.com.pollseed.wrapper.item.ItemItemDto;
import jp.com.pollseed.wrapper.item.ItemItemDto.ItemName;
import jp.com.pollseed.wrapper.user.User;
import jp.com.pollseed.wrapper.user.UserDto;
import jp.com.pollseed.wrapper.user.UserItemDto;
import jp.com.pollseed.wrapper.user.UserItemDto.UserName;

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
        DataModel dataModel = new FileDataModel(FileUtils.getFile(getFile()));

        EvalItemDto evalDto = new EvalItemDto();
        evalDto.evalMap.put(EvalName.MAE, new EvalDto(0.78, 1.0));
        evalDto.evalMap.put(EvalName.RMS, new EvalDto(0.69, 1.0));
        Evaluator eval = new Evaluator(dataModel, evalDto);
        eval.eval();

        UserItemDto userDto = new UserItemDto();
        userDto.userMap.put(UserName.PEARSON, new UserDto(4, 1, 100));
        userDto.userMap.put(UserName.EUCLIDEAN, new UserDto(4, 1, 100));
        userDto.userMap.put(UserName.COSINE, new UserDto(4, 1, 100));
        User user = new User(dataModel, userDto);
        user.affinity();

        ItemItemDto itemDto = new ItemItemDto();
        itemDto.itemMap.put(ItemName.TANIMOTO, new ItemDto(10, 1));
        itemDto.itemMap.put(ItemName.CITY_BLOCK, new ItemDto(1, 1));
        itemDto.itemMap.put(ItemName.LOG_LIKE, new ItemDto(1, 1));
        itemDto.itemMap.put(ItemName.EUCLIDEAN, new ItemDto(1, 1));
        itemDto.itemMap.put(ItemName.COSINE, new ItemDto(1, 1));
        Item item = new Item(dataModel, itemDto);
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
