package com.nextdots.mycomics.mvp.views.renderers;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nextdots.mycomics.R;
import com.nextdots.mycomics.common.model.comics.Comic;
import com.nextdots.mycomics.common.model.comics.ComicDate;
import com.nextdots.mycomics.common.model.comics.Creator;
import com.nextdots.mycomics.common.model.comics.CreatorsList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * Basic comic renderer
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 18/12/16
 */
public class ComicDetailRenderer extends ComicRenderer {

  /** Tag for logs **/
  private static final String TAG = ComicDetailRenderer.class.getSimpleName();

  /** Origin date format **/
  private static final String ORIGIN_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

  /** Target date format **/
  private static final String TARGET_DATE_FORMAT = "yyyy/MM/dd";

  /** On sale date **/
  private static final String ON_SALE_DATE = "onsaleDate";

  /** Creators separator **/
  private static final String CREATORS_SEPARATOR = ", ";

  /** Description TextView **/
  @BindView(R.id.description_tv)
  TextView mDescriptionTv;

  /** On sale date TextView **/
  @BindView(R.id.on_sale_date_tv)
  TextView mOnSaleDateTv;

  /** Creators TextView **/
  @BindView(R.id.creators_tv)
  TextView mCreatorsTv;

  /**
   * Comic detail renderer constructor for an existing view
   *
   * @param comicView
   *         Created comic view
   */
  public ComicDetailRenderer(Comic comic, LayoutInflater layoutInflater, View comicView,
                             ViewGroup parent) {
    super(comicView);
    onCreate(comic, layoutInflater, parent);
  }

  @Override
  public void render() {
    super.render();
    renderDescription();
    renderDate();
    renderCreators();
    // TODO: Add other render items such as description, dates, creators, etc.
  }

  /**
   * Renders the comic's creators
   */
  private void renderCreators() {
    CreatorsList creatorsList = getContent().getCreators();
    if (creatorsList == null) {
      return;
    }
    StringBuilder creatorsStringBuilder = new StringBuilder();
    boolean first = true;
    List<Creator> creators = creatorsList.getCreators();
    for (Creator creator : creators) {
      if (!first) {
        creatorsStringBuilder.append(CREATORS_SEPARATOR);
      } else {
        first = false;
      }
      creatorsStringBuilder.append(creator.getName());
    }
    mCreatorsTv.setText(creatorsStringBuilder.toString());
  }

  /**
   * Renders the comic's description
   */
  private void renderDescription() {
    mDescriptionTv.setText(getContent().getDescription());
  }

  /**
   * Renders the comic's description
   */
  private void renderDate() {
    for (ComicDate date : getContent().getDates()) {
      if (ON_SALE_DATE.equals(date.getType())) {
        mOnSaleDateTv.setText(processDate(date));
        return;
      }
    }
  }

  /**
   * Processes the date using the right format
   *
   * @param date
   *         Date to be parsed
   *
   * @return The formatted date
   */
  private String processDate(ComicDate date) {
    SimpleDateFormat originFormat = new SimpleDateFormat(ORIGIN_DATE_FORMAT);
    SimpleDateFormat targetFormat = new SimpleDateFormat(TARGET_DATE_FORMAT);
    try {
      Date realDate = originFormat.parse(date.getDate());
      return targetFormat.format(realDate);
    } catch (ParseException e) {
      Log.e(TAG, "Error parsing the date", e);
    }
    return null;
  }

}
