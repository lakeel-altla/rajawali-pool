package com.lakeel.altla.vision.builder.presentation.view;

import com.lakeel.altla.vision.builder.presentation.model.UserAssetImageDragModel;

import android.net.Uri;
import android.support.annotation.NonNull;

public interface UserAssetImageItemView {

    void onUpdateName(@NonNull String name);

    void onUpdateThumbnail(@NonNull Uri uri);

    void onStartDrag(@NonNull UserAssetImageDragModel model);
}
