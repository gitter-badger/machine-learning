package jp.com.machine.test.item;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.model.DataModel;

abstract class AbstractItem {

    protected DataModel dataModel;
    protected ItemItemDto dto;

    protected AbstractItem(DataModel dataModel, ItemItemDto dto) {
        if (dataModel == null || dto == null || dto.itemMap == null || dto.itemMap.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.dataModel = dataModel;
        this.dto = dto;
    }

    protected abstract void affinity() throws TasteException;

}
