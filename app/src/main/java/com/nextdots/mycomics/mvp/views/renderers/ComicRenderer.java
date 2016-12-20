package com.nextdots.mycomics.mvp.views.renderers;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nextdots.mycomics.R;
import com.nextdots.mycomics.common.model.comics.Comic;
import com.nextdots.mycomics.common.model.comics.Price;
import com.nextdots.mycomics.mvp.presenters.comics.ComicsListPresenter;
import com.nextdots.mycomics.utils.ImageUtils;
import com.nextdots.mycomics.utils.ViewUtils;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Basic comic renderer
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 18/12/16
 */
public class ComicRenderer extends BaseRenderer<Comic> {

  /** Print price value **/
  private static final String PRINT_PRICE_TYPE = "printPrice";

  /** Comic logo **/
  @BindView(R.id.comic_logo_iv)
  ImageView mComicLogoIv;

  /** Comic title **/
  @BindView(R.id.comic_title_tv)
  TextView mComicTitleTv;

  /** Comic price **/
  @BindView(R.id.comic_price_tv)
  TextView mComicPriceTv;

  /** Favorite mark **/
  @BindView(R.id.favorite_iv)
  ImageView mFavoriteMarkIv;

  /** Comics list presenter **/
  private final ComicsListPresenter mComicsListPresenter;

  /**
   * Comic renderer constructor
   *
   * @param comicsListPresenter
   *         Comics list presenter
   */
  public ComicRenderer(ComicsListPresenter comicsListPresenter) {
    this.mComicsListPresenter = comicsListPresenter;
  }

  @NonNull
  @Override
  protected View inflateView(LayoutInflater inflater, ViewGroup parent) {
    return inflater.inflate(R.layout.layout_item_comic, parent, false);
  }

  @Override
  public void render() {
    Comic comic = getContent();
    renderLogo(comic);
    renderTitle(comic);
    renderPrice(comic);
    renderFavorite(comic);
  }

  /**
   * Called when favorite icon is clicked
   */
  @OnClick(R.id.favorite_iv)
  public void onClickFavorite() {
    Comic comic = getContent();
    boolean addFavorite = !mComicsListPresenter.isFavoriteComic(comic);
    if (addFavorite) {
      mComicsListPresenter.saveFavorite(comic);
    } else {
      mComicsListPresenter.removeFavorite(comic);
    }
    renderFavorite(comic);
  }

  /**
   * Renders the comic's logo
   *
   * @param comic
   *         The comic
   */
  private void renderLogo(Comic comic) {
    ImageUtils.displayImage(mComicLogoIv, comic.getThumbnail().getPath(), null);
  }

  /**
   * Renders the comic's title
   *
   * @param comic
   *         The comic
   */
  private void renderTitle(Comic comic) {
    mComicTitleTv.setText(comic.getTitle());
  }

  /**
   * Renders the comic's price
   *
   * @param comic
   *         The comic
   */
  private void renderPrice(Comic comic) {
    List<Price> prices = comic.getPrices();
    double priceValue = 0;
    for (Price price : prices) {
      if (PRINT_PRICE_TYPE.equals(price.getType())) {
        priceValue = price.getValue();
      }
    }
    boolean isSoldOut = priceValue == 0;
    int textColor = isSoldOut ? R.color.colorSoldOut : R.color.colorPrice;
    mComicPriceTv.setTextColor(ViewUtils.getColor(getContext(), textColor));
    if (isSoldOut) {
      mComicPriceTv.setText(R.string.sold_out);
    } else {
      DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
      String formattedValue = decimalFormat.format(priceValue);
      mComicPriceTv.setText(formattedValue);
    }
  }

  /**
   * Renders if a comic is a user's favorite or not
   *
   * @param comic
   *         The comic
   */
  protected void renderFavorite(Comic comic) {
    boolean isFavorite = mComicsListPresenter.isFavoriteComic(comic);
    int favoriteDrawable =
            isFavorite ? R.drawable.ic_favorite_red_light : R.drawable.ic_favorite_black_12;
    mFavoriteMarkIv.setImageDrawable(ViewUtils.getDrawable(getContext(), favoriteDrawable));
  }

}
