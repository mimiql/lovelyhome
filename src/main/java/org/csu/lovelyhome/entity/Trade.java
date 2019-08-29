package org.csu.lovelyhome.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 交易信息表
 * </p>
 *
 * @author lqm
 * @since 2019-08-29
 */
@TableName("trade")
public class Trade implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 交易id
     */
    @TableId(value = "trade_id", type = IdType.AUTO)
    private Integer tradeId;

    /**
     * 户型id
     */
    private Integer huixngId;

    /**
     * 户型所属楼层
     */
    private Integer level;

    /**
     * 卖方
     */
    private Integer seller;

    /**
     * 买方
     */
    private Integer buyer;

    /**
     * 1出售2出租
     */
    private Integer type;


    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public Integer getHuixngId() {
        return huixngId;
    }

    public void setHuixngId(Integer huixngId) {
        this.huixngId = huixngId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getSeller() {
        return seller;
    }

    public void setSeller(Integer seller) {
        this.seller = seller;
    }

    public Integer getBuyer() {
        return buyer;
    }

    public void setBuyer(Integer buyer) {
        this.buyer = buyer;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Trade{" +
        "tradeId=" + tradeId +
        ", huixngId=" + huixngId +
        ", level=" + level +
        ", seller=" + seller +
        ", buyer=" + buyer +
        ", type=" + type +
        "}";
    }
}
