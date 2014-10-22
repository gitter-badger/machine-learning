package jp.com.pollseed.wrapper.item;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.model.DataModel;

abstract class AbstractItem {

    protected DataModel dataModel;
    protected ItemVO dto;

    protected AbstractItem(DataModel dataModel, ItemVO dto) {
        if (dataModel == null || dto == null || dto.itemMap == null || dto.itemMap.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.dataModel = dataModel;
        this.dto = dto;
    }

    protected abstract void affinity() throws TasteException;

}
