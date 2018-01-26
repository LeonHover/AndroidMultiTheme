package io.github.leonhover.theme.samples;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.leonhover.theme.base.BaseThemeFragment;

/**
 * Created by wangzongliang on 18-1-26.
 */

public class SampleFragment extends BaseThemeFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_sample, container, false);
    }
}
