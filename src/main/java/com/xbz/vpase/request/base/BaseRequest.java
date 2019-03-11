package com.xbz.vpase.request.base;


public class BaseRequest {
    private Integer start;
    private Integer end;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public void setPage(Integer pageNum,Integer pageSize){
        if(pageNum!=null&&pageSize!=null){
            this.start=(pageNum-1)*pageSize;
            this.end=pageSize;
        }
    }


}
