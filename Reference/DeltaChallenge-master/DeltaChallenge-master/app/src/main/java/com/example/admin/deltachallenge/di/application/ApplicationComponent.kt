package com.example.admin.deltachallenge.di.application

import com.example.admin.deltachallenge.di.activity.ActivityComponent
import com.example.admin.deltachallenge.di.activity.ActivityModule
import com.example.admin.deltachallenge.ui.CommunicatorViewModel
import com.example.admin.deltachallenge.ui.MainViewModel
import dagger.Component

@ApplicationScope
@Component(modules = [ApplicationModule::class, LiveDataModule::class])
interface ApplicationComponent {

    fun newActivityComponent(activityModule: ActivityModule): ActivityComponent

    fun inject(communicatorViewModel: CommunicatorViewModel)

}