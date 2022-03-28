package com.allure.lbanners.adapter;

import android.content.Context;
import android.view.View;

import com.allure.lbanners.LMBanners;

public interface LBaseAdapter<T> {
    View getView(LMBanners lBanners, Context context, int position, T data);
}
