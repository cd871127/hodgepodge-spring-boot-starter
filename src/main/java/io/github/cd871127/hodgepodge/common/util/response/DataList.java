package io.github.cd871127.hodgepodge.common.util.response;

import java.io.Serializable;
import java.util.List;

public class DataList<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public DataList() {
    }

    public DataList(List<T> data, int count) {
        this();
        setData(data);
        setCount(count);
    }

    private List<T> data;

    private int count;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
