package com.instantappsample.featureshop;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.instantappsample.featureshop.model.CactiShopItem;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;

/**
 * The <code>RecyclerView</code> for the cacti shop feature, which takes the passed data and fills
 * in the holder view fields accordingly.
 */
public class CactiShopRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<CactiShopItem> mCactiShopItemData;

    public CactiShopRecyclerAdapter(List<CactiShopItem> cactiShopItemData) {
        this.mCactiShopItemData = cactiShopItemData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        return new CactiInformationCardViewHolder(layoutInflater.inflate(
                        CactiInformationCardViewHolder.LAYOUT_RES_ID, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        CactiShopItem cactiShopItem = getItem(position);
        if (cactiShopItem != null) {
            CactiInformationCardViewHolder cardViewHolder = (CactiInformationCardViewHolder) viewHolder;

            // Set fields directly from data
            cardViewHolder.cardItemImage.setImageResource(cactiShopItem.getCactiImageResId());
            cardViewHolder.cardItemTitle.setText(cactiShopItem.getCactiItemTitle());

            // Set the rating field to have 5 stars, and fill in the rating directly from data
            cardViewHolder.cardItemRating.setNumStars(5);
            cardViewHolder.cardItemRating.setRating(cactiShopItem.getCactiItemRating());

            // If the cacti item is in stock, then set the text, text color, and edit field accordingly
            Context context = cardViewHolder.cardItemRootView.getContext();
            if (cactiShopItem.isCactiItemInStock()) {
                cardViewHolder.cardItemStock.setText(context.getString(R.string.card_item_in_stock));
                cardViewHolder.cardItemStock.setTextColor(context.getColor(R.color.colorDarkGreen));
                cardViewHolder.cardItemQuantityEdit.setEnabled(true);
            } else {
                cardViewHolder.cardItemStock.setText(context.getString(R.string.card_item_out_of_stock));
                cardViewHolder.cardItemStock.setTextColor(context.getColor(R.color.colorDarkRed));
                cardViewHolder.cardItemQuantityEdit.setEnabled(false);
            }

            // Set the price as the BigDecimal from data, in the proper format
            BigDecimal cactiItemPrice = cactiShopItem.getCactiItemPrice();
            String roundedPrice = NumberFormat.getCurrencyInstance().format(cactiItemPrice);
            cardViewHolder.cardItemPrice.setText(roundedPrice);

        }
    }

    @Override
    public int getItemCount() {
        return mCactiShopItemData.size();
    }

    private static class CactiInformationCardViewHolder extends RecyclerView.ViewHolder {
        public static final int LAYOUT_RES_ID = R.layout.cacti_shop_card;
        ViewGroup cardItemRootView;
        ImageView cardItemImage;
        TextView cardItemTitle;
        TextView cardItemStock;
        RatingBar cardItemRating;
        TextView cardItemPrice;
        EditText cardItemQuantityEdit;

        public CactiInformationCardViewHolder(View itemView) {
            super(itemView);
            cardItemRootView = (ViewGroup) itemView.findViewById(R.id.card_root_layout);
            cardItemImage = (ImageView) itemView.findViewById(R.id.card_image);
            cardItemTitle = (TextView) itemView.findViewById(R.id.card_header_text);
            cardItemStock = (TextView) itemView.findViewById(R.id.card_stock_text);
            cardItemRating = (RatingBar) itemView.findViewById(R.id.card_rating);
            cardItemPrice = (TextView) itemView.findViewById(R.id.card_price_text);
            cardItemQuantityEdit = (EditText) itemView.findViewById(R.id.card_quantity_edit);
        }
    }

    private CactiShopItem getItem(int position) {
        if (mCactiShopItemData != null) {
            return mCactiShopItemData.get(position);
        }
        return null;
    }
}
