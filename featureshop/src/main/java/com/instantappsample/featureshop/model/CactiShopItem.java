package com.instantappsample.featureshop.model;

import com.instantappsample.cacticollector.base.model.CactiItemBase;

import java.math.BigDecimal;

/**
 * Model object containing a field for use in a cacti shop card.  Note the extension off of
 * the base model {@link CactiItemBase}, which is located in the base feature module.
 */
public class CactiShopItem extends CactiItemBase {

    private int cactiItemRating;

    private boolean cactiItemInStock;

    private BigDecimal cactiItemPrice;

    public CactiShopItem(int cactiImageResId,
                         String cactiItemTitle,
                         int cactiItemRating,
                         boolean cactiItemInStock,
                         BigDecimal cactiItemPrice) {
        super(cactiImageResId, cactiItemTitle);
        this.cactiItemRating = cactiItemRating;
        this.cactiItemInStock = cactiItemInStock;
        this.cactiItemPrice = cactiItemPrice;
    }

    public int getCactiItemRating() {
        return cactiItemRating;
    }

    public void setCactiItemRating(int cactiItemRating) {
        this.cactiItemRating = cactiItemRating;
    }

    public boolean isCactiItemInStock() {
        return cactiItemInStock;
    }

    public void setCactiItemInStock(boolean cactiItemInStock) {
        this.cactiItemInStock = cactiItemInStock;
    }

    public BigDecimal getCactiItemPrice() {
        return cactiItemPrice;
    }

    public void setCactiItemPrice(BigDecimal cactiItemPrice) {
        this.cactiItemPrice = cactiItemPrice;
    }
}
