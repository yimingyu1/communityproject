package com.nowcoder.community.entity;

/**
 * ClassName: Page
 * Description:
 * date: 2021/2/20 上午9:24
 *
 * @author yimingyu
 * @version 1.0
 * @since JDK 1.8
 */
public class Page {
    // 当前页面
    private int pageNum = 1;
    // 每页的数据条数
    private int pageSize = 5;
    // 总的数据条数
    private int rows;
    // 每页的跳转路径
    private String path;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        if (pageNum > 0 ) {
            this.pageNum = pageNum;
        }else{
            this.pageNum = 1;
        }
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize > 0 && pageSize <50) {
            this.pageSize = pageSize;
        }else {
            this.pageSize = 5;
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取当前页的起始行
     * @return
     */
    public int getOffSet(){
        return (this.pageNum -1) * pageSize;
    }

    /**
     * 获取总页数
     * @return
     */
    public int getTotal(){
        if (this.rows % this.pageSize == 0){
            return this.rows / this.pageSize;
        }else {
            return this.rows /this.pageSize + 1;
        }
    }

    /**
     * 起始页
     * @return
     */
    public int getFrom(){
        int from = pageNum - 2;
        return Math.max(from, 1);
    }

    /**
     * 结尾页
     * @return
     */
    public int getTo(){
        int to = pageNum + 2;
        return Math.min(to, getTotal());
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", rows=" + rows +
                ", path='" + path + '\'' +
                '}';
    }
}
