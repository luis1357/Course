package com.yeah.ruisu.mvvmexample;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


public abstract class BaseActivity <T extends ViewDataBinding,
                                    V extends  BaseViewModel>
    extends AppCompatActivity
{
    private T binding;
    private V viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        bind();
    }

    public void bind()
    {
        binding = DataBindingUtil.setContentView(this, getLayoutId());

        this.viewModel = viewModel == null? onCreate() : viewModel;

        binding.setVariable(getVariable(), viewModel);
        binding.executePendingBindings();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        viewModel.onResume();
    }

    public abstract V onCreate();

    public abstract @IdRes
    int getVariable();

    public abstract @LayoutRes
    int getLayoutId();
}
